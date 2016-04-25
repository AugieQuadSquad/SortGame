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
    int moveCount;
    int currentMove;
    Pairs[] pairs;
    int currentGame;
    String secondsRemaining;
    boolean isCanceled;
    AlertDialog alert1;
    int hintCount;
    int falseTests;
    int totalScore;
    int hintWeight = 0;
    int testWeight = 2;
    int wrongMoves;
    int wrongMovesWeight = 1;

    TextView item1;
    TextView item2;
    TextView item3;
    TextView item4;
    TextView item5;
    TextView item6;
    TextView item7;
    TextView item8;
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
    String[] BubbleScore;
    int[] BubbleScoresValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sort);
        ItemDatabase.setValues();
        totalCount = 0;
        currentMove = 0;
        currentGame = 0;
        isCanceled = false;

        BubbleScoresValues = new int[5];
        BubbleScore = new String[5];

        for(int i = 0; i < 5; i++){
            BubbleScore[i] = ItemDatabase.BubbleScore[i];
        }


        // added by Catherine 4/24/2016
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();
/*        editor.putInt(BubbleScore1, 0);
        editor.apply();*/

        hint = (Button) findViewById(R.id.hint);
        test = (Button) findViewById(R.id.test);
        reset = (Button) findViewById(R.id.resetButton);

        myTimer = (TextView) findViewById(R.id.timer);

        /*AlertDialog.Builder builder1 = new AlertDialog.Builder(this)
                .setTitle("Game Over!")
                .setMessage("This is where your score is going to go")
                .setCancelable(false)
                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent tutorialOption = new Intent(BubbleSort.this, BubbleSort.class);
                        tutorialOption.putExtra("button", "2");
                        startActivity(tutorialOption);
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        Intent tutorialOption = new Intent(BubbleSort.this, MyActivity.class);
                        startActivity(tutorialOption);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);

        alert1 = builder1.create();*/

        item1 = (TextView) findViewById(R.id.item1);
        items[0] = item1;
        item1.setTag(R.drawable.box_one);
        item2 = (TextView) findViewById(R.id.item2);
        items[1] = item2;
        item2.setTag(R.drawable.box_two);
        item3 = (TextView) findViewById(R.id.item3);
        items[2] = item3;
        item3.setTag(R.drawable.box_three);
        item4 = (TextView) findViewById(R.id.item4);
        items[3] = item4;
        item4.setTag(R.drawable.box_four);
        item5 = (TextView) findViewById(R.id.item5);
        items[4] = item5;
        item5.setTag(R.drawable.box_five);
        item6 = (TextView) findViewById(R.id.item6);
        items[5] = item6;
        item6.setTag(R.drawable.box_six);
        item7 = (TextView) findViewById(R.id.item7);
        items[6] = item7;
        item7.setTag(R.drawable.box_seven);
        item8 = (TextView) findViewById(R.id.item8);
        items[7] = item8;
        item8.setTag(R.drawable.box_eight);
        totalCount = 8;

        animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

        for (int i = 0; i < items.length; i++) {
            items[i].setText(ItemDatabase.value[i] + "");
            items[i].setOnClickListener(myClickListener);
        }
        if(currentGame == 0){
            pairs = bubbleSort();
        }

        new CountDownTimer(50000, 1000) {

            public void onTick(long millisUntilFinished) {
                if(!isCanceled) {
                    myTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                    secondsRemaining = "" + millisUntilFinished / 1000;
                } else{
                    cancel();
                }
            }

            public void onFinish() {
                myTimer.setText("done!");
                if(isSorted(buildArray())){
                    secondsRemaining = 0+"";
                } else {
                    alert1.setMessage("You ran out of time!");
                    alert1.show();
                }
            }
        }.start();
    }

    private static void swap(TextView item1TV, TextView item2TV) {
        CharSequence tempText = item1TV.getText();
        int tempBackground = (int) item1TV.getTag();
        item1TV.setText(item2TV.getText());
        item1TV.setBackgroundResource((int) item2TV.getTag());
        item2TV.setText(tempText);
        item2TV.setBackgroundResource(tempBackground);
        item1TV.setTag(item2TV.getTag());
        item2TV.setTag(tempBackground);
        YoYo.with(Techniques.SlideInDown)
                .duration(700)
                .playOn(item1TV);
        YoYo.with(Techniques.SlideInDown)
                .duration(700)
                .playOn(item2TV);
    }

    public void testSwap(View view) {
        int[] current = buildArray();
        if(isSorted(current)){
            isCanceled = true;
            getTotalScore();
        } else{
            falseTests++;
            displayMessage("Keep Trying!");
        }
    }

    public int getTotalScore(){
        totalScore = Integer.parseInt(secondsRemaining) - hintWeight*hintCount - testWeight*falseTests - wrongMovesWeight*wrongMoves;
        if(totalScore<0){
            totalScore = 0;
        }
        boolean bool = false;

        // code added by Catherine for internal storage
        // code adopted from http://stackoverflow.com/questions/23024831/android-shared-preferences-example
        for(int i = 0; i < 5; i++){
            if(pref.getInt(BubbleScore[i], 0) < totalScore&& bool == false){
                for(int j = 4; j > i; j--){
                    editor.putInt(BubbleScore[j], BubbleScoresValues[j-1]);
                }
                editor.putInt(BubbleScore[i], totalScore);
                displayMessage("Congratulations! New High Score!");
                BubbleScoresValues[4] = totalScore;
                Arrays.sort(BubbleScoresValues);
                bool = true;
            }
        }
        editor.apply();


        /*alert1.setMessage("Time Remaining: " + secondsRemaining + "\nNumber of Hints used: " + hintCount + "\nNumber of incorrect tests: " + falseTests
                + "\nNumber of Wrong Moves: " + wrongMoves + "\nTotal Score: " + totalScore +"\nHigh Score: " + pref.getInt(BubbleScore1, 0));
        alert1.show();*/
        showDialog();
        return totalScore;
    }

    public void hint(View view){
        if(!isSorted(buildArray())){
            hintCount++;
            swap(items[pairs[currentMove].getFirst()], items[pairs[currentMove].getSecond()]);
            currentMove++;
        }else{
            hintCount++;
            displayMessage("No more moves!");
        }
    }

    private View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (clicked1st == null) {
                clicked1st = (TextView) v;
                clicked1st.setTextSize(45);
                v.startAnimation(animAlpha);
            } else {
                if(clicked1st.equals(v)){
                    clicked1st.setTextSize(30);
                    clicked1st = null;
                } else if(isNextMove(clicked1st, (TextView) v)){
                    swap(clicked1st, (TextView) v);
                    clicked1st.setTextSize(30);
                    clicked1st = null;
                    currentMove++;
                } else {
                    wrongMoves++;
                    displayMessage("Wrong move");
                    clicked1st.setTextSize(30);
                    clicked1st = null;
                }

            }

        }
    };

    public Pairs[] bubbleSort() {
        Pairs[] pairsArray = new Pairs[30];
        int pairsCount = 0;
        int[] tempArray = new int[totalCount];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = ItemDatabase.value[i];
        }
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < tempArray.length - 1; i++) {
                if (tempArray[i] > tempArray[i + 1]) {
                    Pairs tempPair = new Pairs(i, i + 1);
                    pairsArray[pairsCount] = tempPair;
                    pairsCount++;
                    int tempInt = tempArray[i];
                    tempArray[i] = tempArray[i + 1];
                    tempArray[i + 1] = tempInt;
                    swapped = true;
                    moveCount++;
                }
            }
        }
        return pairsArray;
    }

    public void displayMessage(String string) {
        Toast.makeText(this, (string), Toast.LENGTH_SHORT).show();
    }

    public boolean isSorted(int[] outsideArray){
        int[] temp = new int[totalCount];
        for(int i = 0; i < temp.length; i++){
            temp[i] = ItemDatabase.value[i];
        }
        Arrays.sort(temp);
        for(int i = 0; i < totalCount; i++){
            if(temp[i] != outsideArray[i]){
                return false;
            }
        }
        return true;
    }

    public int[] buildArray(){
        int[] array = new int[totalCount];
        for(int i = 0; i < totalCount; i++){
            array[i] = Integer.parseInt(items[i].getText().toString());
        }
        return array;
    }

    public boolean isNextMove(TextView tv1, TextView tv2){
        int indexOf1 = Arrays.asList(items).indexOf(tv1);
        int indexOf2 = Arrays.asList(items).indexOf(tv2);
        if(indexOf1 == pairs[currentMove].getFirst() && indexOf2 == pairs[currentMove].getSecond()){
            return true;
        }else{
            return false;
        }
    }

    public void reset(View view){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void Tutorial(View view){
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); //Delay is in milliseconds

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        sequence.setConfig(config);

        sequence.addSequenceItem(item1, "Tap the first item that you would like to switch", "Next");

        sequence.addSequenceItem(item2, "and switch it by tapping the second item.", "Next");

        sequence.addSequenceItem(hint, "If you get stuck, use a hint! It will complete the next move for you.", "Next");

        sequence.addSequenceItem(reset, "If you mess up, don't worry! Just reset the game and try again.", "Next");

        sequence.addSequenceItem(test, "If you think you've got it, find out with the test button!", "Close");

        sequence.start();
    }

    public void showDialog(){
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
        hintsUsed.setText("Number of Hints used: " + hintCount);
        incorrectTests.setText("Number of incorrect tests: " + falseTests);
        wrongMove.setText("Number of Wrong Moves: " + wrongMoves);
        totalScores.setText("Total Score: " + totalScore);

        dialogBuilder.setTitle("Game Over!");
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public void playAgain(View view){
        Intent tutorialOption = new Intent(BubbleSort.this, BubbleSort.class);
        tutorialOption.putExtra("button", "2");
        startActivity(tutorialOption);
    }

    public void quit(View view){
        Intent tutorialOption = new Intent(BubbleSort.this, MyActivity.class);
        startActivity(tutorialOption);
    }

    public void highScores(View view){
        for(int i = 0; i < 5; i++){
            editor.putInt(BubbleScore[i], totalScore);
            editor.apply();
        }
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.scores_dialog, null);
        dialogBuilder.setView(dialogView);

        highscore1 = (TextView) dialogView.findViewById(R.id.highscore1);
        highscore2 = (TextView) dialogView.findViewById(R.id.highscore2);
        highscore3 = (TextView) dialogView.findViewById(R.id.highscore3);
        highscore4 = (TextView) dialogView.findViewById(R.id.highscore4);
        highscore5 = (TextView) dialogView.findViewById(R.id.highscore5);

        highscore1.setText("Highscore 1: " + pref.getInt(BubbleScore[0], 0));
        highscore2.setText("Highscore 2: " + pref.getInt(BubbleScore[1], 0));
        highscore3.setText("Highscore 3: " + pref.getInt(BubbleScore[2], 0));
        highscore4.setText("Highscore 4: " + pref.getInt(BubbleScore[3], 0));
        highscore5.setText("Highscore 5: " + pref.getInt(BubbleScore[4], 0));

        dialogBuilder.setTitle("High Scores");
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
