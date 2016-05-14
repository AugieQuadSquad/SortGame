package csc420.augustana.com.quadsquadgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * The Instructions class sets the text for the instruction screen which
 * displays before the game in order to teach the player how to complete
 * the sort, depending on which number is passed in as the current
 * game number.
 *
 * @author Devon White, Michael Currie, Luke Currie, Catherine Cross
 * @since 4/24/2016
 */
public class Instructions extends AppCompatActivity {
    private static final int BUBBLE_SORT = 0;
    private static final int INSERTION_SORT = 1;
    private static final int SELECTION_SORT = 2;
    private static final int ABOUT_ME = 3;
    int currentGame = -1;

    /**
     * This method starts the activity and calls the setText method
     *
     * @param savedInstanceState is the Bundle used for onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Intent intent = getIntent();
        currentGame = intent.getIntExtra("game", -1);

        setText();
    }

    /**
     * This method either starts the game or the about me section
     *
     * @param view is the View used to call this method from the layout
     */
    public void toGame(View view) {
        if (currentGame == ABOUT_ME) {
            Intent tutorialOption = new Intent(Instructions.this, MainMenu.class);
            tutorialOption.putExtra("game", currentGame);
            finish();
            startActivity(tutorialOption);
        } else {
            Intent tutorialOption = new Intent(Instructions.this, GameBoard.class);
            tutorialOption.putExtra("game", currentGame);
            finish();
            startActivity(tutorialOption);
        }
    }

    /**
     * This method sets the text to be displayed based on the currentGame pulled from
     * an intent above.
     */
    public void setText() {
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        if (currentGame == BUBBLE_SORT) {
            textView.setText(R.string.bubbleSortInstructions);
        } else if (currentGame == INSERTION_SORT) {
            textView.setText(R.string.insertionSortInstructions);
        } else if (currentGame == SELECTION_SORT) {
            textView.setText(R.string.selectionSortInstructions);
        } else if (currentGame == ABOUT_ME) {
            Button button = (Button) findViewById(R.id.toGame);
            button.setText("Main Menu");
            textView.setText(R.string.aboutMe);
        } else {
            textView.setText("Error: Invalid Game number!");
        }
    }
}
