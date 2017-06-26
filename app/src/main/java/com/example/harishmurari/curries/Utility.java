package com.example.harishmurari.curries;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.harishmurari.curries.model.CurryItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by harishmurari on 6/17/2017.
 */

public class Utility {

    private static final String TAG = Utility.class.getSimpleName();

    private static final String BUTTON_STATE_KEY = "button-state";
    public static String PREFERENCE_FILE = "cart_items_prefs";
    private static Gson gson;


    private static SharedPreferences getSharedpref(Context ctx){
        return ctx.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(SharedPreferences preferences) {
        return preferences.edit();
    }

    private static Gson getGson() {
        if (gson == null) {
            return new Gson();
        }
        return gson;
    }
    public static void saveToCart(Context context, String key, CurryItem curryItem) {
        SharedPreferences preferences = getSharedpref(context);
        SharedPreferences.Editor editor = getEditor(preferences);

        gson = getGson();
        String json = gson.toJson(curryItem);
        editor.putString(key, json);
        editor.apply();
        Log.d(TAG, "Added : " + key + " to cart");
    }

    public static void removeFromCart(Context context, String key) {
        SharedPreferences preferences = getSharedpref(context);
        SharedPreferences.Editor editor = getEditor(preferences);
        editor.remove(key);
        editor.apply();
        Log.d(TAG, "Removed : " + key + " from cart");
    }

    public static ArrayList<CurryItem> retriveCart(Context context) {
        ArrayList<CurryItem> cartItems = new ArrayList<>();
        SharedPreferences preferences = getSharedpref(context);
        Map<String, ?> entries = preferences.getAll();
        Set<String> curriesInCart = entries.keySet();
        CurryItem item;

        gson = getGson();
        Type type = new TypeToken<ArrayList<CurryItem>>(){}.getType();
        for (String curry : curriesInCart) {
            String json = preferences.getString(curry, "");
            item = gson.fromJson(json, CurryItem.class);
            cartItems.add(item);
        }
        return cartItems;
    }

    public static boolean isItemAvailable(Context context, CurryItem item) {
        ArrayList<CurryItem> cartItems = retriveCart(context);
        return cartItems.contains(item);
    }

    public static void clear(Context context) {
        getSharedpref(context).edit().clear().apply();
    }
}