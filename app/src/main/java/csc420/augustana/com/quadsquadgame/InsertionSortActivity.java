package csc420.augustana.com.quadsquadgame;

// Source code based off http://androidsrc.net/android-view-drag-drop-functionality-sample-application/

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.plattysoft.leonids.ParticleSystem;

import java.util.Arrays;




public class InsertionSortActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    private BookItem[] books = new BookItem[15];
    int totalCount = 0;
    int currentBookNo = 0;
    private View[] upperViews = new View[15];
    private View[] lowerViews = new View[15];
    private int[] countUpper = new int[15];
    private int[] countLower = new int[15];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertion_sort);
        ItemDatabase.setValues();


        for (int i = 0; i < ItemDatabase.value.length; i++) {
            BookItem book = new BookItem(ItemDatabase.value[i], ItemDatabase.id[i], ItemDatabase.upperContainer[i]);
            book.setValue(ItemDatabase.value[i]);
            books[i] = book;
            totalCount++;
        }

        for (int i = 0; i < totalCount; i++) {
            upperViews[i] = findViewById(ItemDatabase.upperContainer[i]);
            lowerViews[i] = findViewById(ItemDatabase.lowerContainer[i]);
            upperViews[i].setOnDragListener(this);
            lowerViews[i].setOnDragListener(this);

            countLower[i] = 0;
            countUpper[i] = 1;
        }

        ImageView boxView1 = (ImageView) findViewById(R.id.box_view1);
        ImageView boxView2 = (ImageView) findViewById(R.id.box_view2);
        ImageView boxView3 = (ImageView) findViewById(R.id.box_view3);
        ImageView boxView4 = (ImageView) findViewById(R.id.box_view4);

        //The if statements here remove the "may produce java.lang.NullPointerException" error
        if (boxView1 != null) {
            boxView1.setOnTouchListener(this);
        }
        if (boxView2 != null) {
            boxView2.setOnTouchListener(this);
        }
        if (boxView3 != null) {
            boxView3.setOnTouchListener(this);
        }
        if (boxView4 != null) {
            boxView4.setOnTouchListener(this);
        }

        findViewById(R.id.top_view).setOnDragListener(this);
        findViewById(R.id.bottom_view).setOnDragListener(this);
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        // TODO Auto-generated method stub
        View view = (View) event.getLocalState();
        if (view.getId() != R.id.middle_btn) {
            if (event.getAction() == DragEvent.ACTION_DROP) {
                //we want to make sure it is dropped only to top and bottom parent view

                for (int i = 0; i < totalCount; i++) {
                    // if the view dropping into is a lower view, add to lower view count
                    if (v.getId() == lowerViews[i].getId() && countLower[i] < 1) {
                        ViewGroup source = (ViewGroup) view.getParent();
                        source.removeView(view);
                        countLower[i] = 1;

                        LinearLayout target = (LinearLayout) v;
                        target.addView(view);
                        if (source.getId() == upperViews[i].getId()) {
                            countUpper[i] = 0;
                        }
                    }
                    if (v.getId() == upperViews[i].getId() && countUpper[i] < 1) {
                        ViewGroup source = (ViewGroup) view.getParent();
                        source.removeView(view);

                        LinearLayout target = (LinearLayout) v;
                        target.addView(view);
                        countUpper[i] = 1;
                        if (source.getId() == lowerViews[i].getId()) {
                            countLower[i] = 0;
                        }
                    }
                }
            }

            //make view visible as we set visibility to invisible while starting drag
            view.setVisibility(View.VISIBLE);
        }
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(null, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);
            return true;
        }
        currentBookNo = 0;
        findViewById(R.id.book_cont1).setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
        findViewById(R.id.book_cont2).setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
        findViewById(R.id.book_cont3).setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
        findViewById(R.id.book_cont4).setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
        return false;
    }


    private boolean checkBoxViewEmpty(int sourceLL) {
        if (sourceLL == R.id.book_cont1) {
            countUpper[0]--;
        } else if (sourceLL == R.id.book_cont2) {
            countUpper[1]--;
        } else if (sourceLL == R.id.book_cont3) {
            countUpper[2]--;
        } else if (sourceLL == R.id.book_cont4) {
            countUpper[3]--;
        } else {
            // return false;
        }
        return true;
    }

    public boolean checkSort() {
        int[] array = ItemDatabase.value;
        Arrays.sort(array);
        return false;
    }

    public void iterateList(View v) {

        LinearLayout bookCont1 = (LinearLayout) findViewById(R.id.book_cont1);
        LinearLayout bookCont2 = (LinearLayout) findViewById(R.id.book_cont2);
        LinearLayout bookCont3 = (LinearLayout) findViewById(R.id.book_cont3);
        LinearLayout bookCont4 = (LinearLayout) findViewById(R.id.book_cont4);


        LinearLayout view;

        //The interior if statements here remove the "may produce java.lang.NullPointerException" error
        if (currentBookNo == 0) {
            if (bookCont1 != null && bookCont4 != null) {
                bookCont4.setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
                if (countUpper[0] > 0) {
                    bookCont1.setBackgroundColor(Color.WHITE);
                    Toast.makeText(this, (books[currentBookNo].getValue() + ""), Toast.LENGTH_SHORT).show();
                }
            }
        } else if (currentBookNo == 1) {
            if (bookCont2 != null && bookCont1 != null) {
                bookCont1.setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
                if (countUpper[1] > 0) {
                    bookCont2.setBackgroundColor(Color.WHITE);
                    Toast.makeText(this, (books[currentBookNo].getValue() + ""), Toast.LENGTH_SHORT).show();
                }
            }
        } else if (currentBookNo == 2) {
            if (bookCont2 != null && bookCont3 != null) {
                bookCont2.setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
                if (countUpper[2] > 0) {
                    bookCont3.setBackgroundColor(Color.WHITE);
                    Toast.makeText(this, (books[currentBookNo].getValue() + ""), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            if (bookCont4 != null && bookCont3 != null) {
                bookCont3.setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
                if (countUpper[3] > 0) {
                    bookCont4.setBackgroundColor(Color.WHITE);
                    Toast.makeText(this, (books[currentBookNo].getValue() + ""), Toast.LENGTH_SHORT).show();
                }
            }
        }

        ImageView currentIV = (ImageView) findViewById(R.id.box_view1);
        if (currentIV != null) {
            currentIV.invalidate();
        }
        if (currentBookNo == 3) {
            currentBookNo = 0;
        } else {
            currentBookNo++;
        }
    }

    public void reset(View view){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public int[] buildArray(){
        LinearLayout currentLL;
        View childLL;
        int[] array = new int[4];
        for(int i = 0; i < totalCount; i++){
            currentLL = (LinearLayout) lowerViews[i];
            childLL = currentLL.getChildAt(0);
            int id = childLL.getId();
            if(id == R.id.box_view1){
                array[i] = ItemDatabase.value[0];
            } else if(id == R.id.box_view2){
                array[i] = ItemDatabase.value[1];
            } else if(id == R.id.box_view3){
                array[i] = ItemDatabase.value[2];
            } else if(id == R.id.box_view4){
                array[i] = ItemDatabase.value[3];
            }
        }
        return array;
    }

    public void check(View view){
        int[] array = buildArray();
        int[] solution = ItemDatabase.value;
        Arrays.sort(solution);
        boolean bool = true;
        for(int i = 0; i < totalCount; i++){
            if(array[i] != solution[i]){
                bool = false;
                break;
            }
        }
        if(bool){
           new ParticleSystem(this, 80, R.drawable.confeti2, 10000)
                    .setSpeedModuleAndAngleRange(0f, 0.3f, 180, 180)
                    .setRotationSpeed(144)
                    .setAcceleration(0.00005f, 90)
                    .emit(findViewById(R.id.emiter_top_right), 8);

            new ParticleSystem(this, 80, R.drawable.confeti3, 10000)
                    .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 0)
                    .setRotationSpeed(144)
                    .setAcceleration(0.00005f, 90)
                    .emit(findViewById(R.id.emiter_top_left), 8);
            displayMessage("You Win!");
        } else {
            displayMessage("Try Again...");
        }
    }

    public void displayMessage(String string){
        Toast.makeText(this, (string), Toast.LENGTH_SHORT).show();
    }
}
