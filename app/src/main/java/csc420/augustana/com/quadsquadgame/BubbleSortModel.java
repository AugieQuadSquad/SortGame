package csc420.augustana.com.quadsquadgame;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Cat on 4/27/2016.
 */
public class BubbleSortModel {

    public static List<Pairs> getSwapSequence(int[] array){
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

    public static String getName() {
        return "Bubble Sort";
    }
}
