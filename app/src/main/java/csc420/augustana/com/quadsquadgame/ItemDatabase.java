package csc420.augustana.com.quadsquadgame;

import android.widget.ImageView;

/**
 * Created by Cat on 4/6/2016.
 *
 * Inspired by Renaissance Paintings app from Android Programming Concepts
 *                      by Trish Cornez and Richard Cornez
 */
public class ItemDatabase {
    // array of values associated with each item in order to sort
    public static int value[] = {
            3, 14, 27, 9
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
}
