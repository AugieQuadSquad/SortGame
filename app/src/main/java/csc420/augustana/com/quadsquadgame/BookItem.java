package csc420.augustana.com.quadsquadgame;

import android.view.View;

/**
 * Created by Cat on 4/6/2016.
 *
 * Inspired by Renaissance Paintings app from Android Programming Concepts
 *                      by Trish Cornez and Richard Cornez
 *
 * Data model for individual "book"
 */
public class BookItem {
    private int mValue;
    private int mId;
    private int mContainer;

    public BookItem (int value, int id, int container) {
        mValue = value;
        mId = id;
        mContainer = container;
    }

    public int getValue(){
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }

    public void setContainer(int myView){
        mContainer = myView;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
