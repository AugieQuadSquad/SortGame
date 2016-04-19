package csc420.augustana.com.quadsquadgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BubbleSort extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sort);
        ItemDatabase.setValues();

        TextView[] items = new TextView[8];

        TextView item1 = (TextView) findViewById(R.id.item1);
        items[0] = item1;
        TextView item2 = (TextView) findViewById(R.id.item2);
        items[1] = item2;
        TextView item3 = (TextView) findViewById(R.id.item3);
        items[2] = item3;
        TextView item4 = (TextView) findViewById(R.id.item4);
        items[3] = item4;
        TextView item5 = (TextView) findViewById(R.id.item5);
        items[4] = item5;
        TextView item6 = (TextView) findViewById(R.id.item6);
        items[5] = item6;
        TextView item7 = (TextView) findViewById(R.id.item7);
        items[6] = item7;
        TextView item8 = (TextView) findViewById(R.id.item8);
        items[7] = item8;

        for(int i = 0; i < items.length; i++){
            items[i].setText(ItemDatabase.value[i] + "");
        }
    }
}
