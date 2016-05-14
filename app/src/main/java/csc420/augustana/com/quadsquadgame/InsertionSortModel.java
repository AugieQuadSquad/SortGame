package csc420.augustana.com.quadsquadgame;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Insertion Sort algorithm for an Insert Sort. It also
 * stores the string for Insertion Sort
 *
 * @author Devon White, Michael Currie, Luke Currie, Catherine Cross
 * @since 4/27/2016
 */
public class InsertionSortModel {

    /**
     * This method accepts the array of the current order of objects and values
     * and returns a list of Pairs elements. This list represents the order of moves
     * to be completed for Insertion Sort.
     *
     * @param array This is the first parameter to addNum method
     * @return List<Pairs> This returns the List of Pairs elements
     */
    public static List<Pairs> getSwapSequence(int[] array) {
        List<Pairs> pairsList = new ArrayList<Pairs>();
        int extractedElement = -1;
        int extractedElementIndex = -1;
        int[] tempArray = new int[array.length];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = array[i];
        }
        /*int numSorted = 0;*/
        for (int i = 1; i < tempArray.length; i++) {  // start at 1 ?
            extractedElement = tempArray[i];
            extractedElementIndex = i;
            int arrayCount = extractedElementIndex - 1;
            boolean wasMoved = false;
            while (arrayCount >= 0 && extractedElement < tempArray[arrayCount]) {
                Pairs tempPair = new Pairs(arrayCount + 1, arrayCount);
                pairsList.add(tempPair);
                tempArray[arrayCount + 1] = tempArray[arrayCount];
                wasMoved = true;
                arrayCount--;
            }
            if (wasMoved) {
                tempArray[arrayCount + 1] = extractedElement;
            }
        }
        return pairsList;
    }

    /**
     * This method returns the String Insertion Sort to display the sort name if needed
     *
     * @return String This returns the String of the sort name
     */
    public static String getName() {
        return "Insertion Sort";
    }
}