package csc420.augustana.com.quadsquadgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class Instructions extends AppCompatActivity {

    int currentGame = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Intent intent = getIntent();
        currentGame = intent.getIntExtra("game", -1);

        setText();
    }

    public void toGame(View view){
        Intent tutorialOption = new Intent(Instructions.this, GameBoard.class);
        tutorialOption.putExtra("game", currentGame);
        startActivity(tutorialOption);
    }

    public void setText(){
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        if(currentGame == 0 ){
            textView.setText(R.string.bubbleSortInstructions);
        } else if(currentGame == 1){
            textView.setText(R.string.insertionSortInstructions);
        } else if(currentGame == 2){
            textView.setText("Other Sort method instructions go here");
        } else {
            textView.setText("Error: Invalid Game number!");
        }
    }
}
