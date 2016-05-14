package csc420.augustana.com.quadsquadgame;

/**
 * The pairs method creates a pairs object that is two int values, one for the first value that is sorted, and one for the second
 *
 * @author Devon White, Michael Currie, Luke Currie, Catherine Cross
 * @since 4/21/2016
 */
public class Pairs {
    int first;
    int second;


    /**
     * Constructor for the pairs object that are two ints representing
     * the index of an array.
     *
     * @param tempFirst
     * @param tempSecond
     */
    public Pairs(int tempFirst, int tempSecond) {
        first = tempFirst;
        second = tempSecond;
    }

    /**
     * The following methods set the first and second values in case it is needed for
     * specific cases
     *
     * @param value is the int value for the global variable
     */
    public void setFirst(int value) {
        first = value;
    }

    public void setSecond(int value) {
        second = value;
    }

    /**
     * The following methods return the corresponding values saved as global varaibles
     *
     * @return int
     */
    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    /**
     * This method converts the values to string to display - helps with testing
     * to display which indexes should switch
     *
     * @return String of first and second values
     */
    public String toString() {
        return first + " and " + second;
    }
}
