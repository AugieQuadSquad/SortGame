package csc420.augustana.com.quadsquadgame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devon White on 4/27/2016.
 */
public class InsertionSortModel {

    public static List<Pairs> getSwapSequence(int[] array){
        List<Pairs> pairsList = new ArrayList<Pairs>();
        int extractedElement = -1;
        int extractedElementIndex = -1;
        int[] tempArray = new int[array.length];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = array[i];
        }
        /*int numSorted = 0;*/
        for (int i = 1; i < tempArray.length; i++){  // start at 1 ?
            extractedElement = tempArray[i];
            extractedElementIndex = i;
            int arrayCount = extractedElementIndex - 1;
            boolean wasMoved = false;
            while(arrayCount>=0 && extractedElement < tempArray[arrayCount]){
                Pairs tempPair = new Pairs(arrayCount + 1, arrayCount);
                pairsList.add(tempPair);
                tempArray[arrayCount + 1] = tempArray[arrayCount];
                wasMoved = true;
                arrayCount--;
            }
            if(wasMoved){
                tempArray[arrayCount+1] = extractedElement;
            }
        }
        return pairsList;
    }

    public static String getName() {
        return "Insertion Sort";
    }
}