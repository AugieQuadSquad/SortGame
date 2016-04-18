package csc420.augustana.com.quadsquadgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class InsertionSortTutorial extends AppCompatActivity {

    private int counter = 0;
    private Button next;
    private Button reset;
    private Button test;
    private ImageView image1;

    private LinearLayout container1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertion_sort_tutorial);

        next = (Button) findViewById(R.id.nextbutton);
        reset = (Button) findViewById(R.id.resetButton);
        test = (Button) findViewById(R.id.test);

        image1 = (ImageView) findViewById(R.id.box_view1);

        container1 = (LinearLayout) findViewById(R.id.container_one);

        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); //Delay is in milliseconds

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        sequence.setConfig(config);

        sequence.addSequenceItem(next, "Click the next button to scroll through the items to see their values", "Got it");

        sequence.addSequenceItem(image1, "Drag the items to the bottom", "Got It");

        sequence.addSequenceItem(container1, "In smallest to largest order, based on the values", "Got It");

        sequence.addSequenceItem(reset, "If you mess up, don't worry! Just reset the game and try again", "Got It");

        sequence.addSequenceItem(test, "If you think you've got it, find out with the test button!", "Close");

        sequence.start();
    }
}
