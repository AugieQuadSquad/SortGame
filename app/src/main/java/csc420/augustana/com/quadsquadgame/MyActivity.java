package csc420.augustana.com.quadsquadgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MyActivity extends AppCompatActivity {

    //test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        button1.setOnClickListener(buttonListener1);
        button2.setOnClickListener(buttonListener2);
        button3.setOnClickListener(buttonListener3);
    }

    private final View.OnClickListener buttonListener1 = new View.OnClickListener() {
        public void onClick(View btn) {
            Intent tutorialOption = new Intent(MyActivity.this, TutorialOption.class);
            tutorialOption.putExtra("button", "1");
            startActivity(tutorialOption);
        }
    };

    private final View.OnClickListener buttonListener2 = new View.OnClickListener() {
        public void onClick(View btn) {
            Intent tutorialOption = new Intent(MyActivity.this, TutorialOption.class);
            tutorialOption.putExtra("button", "2");
            startActivity(tutorialOption);
        }
    };

    private final View.OnClickListener buttonListener3 = new View.OnClickListener() {
        public void onClick(View btn) {
            Intent tutorialOption = new Intent(MyActivity.this, TutorialOption.class);
            tutorialOption.putExtra("button", "3");
            startActivity(tutorialOption);
        }
    };
}
