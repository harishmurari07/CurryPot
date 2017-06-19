package com.example.harishmurari.curries;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

/**
 * Created by harishmurari on 6/17/2017.
 */

public class Utility {

    private static final String BUTTON_STATE_KEY = "button-state";

    public static void saveButtonState(Context context, int selectedbutton) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(BUTTON_STATE_KEY, selectedbutton);
        editor.apply();
    }
    public static int getButtonState(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(BUTTON_STATE_KEY, 0);
    }
}