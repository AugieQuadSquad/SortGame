package csc420.augustana.com.quadsquadgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * The MainMenu class is a holder for buttons used to call the gameBoard activity
 *
 * @author Devon White, Michael Currie, Luke Currie, Catherine Cross
 * @since 4/10/2016
 */
public class MainMenu extends AppCompatActivity {
    private static final int BUBBLE_SORT = 0;
    private static final int INSERTION_SORT = 1;
    private static final int SELECTION_SORT = 2;
    private static final int ABOUT_ME = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button bubbleButton = (Button) findViewById(R.id.bubbleSort);
        Button insertionButton = (Button) findViewById(R.id.insertionSort);
        Button selectionButton = (Button) findViewById(R.id.selectionSort);
        Button button4 = (Button) findViewById(R.id.aboutMe);

        bubbleButton.setOnClickListener(bubbleInstructions);
        insertionButton.setOnClickListener(insertionInstructions);
        selectionButton.setOnClickListener(selectionInstructions);
        button4.setOnClickListener(instructions4);
    }

    private final View.OnClickListener bubbleInstructions = new View.OnClickListener() {
        public void onClick(View btn) {
            Intent tutorialOption = new Intent(MainMenu.this, Instructions.class);
            tutorialOption.putExtra("game", BUBBLE_SORT);
            startActivity(tutorialOption);
        }
    };

    private final View.OnClickListener insertionInstructions = new View.OnClickListener() {
        public void onClick(View btn) {
            Intent tutorialOption = new Intent(MainMenu.this, Instructions.class);
            tutorialOption.putExtra("game", INSERTION_SORT);
            startActivity(tutorialOption);
        }
    };

    private final View.OnClickListener selectionInstructions = new View.OnClickListener() {
        public void onClick(View btn) {
            Intent tutorialOption = new Intent(MainMenu.this, Instructions.class);
            tutorialOption.putExtra("game", SELECTION_SORT);
            startActivity(tutorialOption);
        }
    };

    private final View.OnClickListener instructions4 = new View.OnClickListener() {
        public void onClick(View btn) {
            Intent tutorialOption = new Intent(MainMenu.this, Instructions.class);
            tutorialOption.putExtra("game", ABOUT_ME);
            startActivity(tutorialOption);
        }
    };
}
