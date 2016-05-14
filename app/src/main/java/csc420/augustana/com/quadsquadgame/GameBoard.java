package csc420.augustana.com.quadsquadgame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


/**
 * This class runs the main screen during the game. It is expandable for any sort
 * by following a pairs list of moves that determine the way the student should
 * be sorting the elements in order to win the game. It also populates the original
 * list and displays the messages when the player wins or loses the game.
 *
 * @author Devon White, Michael Currie, Luke Currie, Catherine Cross
 * @since 4/9/2016
 */

public class GameBoard extends AppCompatActivity {
    private static final int BUBBLE_SORT = 0;
    private static final int INSERTION_SORT = 1;
    private static final int SELECTION_SORT = 2;

    public static TextView[] items = new TextView[8];
    TextView clicked1st = null;
    Animation animAlpha;
    public static int totalCount;
    public static int currentMove;
    public static int currentGame;
    public static int totalScore;
    private static Context context;
    private static Resources res;

    Button hint;
    Button test;
    Button reset;

    int[] arrayDisplayed;
    Random rand;

    public static CircularFillableLoaders circularFillableLoaders;

    public static List<Pairs> pairsList;

    /**
     * This method is launched when the activity begins and sets the
     * values for the original counts and initializes the text views and buttons.
     * It also starts the timer and sets the list for the sort depending on the
     * game number that was passed into the activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        Intent intent = getIntent();
        currentGame = intent.getIntExtra("game", -1);

        yourNonActivityClass(this);
        res = getResources();

        instantiateArray();

        totalCount = 0;
        currentMove = 0;

        hint = (Button) findViewById(R.id.hint);
        test = (Button) findViewById(R.id.test);
        reset = (Button) findViewById(R.id.resetButton);

        /*builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);*/

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
        for (int i = 0; i < items.length; i++) {
            items[i].setText(arrayDisplayed[i] + "");
            items[i].setOnClickListener(myClickListener);
        }

        circularFillableLoaders = (CircularFillableLoaders) findViewById(R.id.circularFillableLoaders);

        // SOURCE: http://android-er.blogspot.com/2014/09/apply-animation-on-buttons-to-start.html
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

        // this initializes the order of swaps for sort
        // Expandable to use the same activity for all the sorts
        if (currentGame == BUBBLE_SORT) {
            pairsList = BubbleSortModel.getSwapSequence(buildArray());
        } else if (currentGame == INSERTION_SORT) {
            pairsList = InsertionSortModel.getSwapSequence(buildArray());
        } else if (currentGame == SELECTION_SORT) {
            pairsList = SelectionSortModel.getSwapSequence(buildArray());
        } else {
            displayMessage("ERROR: Invalid Game Number");
        }

        HighScores.resetScores();

        Timer timer = new Timer();
        timer.start();
    }

    /**
     * This method initializes the original array of objects to create
     * random numbers on the text view for the player to sort.
     */
    public void instantiateArray() {
        arrayDisplayed = new int[8];
        rand = new Random();
        for (int i = 0; i < arrayDisplayed.length; i++) {
            arrayDisplayed[i] = rand.nextInt(50) + 1;
        }
    }

    /**
     * This method updates the circular loader.
     * Code adopted from https://github.com/lopspower/CircularFillableLoaders
     *
     * @param update accepts the int to change the timer graphic
     */
    public static void setTimerGraphic(int update) {
        circularFillableLoaders.setProgress(update);
    }

    /**
     * This method sets the Activity context to the param context
     *
     * @param context accepts a context as the parameter
     */
    public void yourNonActivityClass(Context context) {
        this.context = context;
    }

    /**
     * This method accepts two textViews and then changes the background
     * and the text in order to swap the two elements.
     * It also uses an external library in order to do the animation
     * of the nodes bouncing up and down.
     *
     * @param item1TV
     * @param item2TV
     */
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
        // SOURCE: https://github.com/daimajia/AndroidViewAnimations
        YoYo.with(Techniques.SlideInDown)
                .duration(700)
                .playOn(item1TV);
        YoYo.with(Techniques.SlideInDown)
                .duration(700)
                .playOn(item2TV);
    }

    /**
     * This method tests to see if the list finished being sorted. If it is then
     * it cancels the timer and gets the total score. Otherwise, it displays a message
     * that says Keep Trying
     *
     * @param view
     */
    public void testSwap(View view) {
        int[] current = buildArray();
        if (isSorted(current)) {
            Timer.cancel();
            getTotalScore();
        } else {
            HighScores.addWrongTest();
            displayMessage("Keep Trying!");
        }
    }

    /**
     * This method gets the total score by calling the HighScores class. If the new totalScore
     * is better than the previous high score, then the sharedPreferences are changed by calling
     * the SaveSharedPreference class. It then also displays a toast if a new high score
     * was reached.
     *
     * @return Lint This returns an int of the TotalScore
     */
    public static int getTotalScore() {
        totalScore = HighScores.getTotalScore(Integer.parseInt(Timer.getSecondsRemaining()));
        Boolean newHighScore = false;
        if (currentGame == BUBBLE_SORT) {
            if (totalScore > SaveSharedPreference.getPrefBubbleHighscore(context)) {
                newHighScore = true;
                SaveSharedPreference.setPrefBubbleHighscore(context, totalScore);
            }
        } else if (currentGame == INSERTION_SORT) {
            if (totalScore > SaveSharedPreference.getPrefInsertionHighscore(context)) {
                newHighScore = true;
                SaveSharedPreference.setPrefInsertionHighscore(context, totalScore);
            }
        } else if (currentGame == SELECTION_SORT) {
            if (totalScore > SaveSharedPreference.getPrefSelectionHighscore(context)) {
                newHighScore = true;
                SaveSharedPreference.setPrefSelectionHighscore(context, totalScore);
            }
        }

        if (newHighScore == true) {
            Toast.makeText(context, "New High Score!", Toast.LENGTH_SHORT).show();
        }
        showDialog(true);
        return totalScore;
    }

    /**
     * This method swaps the next two nodes to be swapped according to the
     * PairsArray of moves
     *
     * @param view
     */
    public void hint(View view) {
        if (!isSorted(buildArray())) {
            HighScores.addHint();
            // CR change
            swap(items[pairsList.get(currentMove).getFirst()], items[pairsList.get(currentMove).getSecond()]);
            currentMove++;
        } else {
            HighScores.addHint();
            displayMessage("No more moves!");
        }
    }

    /**
     * This method sets the onClickListener for the buttons to keep track of how many
     * times the user uses the hints and test buttons
     */
    private View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (clicked1st == null) {
                clicked1st = (TextView) v;
                clicked1st.setTextSize(45);
                // SOURCE: http://android-er.blogspot.com/2014/09/apply-animation-on-buttons-to-start.html
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
                    HighScores.addWrongMove();
                    displayMessage("Wrong move");
                    clicked1st.setTextSize(30);
                    clicked1st = null;
                }

            }

        }
    };

    /**
     * This method displays the string parameter as a toast.
     *
     * @param string
     */
    public void displayMessage(String string) {
        Toast.makeText(this, (string), Toast.LENGTH_SHORT).show();
    }

    /**
     * This method checks to see if the list is sorted by comparing the values
     * of neighboring text views. It returns true if sorted correctly, otherwise
     * returns false.
     *
     * @param outsideArray
     * @return Boolean
     */
    public static boolean isSorted(int[] outsideArray) {
        boolean sorted = true;
        for (int i = 0; i < outsideArray.length - 1; i++) {
            if (outsideArray[i] > outsideArray[i + 1]) {
                sorted = false;
            }
        }
        return sorted;
    }

    /**
     * This method builds the array by getting the text in the TextView in the
     * current order on the screen that the user would be seeing.
     *
     * @return int[]
     */
    public static int[] buildArray() {
        int[] array = new int[totalCount];
        for (int i = 0; i < totalCount; i++) {
            array[i] = Integer.parseInt(items[i].getText().toString());
        }
        return array;
    }

    /**
     * This method checks to see if the swap was the correct next move for the
     * user according to the pairs list of the current sort.
     *
     * @param tv1 textView1
     *            tv2 textView2
     * @return boolean returns true if correct next move, false otherwise
     */
    public boolean isNextMove(TextView tv1, TextView tv2) {
        int indexOf1 = Arrays.asList(items).indexOf(tv1);
        int indexOf2 = Arrays.asList(items).indexOf(tv2);
        if (indexOf1 == pairsList.get(currentMove).getFirst() && indexOf2 == pairsList.get(currentMove).getSecond()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method resets the gameboard by adding a point to the scores class for reset, cancels
     * the timer and then restarts the activity.
     *
     * @param view
     */
    public void reset(View view) {
        HighScores.addResetClick();
        Intent intent = getIntent();
        finish();
        Timer.cancel();
        startActivity(intent);
    }

    /**
     * This method starts the tutorial for how to work the view, using the
     * external library sourced below:
     * <p/>
     * SOURCE: https://github.com/deano2390/MaterialShowcaseView
     *
     * @param view
     */
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

    /**
     * This method creates the dialog builder to display the final scores once the
     * game is finished by pulling the scores from the HighScores class and setting
     * the Text fields in the dialog layout.
     *
     * @param timeOut boolean that is true if the player ran out of time and false otherwise
     */
    public static void showDialog(boolean timeOut) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final TextView timeRemaining = (TextView) dialogView.findViewById(R.id.timeRemaining);
        final TextView hintsUsed = (TextView) dialogView.findViewById(R.id.hintsUsed);
        final TextView incorrectTests = (TextView) dialogView.findViewById(R.id.incorrectTests);
        final TextView wrongMove = (TextView) dialogView.findViewById(R.id.wrongMoves);
        final TextView totalScores = (TextView) dialogView.findViewById(R.id.totalScore);

        String temp = res.getString(R.string.timeRemaining) + Timer.getSecondsRemaining();
        timeRemaining.setText(temp);
        temp = res.getString(R.string.hintsUsed) + HighScores.getHintsCount();
        hintsUsed.setText(temp);
        temp = res.getString(R.string.tests) + HighScores.getWrongTestCount();
        incorrectTests.setText(temp);
        temp = res.getString(R.string.wrongMoves) + HighScores.getWrongMovesCount();
        wrongMove.setText(temp);
        temp = res.getString(R.string.score) + totalScore;
        totalScores.setText(temp);

        if (timeOut) {
            dialogBuilder.setTitle("You Win!");
        } else {
            dialogBuilder.setTitle("You Lose!");
        }
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    /**
     * This method restarts the activity and cancels the timer.
     *
     * @param view
     */
    public void playAgain(View view) {
        /*instantiateArray();*/
        Timer.cancel();
        Intent game = new Intent(GameBoard.this, GameBoard.class);
        game.putExtra("game", currentGame);
        startActivity(game);
        finish();
    }

    /**
     * This method cancels the timer, then it starts the activity over again
     * by passing the menu with the parameters of GameBoard and MainMenu
     *
     * @param view
     */
    public void quit(View view) {
        Intent menu = new Intent(GameBoard.this, MainMenu.class);
        Timer.cancel();
        startActivity(menu);
        finish();
    }

    /**
     * This method populates the highscores dialog which displays after you click
     * on HighScores after the game finishes. It also sets the buttons to play again or
     * quit to go back to the main menu.
     *
     * @param view
     */
    public void highScores(final View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setPositiveButton("Play Again?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
                playAgain(view);
            }
        });
        builder.setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                quit(view);
                dialog.cancel();
                finish();
            }
        });


        if (currentGame == BUBBLE_SORT) {
            builder.setTitle("Bubble Sort Highscore!");
            builder.setMessage(res.getString(R.string.hs1) + " " + SaveSharedPreference.getPrefBubbleHighscore(context));
        } else if (currentGame == INSERTION_SORT) {
            builder.setTitle("Insertion Sort Highscore!");
            builder.setMessage(res.getString(R.string.hs1) + " " + SaveSharedPreference.getPrefInsertionHighscore(context));
        } else if (currentGame == SELECTION_SORT) {
            builder.setTitle("Selection Sort Highscore!");
            builder.setMessage(res.getString(R.string.hs1) + " " + SaveSharedPreference.getPrefSelectionHighscore(context));
        } else {
            builder.setMessage("Invalid Game Number");
        }
        builder.create().show();

    }
}
