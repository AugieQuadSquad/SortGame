package csc420.augustana.com.quadsquadgame;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.view.View;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class BubbleSort extends AppCompatActivity {


    public static TextView[] items = new TextView[8];
    TextView clicked1st = null;
    Animation animAlpha;
    int totalCount;
    int currentMove;
    int currentGame;
    String secondsRemaining;
    boolean isCanceled;
    int totalScore;

    TextView highscore1;
    TextView highscore2;
    TextView highscore3;
    TextView highscore4;
    TextView highscore5;
    Button hint;
    Button test;
    Button reset;
    TextView myTimer;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String[] BubbleScoreKeys;
    int[] BubbleHighScoresValues;

    // CR changes
    int[] arrayDisplayed;
    Random rand;

    CountDownTimer timer;

    List<Pairs> pairsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sort);

        // CR changes
        arrayDisplayed = new int[8];
        rand = new Random();
        for(int i=0; i < arrayDisplayed.length; i++) {
            arrayDisplayed[i] = rand.nextInt(50) + 1;
        }

        // TODO: get rid of highscores and move to new class....
        totalCount = 0;
        currentMove = 0;
        currentGame = 0;

        // TODO: get rid of isCanceled - add timer to new class ... ?
        isCanceled = false;

        // sets up SharedPreferences for high scores
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();

        // BubbleHighScoresValues represents int high scores to be saved in SharedPreferences
        BubbleHighScoresValues = new int[5];
        // BubbleScoreKeys represents strings used for keys for the SharedPreferences
        BubbleScoreKeys = new String[5];

        // CR change - creates array for sharedPreference keys

        BubbleScoreKeys[0] = "Score1";
        BubbleScoreKeys[1] = "Score2";
        BubbleScoreKeys[2] = "Score3";
        BubbleScoreKeys[3] = "Socre4";
        BubbleScoreKeys[4] = "Score5";

        for (int i = 0; i < 5; i++) {
            BubbleHighScoresValues[i] = pref.getInt(BubbleScoreKeys[i], 0);
            // displayMessage(BubbleScoreKeys[i]);
        }

        hint = (Button) findViewById(R.id.hint);
        test = (Button) findViewById(R.id.test);
        reset = (Button) findViewById(R.id.resetButton);

        myTimer = (TextView) findViewById(R.id.timer);

        // sets item array items as the text views
        items[0] = (TextView) findViewById(R.id.item1);
        items[0].setTag(R.drawable.box_one);
        items[1] = (TextView) findViewById(R.id.item2);
        items[1].setTag(R.drawable.box_two);
        items[2] = (TextView) findViewById(R.id.item3);
        items[2].setTag(R.drawable.box_three);
        items[3] = (TextView) findViewById(R.id.item4);
        items[3].setTag(R.drawable.box_four);
        items[4] = (TextView) findViewById(R.id.item5);
        items[4].setTag(R.drawable.box_five);
        items[5] = (TextView) findViewById(R.id.item6);
        items[5].setTag(R.drawable.box_six);
        items[6] = (TextView) findViewById(R.id.item7);
        items[6].setTag(R.drawable.box_seven);
        items[7] = (TextView) findViewById(R.id.item8);
        items[7].setTag(R.drawable.box_eight);
        totalCount = 8;


        // TODO: add source
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

        for (int i = 0; i < items.length; i++) {
            items[i].setText(arrayDisplayed[i] + "");
            items[i].setOnClickListener(myClickListener);
        }

        // this initializes the order of swaps for bubble sort
        // if makes this expandable to use the same activity for all the sorts
        if (currentGame == 0) {
            // pairs = bubbleSort();
            pairsList = BubbleSortModel.getSwapSequence(buildArray());
        }

        // 60000ms with a tick every 1000
        timer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                myTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                secondsRemaining = "" + millisUntilFinished / 1000;
            }

            public void onFinish() {
                myTimer.setText("done!");
                if (isSorted(buildArray())) {
                    secondsRemaining = 0 + "";
                    showDialog(true);
                } else {
                    secondsRemaining = 0 + "";
                    showDialog(false);
                }
            }
        }.start();
    }

    // swaps two text views
    private static void swap(TextView item1TV, TextView item2TV) {
        CharSequence tempText = item1TV.getText();
        int tempBackground = (int) item1TV.getTag();
        item1TV.setText(item2TV.getText());
        item1TV.setBackgroundResource((int) item2TV.getTag());
        item2TV.setText(tempText);
        item2TV.setBackgroundResource(tempBackground);
        item1TV.setTag(item2TV.getTag());
        item2TV.setTag(tempBackground);
        // animation - from external library
        // TODO: add source
        YoYo.with(Techniques.SlideInDown)
                .duration(700)
                .playOn(item1TV);
        YoYo.with(Techniques.SlideInDown)
                .duration(700)
                .playOn(item2TV);
    }

    // tests to see if current move is the correct next move for any sort
    public void testSwap(View view) {
        int[] current = buildArray();
        if (isSorted(current)) {
            timer.cancel();
            getTotalScore();
        } else {
            BubbleHighScores.addWrongTest();
            displayMessage("Keep Trying!");
        }
    }

    // TODO: change for loops to fix SharePreferences high scores
    public int getTotalScore() {
        totalScore = BubbleHighScores.getTotalScore(Integer.parseInt(secondsRemaining));
        BubbleHighScoresValues = BubbleHighScores.scoreBoard(BubbleHighScoresValues);
        for(int i = 0; i< BubbleHighScoresValues.length; i++){
            editor.putInt(BubbleScoreKeys[i], BubbleHighScoresValues[i]);
            editor.apply();
        }
        showDialog(true);
        return totalScore;
    }

    // executes next move according to pairs array
    public void hint(View view) {
        if (!isSorted(buildArray())) {
            BubbleHighScores.addHint();
            // CR change
            // swap(items[pairsList[currentMove].getFirst()], items[pairs[currentMove].getSecond()]);
            swap(items[pairsList.get(currentMove).getFirst()], items[pairsList.get(currentMove).getSecond()]);
            currentMove++;
        } else {
            BubbleHighScores.addHint();
            displayMessage("No more moves!");
        }
    }

    private View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (clicked1st == null) {
                clicked1st = (TextView) v;
                clicked1st.setTextSize(45);
                // TODO: add source
                v.startAnimation(animAlpha);
            } else {
                if (clicked1st.equals(v)) {
                    clicked1st.setTextSize(30);
                    clicked1st = null;
                } else if (isNextMove(clicked1st, (TextView) v)) {
                    swap(clicked1st, (TextView) v);
                    clicked1st.setTextSize(30);
                    clicked1st = null;
                    currentMove++;
                } else {
                    BubbleHighScores.addWrongMove();
                    displayMessage("Wrong move");
                    clicked1st.setTextSize(30);
                    clicked1st = null;
                }

            }

        }
    };


    public void displayMessage(String string) {
        Toast.makeText(this, (string), Toast.LENGTH_SHORT).show();
    }

    // checks if sorted
    public boolean isSorted(int[] outsideArray) {
        boolean sorted = true;
        for(int i=0; i < outsideArray.length-1; i++) {
            if(outsideArray[i] > outsideArray[i+1]){
                sorted = false;
            }
        }
        return sorted;
    }

    // creates an int array of the current order of textViews
    public int[] buildArray() {
        int[] array = new int[totalCount];
        for (int i = 0; i < totalCount; i++) {
            array[i] = Integer.parseInt(items[i].getText().toString());
        }
        return array;
    }

    // checks if the move attempted by the user is the correct next move
    public boolean isNextMove(TextView tv1, TextView tv2) {
        int indexOf1 = Arrays.asList(items).indexOf(tv1);
        int indexOf2 = Arrays.asList(items).indexOf(tv2);
        if (indexOf1 == pairsList.get(currentMove).getFirst() && indexOf2 == pairsList.get(currentMove).getSecond()) {
            return true;
        } else {
            return false;
        }
    }

    // resets the gameBoard screen to original state
    public void reset(View view) {
        BubbleHighScores.addResetClick();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    // TODO: source
    // adds tutorial to show user how to use interface
    public void Tutorial(View view) {
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); //Delay is in milliseconds

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        sequence.setConfig(config);

        sequence.addSequenceItem(items[0], "Tap the first item that you would like to switch", "Next");

        sequence.addSequenceItem(items[1], "and switch it by tapping the second item.", "Next");

        sequence.addSequenceItem(hint, "If you get stuck, use a hint! It will complete the next move for you.", "Next");

        sequence.addSequenceItem(reset, "If you mess up, don't worry! Just reset the game and try again.", "Next");

        sequence.addSequenceItem(test, "If you think you've got it, find out with the test button!", "Close");

        sequence.start();
    }

    // shows the pop-up screen after finishing round
    public void showDialog(boolean timeOut) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final TextView timeRemaining = (TextView) dialogView.findViewById(R.id.timeRemaining);
        final TextView hintsUsed = (TextView) dialogView.findViewById(R.id.hintsUsed);
        final TextView incorrectTests = (TextView) dialogView.findViewById(R.id.incorrectTests);
        final TextView wrongMove = (TextView) dialogView.findViewById(R.id.wrongMoves);
        final TextView totalScores = (TextView) dialogView.findViewById(R.id.totalScore);

        timeRemaining.setText("Time Remaining: " + secondsRemaining);
        hintsUsed.setText("Number of Hints used: " + BubbleHighScores.getHintsCount());
        incorrectTests.setText("Number of incorrect tests: " + BubbleHighScores.getWrongTestCount());
        wrongMove.setText("Number of Wrong Moves: " + BubbleHighScores.getWrongMovesCount());
        totalScores.setText("Total Score: " + totalScore);

        if (timeOut) {
            dialogBuilder.setTitle("You Win!");
        } else {
            dialogBuilder.setTitle("You Lose!");
        }
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    // TODO: restart game with different array values
    public void playAgain(View view) {
        Intent tutorialOption = new Intent(BubbleSort.this, BubbleSort.class);
        tutorialOption.putExtra("button", "2");
        startActivity(tutorialOption);
    }

    public void quit(View view) {
        Intent tutorialOption = new Intent(BubbleSort.this, MainMenu.class);
        startActivity(tutorialOption);
    }

    // shows the pop-up for the high scores using SharedPreferences
    public void highScores(View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.scores_dialog, null);
        dialogBuilder.setView(dialogView);

        highscore1 = (TextView) dialogView.findViewById(R.id.highscore1);
        highscore2 = (TextView) dialogView.findViewById(R.id.highscore2);
        highscore3 = (TextView) dialogView.findViewById(R.id.highscore3);
        highscore4 = (TextView) dialogView.findViewById(R.id.highscore4);
        highscore5 = (TextView) dialogView.findViewById(R.id.highscore5);

        /*highscore1.setText("Highscore 1: " + BubbleHighScoresValues[0]);
        highscore2.setText("Highscore 2: " + BubbleHighScoresValues[1]);
        highscore3.setText("Highscore 3: " + BubbleHighScoresValues[2]);
        highscore4.setText("Highscore 4: " + BubbleHighScoresValues[3]);
        highscore5.setText("Highscore 5: " + BubbleHighScoresValues[4]);*/

        highscore1.setText("Highscore 1: " + pref.getInt(BubbleScoreKeys[0], 0));
        highscore2.setText("Highscore 2: " + pref.getInt(BubbleScoreKeys[1], 0));
        highscore3.setText("Highscore 3: " + pref.getInt(BubbleScoreKeys[2], 0));
        highscore4.setText("Highscore 4: " + pref.getInt(BubbleScoreKeys[3], 0));
        highscore5.setText("Highscore 5: " + pref.getInt(BubbleScoreKeys[4], 0));

        dialogBuilder.setTitle("High Scores");
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
