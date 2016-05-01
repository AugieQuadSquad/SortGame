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
        int numSorted = 0;
        for (int i = numSorted; i < tempArray.length; i++){
            extractedElement = tempArray[i];
            extractedElementIndex = i;
            for(int j = i - 1; j>=0; j--){
                if(extractedElement < tempArray[j]) {
                    Pairs tempPair = new Pairs(j + 1, j);
                    pairsList.add(tempPair);
                    tempArray[j + 1] = tempArray[j];
                } else {
                    Pairs tempPair = new Pairs(j + 1, extractedElementIndex);
                    pairsList.add(tempPair);
                    tempArray[j + 1] = extractedElement;
                }

            }
        }
        return pairsList;
    }

    public static String getName() {
        return "Insertion Sort";
    }
}