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
import android.view.GestureDetector.SimpleOnGestureListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Random;

//TESTING UPDATES

public class InsertionSortActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    private BookItem[] books = new BookItem[15];
    int totalCount = 0;
    int currentBookNo = 0;
    private View[] upperViews = new View[15];
    private View[] lowerViews = new View[15];
    private int[] countUpper = new int[15];
    private int[] countLower = new int[15];
    private int[] containersInUse = new int[4];

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

        for (int i = 0; i < 3; i++) {
            containersInUse[i] = ItemDatabase.upperContainer[i];
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

        /*upperViews[0] = findViewById(R.id.book_cont1);
        upperViews[1] = findViewById(R.id.book_cont2);
        upperViews[2] = findViewById(R.id.book_cont3);
        upperViews[3] = findViewById(R.id.book_cont4);

        lowerViews[0] = findViewById(R.id.container_one);
        lowerViews[1] = findViewById(R.id.container_two);
        lowerViews[2] = findViewById(R.id.container_three);
        lowerViews[3] = findViewById(R.id.container_four);

        for(int i=0; i<totalCount; i++){
            upperViews[i].setOnDragListener(this);
            lowerViews[i].setOnDragListener(this);
        }*/

        findViewById(R.id.top_view).setOnDragListener(this);
        findViewById(R.id.bottom_view).setOnDragListener(this);
/*
        for (int i = 0; i < totalCount; i++) {
            countLower[i] = 0;
            countUpper[i] = 1;
        }*/
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        // TODO Auto-generated method stub
        View view = (View) event.getLocalState();
        if (view.getId() != R.id.middle_btn) {
            if (event.getAction() == DragEvent.ACTION_DROP) {
                //we want to make sure it is dropped only to top and bottom parent view
                //View view = (View) event.getLocalState();


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

                //LinearLayout target = (LinearLayout) v;
                //target.addView(view);
            }


            /*// Potentially change to case/switch rather than if else statements

            if (v.getId() == R.id.top_view || (v.getId() == R.id.container_one && countCont1 == 0) || (v.getId() == R.id.container_two && countCont2 == 0) || (v.getId() == R.id.container_three && countCont3 == 0) || (v.getId() == R.id.container_four && countCont4 == 0)) {


                ViewGroup source = (ViewGroup) view.getParent();
                source.removeView(view);



                if (v.getId() == R.id.container_one) {
                    countLower[0] = 1;
                } else if (v.getId() == R.id.container_two) {
                    countLower[1] = 1;
                } else if (v.getId() == R.id.container_three) {
                    countLower[2] = 1;
                } else if (v.getId() == R.id.container_four) {
                    countLower[3] = 1;
                } else {
                    if (source.getId() == R.id.container_one) {
                        countLower[0]--;
                    } else if (source.getId() == R.id.container_two) {
                        countLower[1]--;
                    } else if (source.getId() == R.id.container_three) {
                        countLower[2]--;
                    } else if (source.getId() == R.id.container_four) {
                        countLower[3]--;
                    }
                }

                Boolean movedOutofTop = checkBoxViewEmpty(source.getId());
                if (!movedOutofTop) {
                    if (v.getId() == R.id.book_cont1) {
                        countUpper[0]++;
                    } else if (v.getId() == R.id.book_cont2) {
                        countUpper[1]++;
                    } else if (v.getId() == R.id.book_cont3) {
                        countUpper[2]++;
                    } else {
                        countUpper[3]++;
                    }
                }


                LinearLayout target = (LinearLayout) v;
                target.addView(view);
            }*/

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

    // display toast
    private void displayToast(int bookValue) {
        // Show the value of the book as
        // a toast with a short display life
        // String bookString = bookValue + "";
        Toast.makeText(this, (bookValue + ""), Toast.LENGTH_SHORT).show();
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

        // Toast.makeText(this, (books[currentBookNo].getValue() + ""), Toast.LENGTH_SHORT).show();

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
}
