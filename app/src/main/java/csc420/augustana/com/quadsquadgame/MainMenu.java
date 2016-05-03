package csc420.augustana.com.quadsquadgame;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button button1 = (Button) findViewById(R.id.test);
        Button button2 = (Button) findViewById(R.id.tutorial);
        Button button3 = (Button) findViewById(R.id.hint);

        button1.setOnClickListener(instructions1);
        button2.setOnClickListener(instructions2);
        button3.setOnClickListener(instructions3);
    }

    private final View.OnClickListener instructions1 = new View.OnClickListener() {
        public void onClick(View btn) {
            Intent tutorialOption = new Intent(MainMenu.this, Instructions.class);
            tutorialOption.putExtra("game", 0);
            startActivity(tutorialOption);
        }
    };

    private final View.OnClickListener instructions2 = new View.OnClickListener() {
        public void onClick(View btn) {
            Intent tutorialOption = new Intent(MainMenu.this, Instructions.class);
            tutorialOption.putExtra("game", 1);
            startActivity(tutorialOption);
        }
    };

    private final View.OnClickListener instructions3 = new View.OnClickListener() {
        public void onClick(View btn) {
            Intent tutorialOption = new Intent(MainMenu.this, Instructions.class);
            tutorialOption.putExtra("game", 2);
            startActivity(tutorialOption);
        }
    };
}
