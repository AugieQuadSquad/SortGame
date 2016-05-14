package csc420.augustana.com.quadsquadgame;

import java.util.ArrayList;
import java.util.List;

/**
 * The BubbleSortModel class creates the algorithm for the Bubble Sort array. It also
 * stores the string to display if Bubble Sort is being used.
 *
 * @author Devon White, Michael Currie, Luke Currie, Catherine Cross
 * @since 4/10/2016
 */
public class BubbleSortModel {

    /**
     * This method accepts the array of the current order of objects and values
     * and returns a list of Pairs elements. This list represents the order of moves
     * to be completed for Bubble Sort.
     *
     * @param array This is the first parameter to addNum method
     * @return List<Pairs> This returns the List of Pairs elements
     */
    public static List<Pairs> getSwapSequence(int[] array) {
        List<Pairs> pairsList = new ArrayList<Pairs>();
        int[] tempArray = new int[array.length];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = array[i];
        }
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < tempArray.length - 1; i++) {
                if (tempArray[i] > tempArray[i + 1]) {
                    Pairs tempPair = new Pairs(i, i + 1);
                    pairsList.add(tempPair);
                    int tempInt = tempArray[i];
                    tempArray[i] = tempArray[i + 1];
                    tempArray[i + 1] = tempInt;
                    swapped = true;
                }
            }
        }
        return pairsList;
    }

    /**
     * This method returns the String Bubble Sort to display the sort name if needed
     *
     * @return String This returns the String of the sort name
     */
    public static String getName() {
        return "Bubble Sort";
    }
}
