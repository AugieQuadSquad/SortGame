package csc420.augustana.com.quadsquadgame;

import android.widget.Toast;

/**
 * Created by Owner on 4/21/2016.
 */
public class Pairs {
    int first;
    int second;

    public Pairs(){
        first = -1;
        second = -1;
    }

    public Pairs(int tempFirst,int tempSecond){
        first = tempFirst;
        second = tempSecond;
    }

    public void setFirst(int value){
        first = value;
    }

    public void setSecond(int value){
        second = value;
    }

    public int getFirst(){
        return first;
    }

    public int getSecond(){
        return second;
    }

    public String toString(){
        return first + " and " + second;
    }
}
