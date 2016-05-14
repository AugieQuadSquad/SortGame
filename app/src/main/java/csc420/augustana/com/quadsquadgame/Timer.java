package csc420.augustana.com.quadsquadgame;

import android.os.CountDownTimer;

/**
 * The Timer class creates the Timer countdown
 *
 * @author Devon White, Michael Currie, Luke Currie, Catherine Cross
 * @since 4/10/2016
 */
public class Timer {

    public static String secondsRemaining;
    int circularFillableCount = 0;
    private int secondCountDown = 30;
    public static CountDownTimer timer;


    /**
     * This method initiates and starts the countDownTimer
     */
    public void start() {
        // 60000ms with a tick every 1000
        timer = new CountDownTimer(secondCountDown * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                secondsRemaining = "" + millisUntilFinished / 1000;
                circularFillableCount += (100 / secondCountDown);
                GameBoard.setTimerGraphic(circularFillableCount);
            }

            public void onFinish() {
                if (GameBoard.isSorted(GameBoard.buildArray())) {
                    secondsRemaining = 0 + "";
                    GameBoard.showDialog(true);
                } else {
                    secondsRemaining = 0 + "";
                    GameBoard.showDialog(false);
                }
            }
        }.start();
    }

    /**
     * This method returns the number of seconds remaining when the timer ends.
     *
     * @return String
     */
    public static String getSecondsRemaining() {
        return secondsRemaining;
    }

    /**
     * This method cancels the timer
     */
    public static void cancel() {
        timer.cancel();
    }
}
