package csc420.augustana.com.quadsquadgame;

import android.widget.ImageView;
import java.util.Random;

/**
 * Created by Cat on 4/6/2016.
 *
 * Inspired by Renaissance Paintings app from Android Programming Concepts
 *                      by Trish Cornez and Richard Cornez
 */

public class ItemDatabase {
    static Random rand = new Random();
    //initialize random numbers 1-50.
    static int randomNum1 = rand.nextInt(50) + 1;
    static int randomNum2 = rand.nextInt(50) + 1;
    static int randomNum3 = rand.nextInt(50) + 1;
    static int randomNum4 = rand.nextInt(50) + 1;


    // array of values associated with each item in order to sort
    public static int value[] = {
            randomNum1, randomNum2, randomNum3, randomNum4
    };

    // array of the item ids
    public static int id[] = {
            /*R.drawable.box_one,
            R.drawable.box_two,
            R.drawable.box_three,
            R.drawable.box_four*/
            R.id.box_view1,
            R.id.box_view2,
            R.id.box_view3,
            R.id.box_view4
    };

    public static void setValues(){
        Random rand = new Random();
        //initialize random numbers 1-50.
        randomNum1 = rand.nextInt(50) + 1;
        randomNum2 = rand.nextInt(50) + 1;
        randomNum3 = rand.nextInt(50) + 1;
        randomNum4 = rand.nextInt(50) + 1;
    }

    public static int upperContainer[] = {
            R.id.book_cont1, R.id.book_cont2, R.id.book_cont3, R.id.book_cont4
    };

    public static int lowerContainer[] = {
            R.id.container_one, R.id.container_two, R.id.container_three, R.id.container_four
    };
}
