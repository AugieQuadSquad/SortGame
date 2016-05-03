package csc420.augustana.com.quadsquadgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Instructions extends AppCompatActivity {

    int currentGame = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Intent intent = getIntent();
        currentGame = intent.getIntExtra("game", -1);

        if(currentGame == 0 ){

        } else if(currentGame == 1){

        } else if(currentGame == 2){

        } else {

        }
    }
}
