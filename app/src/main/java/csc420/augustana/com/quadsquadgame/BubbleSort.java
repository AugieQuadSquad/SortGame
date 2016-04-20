package csc420.augustana.com.quadsquadgame;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Arrays;
import android.view.View;

public class BubbleSort extends AppCompatActivity {

    public static TextView[] items = new TextView[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sort);
        ItemDatabase.setValues();

        TextView item1 = (TextView) findViewById(R.id.item1);
        items[0] = item1;
        item1.setTag(R.drawable.box_one);
        TextView item2 = (TextView) findViewById(R.id.item2);
        items[1] = item2;
        item2.setTag(R.drawable.box_two);
        TextView item3 = (TextView) findViewById(R.id.item3);
        items[2] = item3;
        item3.setTag(R.drawable.box_three);
        TextView item4 = (TextView) findViewById(R.id.item4);
        items[3] = item4;
        item4.setTag(R.drawable.box_four);
        TextView item5 = (TextView) findViewById(R.id.item5);
        items[4] = item5;
        item5.setTag(R.drawable.box_five);
        TextView item6 = (TextView) findViewById(R.id.item6);
        items[5] = item6;
        item6.setTag(R.drawable.box_six);
        TextView item7 = (TextView) findViewById(R.id.item7);
        items[6] = item7;
        item7.setTag(R.drawable.box_seven);
        TextView item8 = (TextView) findViewById(R.id.item8);
        items[7] = item8;
        item8.setTag(R.drawable.box_eight);

        for(int i = 0; i < items.length; i++){
            items[i].setText(ItemDatabase.value[i] + "");
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
    }


}
