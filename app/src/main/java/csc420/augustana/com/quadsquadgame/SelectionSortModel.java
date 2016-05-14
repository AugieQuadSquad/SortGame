package csc420.augustana.com.quadsquadgame;

import java.util.ArrayList;
import java.util.List;

/**
 * The SelectionSortModel class creates the algorithm for the Selection Sort array. It also
 * stores the string to display if Selection Sort is being used.
 *
 * @author Devon White, Michael Currie, Luke Currie, Catherine Cross
 * @since 5/9/2016
 */
public class SelectionSortModel {

    /**
     * This method accepts the array of the current order of objects and values
     * and returns a list of Pairs elements. This list represents the order of moves
     * to be completed for Selection Sort.
     *
     * @param array This is the first parameter to addNum method
     * @return List<Pairs> This returns the List of Pairs elements
     */
    public static List<Pairs> getSwapSequence(int[] array) {
        List<Pairs> pairsList = new ArrayList<Pairs>();
        ArrayList<Integer> tempArray = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            tempArray.add(array[i]);
        }

        while (!isSorted(tempArray)) {
            for (int i = 0; i < tempArray.size() - 1; i++) {
                int iVal = tempArray.get(i);
                int minVal = iVal;
                int minIndex = i;
                for (int j = i + 1; j < tempArray.size(); j++) {
                    if (tempArray.get(j) < minVal) {
                        minVal = tempArray.get(j);
                        minIndex = j;
                    }
                }
                if (minIndex != i) {
                    pairsList.add(new Pairs(i, minIndex));
                    int tempVal = tempArray.get(i);
                    tempArray.set(i, tempArray.get(minIndex));
                    tempArray.set(minIndex, tempVal);
                }
            }

        }
        return pairsList;
    }

    /**
     * This method checks to see if the array is sorted
     *
     * @return boolean returns true if the array is sorted, false otherwise
     */
    private static boolean isSorted(ArrayList<Integer> tempArray) {
        boolean sorted = true;
        for (int i = 1; i < tempArray.size(); i++) {
            if (tempArray.get(i - 1).compareTo(tempArray.get(i)) > 0) sorted = false;
        }

        return sorted;
    }

    /**
     * This method returns the String Bubble Sort to display the sort name if needed
     *
     * @return String This returns the String of the sort name
     */
    public static String getName() {
        return "Selection Sort";
    }
}
