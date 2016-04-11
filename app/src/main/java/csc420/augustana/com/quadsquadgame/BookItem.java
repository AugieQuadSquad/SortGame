package csc420.augustana.com.quadsquadgame;

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

    public BookItem (int value, int id) {
        mValue = value;
        mId = id;
    }

    public int getValue(){
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
