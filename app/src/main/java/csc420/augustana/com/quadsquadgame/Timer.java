package csc420.augustana.com.quadsquadgame;

import android.os.CountDownTimer;

/**
 * Created by Devon White on 4/29/2016.
 */
public class Timer {

    public static String secondsRemaining;
    int circularFillableCount = 0;
    private int secondCountDown = 30;
    public static CountDownTimer timer;



    public void start() {
        // 60000ms with a tick every 1000
        timer = new CountDownTimer(secondCountDown * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                secondsRemaining = "" + millisUntilFinished / 1000;
                circularFillableCount += (100 / secondCountDown);
                BubbleSort.setTimerGraphic(circularFillableCount);
            }

            public void onFinish() {
                if (BubbleSort.isSorted(BubbleSort.buildArray())) {
                    secondsRemaining = 0 + "";
                    BubbleSort.showDialog(true);
                } else {
                    secondsRemaining = 0 + "";
                    BubbleSort.showDialog(false);
                }
            }
        }.start();
    }

    public static String getSecondsRemaining(){
        return secondsRemaining;
    }

    public static void cancel(){
        timer.cancel();
    }

}
