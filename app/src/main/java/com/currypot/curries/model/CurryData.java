package com.currypot.curries.model;


import com.currypot.curries.R;

/**
 * Created by harishmurari on 6/5/2017.
 */

public class CurryData {

    public static final int PALAKURA_PAPPU = 0;
    public static final int TOMATO_CURRY = 1;
    public static final int EGG_CURRY = 2;
    public static final int BENDAKAYA_FRY = 3;
    public static final int EGG_MASALA = 4;
    public static final int VEG_MANCHURIA = 5;

    //Non- veg
    public static final int CHICKEN_FRY = 6;
    public static final int CHKEN_CURRY = 7;
    public static final int FISH_CURRY = 8;
    public static final int CHICKEN_65 = 9;
    public static final int MUTTON_CURRY = 10;

    //Rice- bowl
    public static final int SMALL_BOWL = 11;
    public static final int LARGE_BOWL = 12;
    public static final int JUMBO_BOWL = 13;

    //lunch box
    public static final int LUNCH_BOX = 14;
    public static final int THALI = 15;


    //3 arrays should be in same order
    public static String[] curriesArray =
            {
                    "Palakura Pappu",
                    "Tomato Curry",
                    "Egg Curry",
                    "Bendakaya Fry",
                    "Egg Masala Curry",
                    "Veg Manchuria",

                    //non-veg curries
                    "Chicken Fry",
                    "Chicken Curry",
                    "Fish Curry",
                    "Chicken 65",
                    "Mutton Curry",

                    //rice Bowl
                    "Small Bowl",
                    "Large Bowl",
                    "Jumbo Bowl",

                    //thali
                    "Lunch Box",
                    "Thali"


            };

    public static double[] curryPrice =
            {
                    15,
                    15,
                    25,
                    25,
                    35,
                    20,

                    // non-veg prices
                    25,
                    25,
                    35,
                    30,
                    50,

                    // rice bowl
                    20,
                    30,
                    50,

                    //thali
                    50,
                    75,


            };

    public static Integer[] drawableArray =
            {
                    PALAKURA_PAPPU,
                    TOMATO_CURRY,
                    EGG_CURRY,
                    BENDAKAYA_FRY,
                    EGG_MASALA,
                    VEG_MANCHURIA,

                    //non-veg
                    CHICKEN_FRY,
                    CHKEN_CURRY,
                    FISH_CURRY,
                    CHICKEN_65,
                    MUTTON_CURRY,

                    //rice bowl
                    SMALL_BOWL,
                    LARGE_BOWL,
                    JUMBO_BOWL,

                    //thali
                    LUNCH_BOX,
                    THALI
            };

    public static String[] curryDescription =
            {
                    "Chicken cooked in a traditional Indian curry sauce",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "Chicken cooked in a traditional Indian curry sauce",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
            };

    public static int getResourceDrawable(int id) {

        if (PALAKURA_PAPPU == id) {
            return R.drawable.tomato_dal;
        } else if (TOMATO_CURRY == id) {
            return R.drawable.tomato_curry;
        } else if (EGG_CURRY == id) {
            return R.drawable.egg_curry;
        } else if (BENDAKAYA_FRY == id) {
            return R.drawable.bendakai_fry;
        } else if (EGG_MASALA == id) {
            return R.drawable.egg_masala;
        } else if (VEG_MANCHURIA == id) {
            return R.drawable.veg_manchurian;
        } else if (CHICKEN_FRY == id) {
            return R.drawable.chicken_fry;
        } else if (CHKEN_CURRY == id) {
            return R.drawable.chicken_curry;
        } else if (FISH_CURRY == id) {
            return R.drawable.fish_pulusu;
        } else if (CHICKEN_65 == id) {
            return R.drawable.chicken_65;
        } else if (MUTTON_CURRY == id) {
            return R.drawable.mutton_curry;
        } else if (SMALL_BOWL == id) {
            return R.drawable.white_rice_small;
        } else if (LARGE_BOWL == id) {
            return R.drawable.white_rice_medium;
        } else if (JUMBO_BOWL == id) {
            return R.drawable.white_rice_jumbo;
        } else if (LUNCH_BOX == id) {
            return R.drawable.lunch_box;
        } else if (THALI == id) {
            return R.drawable.thali;
        }
        return -1;
    }
}
