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
import java.util.ArrayList;
import java.util.Random;

public class InsertionSortActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    private int countCont1, countCont2, countCont3, countCont4;
    private GestureDetector gestureDetector;
    private BookItem[] books = new BookItem[15];
    int totalCount = 0;
    int currentBookNo = 0;
    int randomNum1, randomNum2, randomNum3, randomNum4;
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertion_sort);

        rand = new Random();
        //initialize random numbers 1-50.
        randomNum1 = rand.nextInt(50) + 1;
        randomNum2 = rand.nextInt(50) + 1;
        randomNum3 = rand.nextInt(50) + 1;
        randomNum4 = rand.nextInt(50) + 1;

        gestureDetector = new GestureDetector(this, new SingleTapConfirm());

        for (int i=0; i < ItemDatabase.value.length; i++) {
            // bookIV = new ImageView(this);
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

        LinearLayout leftView = (LinearLayout) findViewById(R.id.left_view);
        LinearLayout rightView = (LinearLayout) findViewById(R.id.right_view);
        LinearLayout cont1 = (LinearLayout) findViewById(R.id.container_one);
        LinearLayout cont2 = (LinearLayout) findViewById(R.id.container_two);
        LinearLayout cont3 = (LinearLayout) findViewById(R.id.container_three);
        LinearLayout cont4 = (LinearLayout) findViewById(R.id.container_four);

        //The if statements here removes the "may produce java.lang.NullPointerException" error
        if(leftView != null){
            leftView.setOnDragListener(this);
        }
        if(rightView != null){
            rightView.setOnDragListener(this);
        }
        if(cont1 != null){
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
        }


        // TODO: change to array to check through in onDrag
        countCont1 = 0;
        countCont2 = 0;
        countCont3 = 0;
        countCont4 = 0;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        // TODO Auto-generated method stub
        if(event.getAction()==DragEvent.ACTION_DROP){
            //we want to make sure it is dropped only to left and right parent view
            View view = (View)event.getLocalState();

            // Potentially change to case/switch rather than if else statements
            if(v.getId() == R.id.left_view  || (v.getId() == R.id.container_one && countCont1 == 0) || (v.getId() == R.id.container_two && countCont2 == 0) || (v.getId() == R.id.container_three && countCont3 == 0) || (v.getId() == R.id.container_four && countCont4 == 0)){


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
        /*ImageView image = (ImageView) findViewById(R.id.box_view1);
        Rect rect = new Rect();
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            image.setColorFilter(Color.argb(50, 0, 0, 0));
            rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            image.setColorFilter(Color.argb(0, 0, 0, 0));
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            if(!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())){
                image.setColorFilter(Color.argb(0, 0, 0, 0));
            }
        }*/

        return false;
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


    private class SingleTapConfirm extends SimpleOnGestureListener {
        /*@Override
        public boolean onDown(MotionEvent e) {
            return true;
        }*/

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            return true;
        }
    }


    public void iterateList(View v){

        LinearLayout bookCont1 = (LinearLayout) findViewById(R.id.book_cont1);
        LinearLayout bookCont2 = (LinearLayout) findViewById(R.id.book_cont2);
        LinearLayout bookCont3 = (LinearLayout) findViewById(R.id.book_cont3);
        LinearLayout bookCont4 = (LinearLayout) findViewById(R.id.book_cont4);

        /*Rect rect;
        ImageView currentIV;

        book_cont1

        currentIV = (ImageView) findViewById(books[currentBookNo].getId());
        currentIV.setColorFilter(Color.argb(50, 0, 0, 0));
        rect = new Rect(currentIV.getLeft(), currentIV.getTop(), currentIV.getRight(), currentIV.getBottom());
        // currentIV.setColorFilter(Color.argb(0,0,0,0));*/
        LinearLayout view;

        //The interior if statements here remove the "may produce java.lang.NullPointerException" error
        if(currentBookNo == 0) {
            if(bookCont1 != null && bookCont4 != null){
                bookCont1.setBackgroundColor(Color.WHITE);
                bookCont4.setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
                Toast.makeText(this, (randomNum1 + ""), Toast.LENGTH_SHORT).show();
            }
        } else if(currentBookNo==1){
            if(bookCont2 != null && bookCont1 != null){
                bookCont2.setBackgroundColor(Color.WHITE);
                bookCont1.setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
                Toast.makeText(this, (randomNum2 + ""), Toast.LENGTH_SHORT).show();
            }
        } else if(currentBookNo==2) {
            if(bookCont2 != null && bookCont3 != null){
                bookCont3.setBackgroundColor(Color.WHITE);
                bookCont2.setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
                Toast.makeText(this, (randomNum3 + ""), Toast.LENGTH_SHORT).show();
            }
        } else {
            if(bookCont4 != null && bookCont3 != null){
                bookCont4.setBackgroundColor(Color.WHITE);
                bookCont3.setBackgroundColor(getResources().getColor(R.color.mcolorDarkGrey));
                Toast.makeText(this, (randomNum4 + ""), Toast.LENGTH_SHORT).show();
            }
        }


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
