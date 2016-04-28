package csc420.augustana.com.quadsquadgame;

/**
 * Created by Cat on 4/27/2016.
 * CR change
 */
public class BubbleHighScores {
    private static int hintsCount;
    private static int wrongTestCount;
    private static int resetCount;
    private static int wrongMovesCount;
    private static int totalScore;
    private static int hintWeight = 0;
    private static int testWeight = 0;
    private static int wrongMovesWeight = 0;
    private static int restWeight = 0;
    private static int[] topScores = new int[5];

    public BubbleHighScores(int hintsCount, int wrongTestCount, int resetCount, int totalScore, int wrongMovesCount, int[] topScores) {
        this.hintsCount = hintsCount;
        this.wrongTestCount = wrongTestCount;
        this.resetCount = resetCount;
        this.totalScore = totalScore;
        this.wrongMovesCount = wrongMovesCount;
        this.topScores = topScores;
    }

    public static int getHintsCount(){
        return hintsCount;
    }

    public static int getWrongTestCount(){
        return wrongTestCount;
    }

    public static int getResetCount(){
        return resetCount;
    }

    public static int getWrongMovesCount(){
        return wrongMovesCount;
    }

    public static void addHint(){
        hintsCount++;
    }

    public static void addWrongTest(){
        wrongTestCount++;
    }

    public static void addResetClick(){
        resetCount++;
    }

    public static void addWrongMove() { wrongMovesCount++; }

    public static int getTotalScore(int secondsRemaining){
        totalScore = secondsRemaining + - hintWeight * hintsCount - testWeight * wrongTestCount - wrongMovesWeight * wrongMovesCount - resetCount* restWeight;
        if (totalScore < 0) {
            totalScore = 0;
        }
        return totalScore;
    }

    public static int[] scoreBoard(int[] pastScores) {
        Boolean notChangedYet = true;
        for(int i=0; i < pastScores.length; i++) {
            if(totalScore >= pastScores[i] && notChangedYet){
                for(int j=4; j > i; j--){
                    if(j>0) {
                        topScores[j] = topScores[j - 1];
                    }
                }
                topScores[i] = totalScore;
                notChangedYet = false;
            }
        }
        return topScores;
    }
}
