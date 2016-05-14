package csc420.augustana.com.quadsquadgame;

/**
 * The HighScores class keeps track of button clicks in order to create
 * a high score.
 *
 * @author Devon White, Michael Currie, Luke Currie, Catherine Cross
 * @since 4/22/2016
 */
public class HighScores {
    private static int hintsCount;
    private static int wrongTestCount;
    private static int resetCount;
    private static int wrongMovesCount;
    private static int totalScore;
    private static int hintWeight = 0;
    private static int testWeight = 0;
    private static int wrongMovesWeight = 0;
    private static int restWeight = 0;


    /**
     * Constructor for the HighScores class
     *
     * @param hintsCount
     * @param wrongTestCount
     * @param resetCount
     * @param totalScore
     * @param wrongMovesCount
     */
    public HighScores(int hintsCount, int wrongTestCount, int resetCount, int totalScore, int wrongMovesCount) {
        this.hintsCount = hintsCount;
        this.wrongTestCount = wrongTestCount;
        this.resetCount = resetCount;
        this.totalScore = totalScore;
        this.wrongMovesCount = wrongMovesCount;
        //this.topScores = topScores;
    }

    /**
     * This method resets all the scores to 0 to be called when the game is restarted
     */
    public static void resetScores() {
        hintsCount = 0;
        resetCount = 0;
        wrongTestCount = 0;
        wrongMovesCount = 0;
        totalScore = 0;
    }

    /**
     * The following methods return the count for the global variables
     *
     * @return int This returns the global variable for each of
     * get methods below.
     */
    public static int getHintsCount() {
        return hintsCount;
    }

    public static int getWrongTestCount() {
        return wrongTestCount;
    }

    public static int getResetCount() {
        return resetCount;
    }

    public static int getWrongMovesCount() {
        return wrongMovesCount;
    }

    /**
     * These methods add one to the score for the corresponding variables
     * in order to create the total score.
     */
    public static void addHint() {
        hintsCount++;
    }

    public static void addWrongTest() {
        wrongTestCount++;
    }

    public static void addResetClick() {
        resetCount++;
    }

    public static void addWrongMove() {
        wrongMovesCount++;
    }

    /**
     * This method gets the total score by using the counts of the times the player used hints,
     * the number of wrong tests, and the number of wrong moves. They are all multiplied by weights
     * in order to value the scores as the programmer desires. For our purposes, we left the
     * weights at 1.
     *
     * @param secondsRemaining int for the amount of time remaining
     * @return int returns the total score using the weights and the counts
     */
    public static int getTotalScore(int secondsRemaining) {
        totalScore = secondsRemaining - hintWeight * hintsCount - testWeight * wrongTestCount - wrongMovesWeight * wrongMovesCount - resetCount * restWeight;
        if (totalScore < 0) {
            totalScore = 0;
        }
        return totalScore;
    }
}
