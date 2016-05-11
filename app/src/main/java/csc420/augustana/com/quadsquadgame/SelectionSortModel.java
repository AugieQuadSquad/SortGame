package csc420.augustana.com.quadsquadgame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke Currie on 5/9/2016.
 */
public class SelectionSortModel {



    public static List<Pairs> getSwapSequence(int[] array) {
        List<Pairs> pairsList = new ArrayList<Pairs>();
        ArrayList<Integer> tempArray = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            tempArray.add(array[i]);
        }

        while (!isSorted(tempArray)) {
            for (int i=0; i<tempArray.size()-1;i++){
                int iVal=tempArray.get(i);
                int minVal=iVal;
                int minIndex=i;
                for(int j=i+1;j<tempArray.size();j++){
                    if(tempArray.get(j)<minVal){
                        minVal=tempArray.get(j);
                        minIndex=j;
                    }
                }
                if (minIndex!=i){
                    pairsList.add(new Pairs(i,minIndex));
                    int tempVal=tempArray.get(i);
                    tempArray.set(i,tempArray.get(minIndex));
                    tempArray.set(minIndex,tempVal);
                }
            }

        }
        return pairsList;
    }

    private static boolean isSorted(ArrayList<Integer> tempArray)
    {
        boolean sorted = true;
        for (int i = 1; i < tempArray.size(); i++) {
            if (tempArray.get(i-1).compareTo(tempArray.get(i)) > 0) sorted = false;
        }

        return sorted;
    }

    public static String getName() {
        return "Selection Sort";
    }
}
