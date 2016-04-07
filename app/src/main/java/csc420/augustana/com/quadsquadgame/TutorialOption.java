package csc420.augustana.com.quadsquadgame;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TutorialOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_option);

        //PASS DATA
        Intent intent = getIntent();
        String str = intent.getStringExtra("button");
        int button = Integer.parseInt(str);
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.LinearLayout);
        TextView text = (TextView) findViewById(R.id.textView3);
        if(button == 1){
            text.setText("Insertion Sort");
        } else if(button == 2) {
            text.setText("Quick Sort");
        } else if(button ==3) {
            text.setText("Bubble Sort");
        }
    }
}
