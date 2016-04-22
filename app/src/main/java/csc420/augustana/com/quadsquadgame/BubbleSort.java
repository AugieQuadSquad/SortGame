package csc420.augustana.com.quadsquadgame;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import android.view.View;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class BubbleSort extends AppCompatActivity {

    public static TextView[] items = new TextView[8];
    TextView clicked1st = null;
    Animation animAlpha;
    int totalCount;
    int moveCount;
    int currentMove;
    Pairs[] pairs;
    int currentGame;

    TextView item1;
    TextView item2;
    TextView item3;
    TextView item4;
    TextView item5;
    TextView item6;
    TextView item7;
    TextView item8;
    Button hint;
    Button test;
    Button reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sort);
        ItemDatabase.setValues();
        totalCount = 0;
        currentMove = 0;
        currentGame = 0;

        hint = (Button) findViewById(R.id.hint);
        test = (Button) findViewById(R.id.test);
        reset = (Button) findViewById(R.id.resetButton);


        item1 = (TextView) findViewById(R.id.item1);
        items[0] = item1;
        item1.setTag(R.drawable.box_one);
        item2 = (TextView) findViewById(R.id.item2);
        items[1] = item2;
        item2.setTag(R.drawable.box_two);
        item3 = (TextView) findViewById(R.id.item3);
        items[2] = item3;
        item3.setTag(R.drawable.box_three);
        item4 = (TextView) findViewById(R.id.item4);
        items[3] = item4;
        item4.setTag(R.drawable.box_four);
        item5 = (TextView) findViewById(R.id.item5);
        items[4] = item5;
        item5.setTag(R.drawable.box_five);
        item6 = (TextView) findViewById(R.id.item6);
        items[5] = item6;
        item6.setTag(R.drawable.box_six);
        item7 = (TextView) findViewById(R.id.item7);
        items[6] = item7;
        item7.setTag(R.drawable.box_seven);
        item8 = (TextView) findViewById(R.id.item8);
        items[7] = item8;
        item8.setTag(R.drawable.box_eight);
        totalCount = 8;

        animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

        for (int i = 0; i < items.length; i++) {
            items[i].setText(ItemDatabase.value[i] + "");
            items[i].setOnClickListener(myClickListener);
        }
        if(currentGame == 0){
            pairs = bubbleSort();
        }


    }

    private static void swap(TextView item1TV, TextView item2TV) {
        CharSequence tempText = item1TV.getText();
        int tempBackground = (int) item1TV.getTag();
        item1TV.setText(item2TV.getText());
        item1TV.setBackgroundResource((int) item2TV.getTag());
        item2TV.setText(tempText);
        item2TV.setBackgroundResource(tempBackground);
        item1TV.setTag(item2TV.getTag());
        item2TV.setTag(tempBackground);
        YoYo.with(Techniques.SlideInDown)
                .duration(700)
                .playOn(item1TV);
        YoYo.with(Techniques.SlideInDown)
                .duration(700)
                .playOn(item2TV);
    }

    public void testSwap(View view) {
        int[] current = buildArray();
        if(isSorted(current)){
            displayMessage("You Win!");
        } else{
            displayMessage("Keep Trying!");
        }
    }

    public void hint(View view){
        swap(items[pairs[currentMove].getFirst()], items[pairs[currentMove].getSecond()]);
        currentMove++;
    }

    private View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (clicked1st == null) {
                clicked1st = (TextView) v;
                v.startAnimation(animAlpha);
            } else {
                if(isNextMove(clicked1st, (TextView) v)){
                    swap(clicked1st, (TextView) v);
                    clicked1st = null;
                    currentMove++;
                } else {
                    displayMessage("Wrong move");
                    clicked1st = null;
                }

            }

        }
    };

    public Pairs[] bubbleSort() {
        Pairs[] pairsArray = new Pairs[30];
        int pairsCount = 0;
        int[] tempArray = new int[totalCount];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = ItemDatabase.value[i];
        }
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < tempArray.length - 1; i++) {
                if (tempArray[i] > tempArray[i + 1]) {
                    Pairs tempPair = new Pairs(i, i + 1);
                    pairsArray[pairsCount] = tempPair;
                    pairsCount++;
                    int tempInt = tempArray[i];
                    tempArray[i] = tempArray[i + 1];
                    tempArray[i + 1] = tempInt;
                    swapped = true;
                    moveCount++;
                }
            }
        }
        return pairsArray;
    }

    public void displayMessage(String string) {
        Toast.makeText(this, (string), Toast.LENGTH_SHORT).show();
    }

    public boolean isSorted(int[] outsideArray){
        int[] temp = new int[totalCount];
        for(int i = 0; i < temp.length; i++){
            temp[i] = ItemDatabase.value[i];
        }
        Arrays.sort(temp);
        for(int i = 0; i < totalCount; i++){
            if(temp[i] != outsideArray[i]){
                return false;
            }
        }
        return true;
    }

    public int[] buildArray(){
        int[] array = new int[totalCount];
        for(int i = 0; i < totalCount; i++){
            array[i] = Integer.parseInt(items[i].getText().toString());
        }
        return array;
    }

    public boolean isNextMove(TextView tv1, TextView tv2){
        int indexOf1 = Arrays.asList(items).indexOf(tv1);
        int indexOf2 = Arrays.asList(items).indexOf(tv2);
        if(indexOf1 == pairs[currentMove].getFirst() && indexOf2 == pairs[currentMove].getSecond()){
            return true;
        }else{
            return false;
        }
    }

    public void reset(View view){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void Tutorial(View view){
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); //Delay is in milliseconds

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);

        sequence.setConfig(config);

        sequence.addSequenceItem(item1, "Click the next button to scroll through the items to see their values.", "Next");

        sequence.addSequenceItem(item2, "Drag the items to the bottom...", "Next");

        sequence.addSequenceItem(hint, "In smallest to largest order, based on the values.", "Next");

        sequence.addSequenceItem(test, "If you mess up, don't worry! Just reset the game and try again.", "Next");

        sequence.addSequenceItem(reset, "If you think you've got it, find out with the test button!", "Close");

        sequence.start();
    }

}
