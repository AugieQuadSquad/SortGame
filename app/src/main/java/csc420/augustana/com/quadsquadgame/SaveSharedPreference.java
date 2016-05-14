package csc420.augustana.com.quadsquadgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * The SaveSharedPreference class uses the context passes in as parameters and
 * saves the values into Shared Preferences using the global variables as the
 * key for the Shared Preferences
 *
 * @author Devon White, Michael Currie, Luke Currie, Catherine Cross
 * @since 5/11/2016
 */
public class SaveSharedPreference {
    static final String PREF_BUBBLE_HIGHSCORE = "Bubble Highscore";
    static final String PREF_INSERTION_HIGHSCORE = "Insertion Highscore";
    static final String PREF_SELECTION_HIGHSCORE = "Selection Highscore";

    /**
     * The following methods get or set the shared preference values for the
     * high scores for each of the sorts.
     *
     * @param ctx is the context of the activity that called the method
     * @return List<Pairs> This returns
     */
    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static int getPrefBubbleHighscore(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_BUBBLE_HIGHSCORE, 0);
    }

    public static int getPrefInsertionHighscore(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_INSERTION_HIGHSCORE, 0);
    }

    public static int getPrefSelectionHighscore(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_SELECTION_HIGHSCORE, 0);
    }

    public static void setPrefBubbleHighscore(Context ctx, int token) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_BUBBLE_HIGHSCORE, token);
        editor.apply();
    }

    public static void setPrefInsertionHighscore(Context ctx, int token) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_INSERTION_HIGHSCORE, token);
        editor.apply();
    }

    public static void setPrefSelectionHighscore(Context ctx, int token) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_SELECTION_HIGHSCORE, token);
        editor.apply();
    }
}
