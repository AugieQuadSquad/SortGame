package csc420.augustana.com.quadsquadgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TutorialOption extends AppCompatActivity {
    private static final int BUBBLE_SORT = 0;
    private static final int INSERTION_SORT = 1;
    private static final int SELECTION_SORT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_option);

        //PASS DATA
        Intent intent = getIntent();
        String str = intent.getStringExtra("button");
        int button = Integer.parseInt(str);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.LinearLayout);
        TextView text = (TextView) findViewById(R.id.textView3);
        if (button == INSERTION_SORT) {
            text.setText("Insertion Sort");
        } else if (button == SELECTION_SORT) {
            text.setText("Selection Sort");
        } else if (button == BUBBLE_SORT) {
            text.setText("Bubble Sort");
        }
    }
}
