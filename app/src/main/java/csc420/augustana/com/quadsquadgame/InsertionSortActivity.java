package csc420.augustana.com.quadsquadgame;

// Source code based off http://androidsrc.net/android-view-drag-drop-functionality-sample-application/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.media.Image;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.GestureDetector.SimpleOnGestureListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

//TESTING UPDATES

public class InsertionSortActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    private int countCont1, countCont2, countCont3, countCont4;
    private int countUpCont1, countUpCont2, countUpCont3, countUpCont4;
    private BookItem[] books = new BookItem[15];
    int totalCount = 0;
    int currentBookNo = 0;
    private View[] upperLL = new View[15];
    private View[] lowerLL = new View[15];
    private int[] countUpper = new int[15];
    private int[] countLower = new int[15];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertion_sort);
        ItemDatabase.setValues();

        for (int i=0; i < ItemDatabase.value.length; i++) {
            BookItem book = new BookItem(ItemDatabase.value[i], ItemDatabase.id[i]);
            books[i] = book;
            totalCount++;
        }

        ImageView boxView1 = (ImageView) findViewById(R.id.box_view1);
        ImageView boxView2 = (ImageView) findViewById(R.id.box_view2);
        ImageView boxView3 = (ImageView) findViewById(R.id.box_view3);
        ImageView boxView4 = (ImageView) findViewById(R.id.box_view4);

        //The if statements here remove the "may produce java.lang.NullPointerException" error
        if(boxView1 != null){
            boxView1.setOnTouchListener(this);
        }
        if(boxView2 != null){
            boxView2.setOnTouchListener(this);
        }
        if(boxView3 != null){
            boxView3.setOnTouchListener(this);
        }
        if(boxView4 != null){
            boxView4.setOnTouchListener(this);
        }

        LinearLayout topView = (LinearLayout) findViewById(R.id.top_view);
        LinearLayout bottomView = (LinearLayout) findViewById(R.id.bottom_view);
        LinearLayout cont1 = (LinearLayout) findViewById(R.id.container_one);
        LinearLayout cont2 = (LinearLayout) findViewById(R.id.container_two);
        LinearLayout cont3 = (LinearLayout) findViewById(R.id.container_three);
        LinearLayout cont4 = (LinearLayout) findViewById(R.id.container_four);

        lowerLL[0] = findViewById(R.id.container_one);
        lowerLL[1] = findViewById(R.id.container_two);
        lowerLL[2] = findViewById(R.id.container_three);
        lowerLL[3] = findViewById(R.id.container_four);

        for(int i=0; i<books.length; i++) {
            if(lowerLL[i] != null) {
                lowerLL[i].setOnDragListener(this);
            }
        }


        //The if statements here removes the "may produce java.lang.NullPointerException" error
        // There should not be a drag listener for top and bottom view...
/*        if(topView != null){
            topView.setOnDragListener(this);
        }
        if(bottomView != null){
            bottomView.setOnDragListener(this);
        }*/

        // should be completed by for loop above (to test)
/*        if(cont1 != null){
            cont1.setOnDragListener(this);
        }
        if(cont2 != null){
            cont2.setOnDragListener(this);
        }
        if(cont3 != null){
            cont3.setOnDragListener(this);
        }
        if(cont4 != null){
            cont4.setOnDragListener(this);
        } */


        /* Should be done by for loop
        countCont1 = 0;
        countCont2 = 0;
        countCont3 = 0;
        countCont4 = 0;*/

        for(int i=0; i < books.length; i++){
            countLower[i] = 0;
            countUpper[i] = 1;
        }

        // countUpCont1 = countUpCont2 = countUpCont3 = countUpCont4 = 1;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        // TODO Auto-generated method stub
        if(event.getAction()==DragEvent.ACTION_DROP){
            //we want to make sure it is dropped only to top and bottom parent view
            View view = (View)event.getLocalState();

            // Potentially change to case/switch rather than if else statements
            // TODO: CHANGE TO ARRAY CHECK
            if(v.getId() == R.id.top_view  || (v.getId() == R.id.container_one && countCont1 == 0) || (v.getId() == R.id.container_two && countCont2 == 0) || (v.getId() == R.id.container_three && countCont3 == 0) || (v.getId() == R.id.container_four && countCont4 == 0)){


                ViewGroup source = (ViewGroup) view.getParent();
                source.removeView(view);


                // TODO: inefficient - recode?
                if (v.getId() == R.id.container_one) {
                    countCont1 = 1;
                } else if (v.getId() == R.id.container_two) {
                    countCont2 = 1;
                } else if (v.getId() == R.id.container_three) {
                    countCont3 = 1;
                } else if (v.getId() == R.id.container_four) {
                    countCont4 = 1;
                } else {
                    if(source.getId() == R.id.container_one) {
                        countCont1--;
                    } else if(source.getId() == R.id.container_two) {
                        countCont2--;
                    } else if(source.getId() == R.id.container_three) {
                        countCont3--;
                    } else if(source.getId() == R.id.container_four) {
                        countCont4--;
                    }
                }

                Boolean movedOutofTop = checkBoxViewEmpty(source.getId());
                if(!movedOutofTop){
                    if(v.getId() == R.id.book_cont1){
                        countUpCont1++;
                    } else if(v.getId() == R.id.book_cont2) {
                        countUpCont2++;
                    } else if(v.getId() == R.id.book_cont3) {
                        countUpCont3++;
                    } else{
                        countUpCont4++;
                    }
                }


                LinearLayout target = (LinearLayout) v;
                target.addView(view);
            }
            //make view visible as we set visibility to invisible while starting drag
            view.setVisibility(View.VISIBLE);
        }
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // TODO Auto-generated method stub
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(null, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);
            return true;
        }
        currentBookNo=0;
        findViewById(R.id.book_cont1).setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
        findViewById(R.id.book_cont2).setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
        findViewById(R.id.book_cont3).setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
        findViewById(R.id.book_cont4).setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
        return false;
    }


    private boolean checkBoxViewEmpty(int sourceLL){
        if(sourceLL == R.id.book_cont1){
            countUpCont1--;
        } else if(sourceLL == R.id.book_cont2) {
            countUpCont2--;
        } else if(sourceLL == R.id.book_cont3) {
            countUpCont3--;
        } else if(sourceLL == R.id.book_cont4){
            countUpCont4--;
        } else {
            // return false;
        }
        return true;
    }

/*    public boolean checkSort() {


        return false;
    }*/

    // display toast
    private void displayToast(int bookValue) {
        // Show the value of the book as
        // a toast with a short display life
        // String bookString = bookValue + "";
        Toast.makeText(this, (bookValue + ""), Toast.LENGTH_SHORT).show();
    }



    public void iterateList(View v){

        LinearLayout bookCont1 = (LinearLayout) findViewById(R.id.book_cont1);
        LinearLayout bookCont2 = (LinearLayout) findViewById(R.id.book_cont2);
        LinearLayout bookCont3 = (LinearLayout) findViewById(R.id.book_cont3);
        LinearLayout bookCont4 = (LinearLayout) findViewById(R.id.book_cont4);


        LinearLayout view;

        //The interior if statements here remove the "may produce java.lang.NullPointerException" error
        if(currentBookNo == 0) {
            if(bookCont1 != null && bookCont4 != null){
                bookCont4.setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
                if(countUpCont1>0){
                    bookCont1.setBackgroundColor(Color.WHITE);
                    // Toast.makeText(this, (randomNum1 + ""), Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, (books[currentBookNo].getValue() + ""), Toast.LENGTH_SHORT).show();
                }
            }
        } else if(currentBookNo==1){
            if(bookCont2 != null && bookCont1 != null){
                bookCont1.setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
                if(countUpCont2>0){
                    bookCont2.setBackgroundColor(Color.WHITE);
                    //Toast.makeText(this, (randomNum2 + ""), Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, (books[currentBookNo].getValue() + ""), Toast.LENGTH_SHORT).show();
                }
            }
        } else if(currentBookNo==2) {
            if(bookCont2 != null && bookCont3 != null){
                bookCont2.setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
                if(countUpCont3>0) {
                    bookCont3.setBackgroundColor(Color.WHITE);
                    // Toast.makeText(this, (randomNum3 + ""), Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, (books[currentBookNo].getValue() + ""), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            if(bookCont4 != null && bookCont3 != null){
                bookCont3.setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
                if(countUpCont4>0) {
                    bookCont4.setBackgroundColor(Color.WHITE);
                    //Toast.makeText(this, (randomNum4 + ""), Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, (books[currentBookNo].getValue() + ""), Toast.LENGTH_SHORT).show();
                }
            }
        }

        // Toast.makeText(this, (books[currentBookNo].getValue() + ""), Toast.LENGTH_SHORT).show();

        ImageView currentIV = (ImageView) findViewById(R.id.box_view1);
        if(currentIV != null){
            currentIV.invalidate();
        }
        if(currentBookNo==3){
            currentBookNo=0;
        } else{
            currentBookNo++;
        }
    }
}
