package csc420.augustana.com.quadsquadgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Instructions extends AppCompatActivity {
    private final int BUBBLESORT = 0;
    private final int INSERTIONSORT = 1;

    int currentGame = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Intent intent = getIntent();
        currentGame = intent.getIntExtra("game", -1);

        TextView temp = (TextView) findViewById(R.id.instructionTextView);

        if(currentGame == 0 ){
            temp.setText(R.string.bubblesortinstructions);
        } else if(currentGame == 1){

        } else if(currentGame == BUBBLESORT){

        } else {

        }
    }
}
