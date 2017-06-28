package com.example.harishmurari.curries;

/**
 * Created by harishmurari on 6/5/2017.
 */

public class MyData {

    //main list items
    public static String[] mainmenuArray = {"Vegetarian", "Non-Vegetarian", " White Rice", "Lunch-Box"};

    public static final int VEGETARIAN = 0;
    public static final int NON_VEGETARIAN = 1;
    public static final int WHITE_RICE = 2;
    public static final int LUNCH_BOX = 3;

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
                    "Thali",


            };

    public static double [] curryPrice =
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
                    R.drawable.tomato_dal,
                    R.drawable.tomato_curry,
                    R.drawable.egg_curry,
                    R.drawable.bendakai_fry,
                    R.drawable.egg_masala,
                    R.drawable.veg_manchurian,

                    //non-veg
                    R.drawable.chicken_fry,
                    R.drawable.chicken_curry,
                    R.drawable.fish_pulusu,
                    R.drawable.chicken_65,
                    R.drawable.mutton_curry,

                    //rice bowl
                    R.drawable.white_rice_small,
                    R.drawable.white_rice_medium,
                    R.drawable.white_rice_jumbo,

                    //thali
                    R.drawable.lunch_box,
                    R.drawable.thali
            };

    public static String[] curryDescription =
            {
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
            };
    public static String[] nonVegCurriesArray =
            {
                    "Chicken Fry",
                    "Chicken Curry",
                    "Fish Curry",
                    "Chicken 65",
                    "Mutton Curry"
            };

    public static double[] nonVegPrice =
            {
                    25,
                    25,
                    35,
                    30,
                    50
            };

    public static Integer[] nonVegDrawableArray =
            {
                    R.drawable.chicken_fry,
                    R.drawable.chicken_curry,
                    R.drawable.fish_pulusu,
                    R.drawable.chicken_65,
                    R.drawable.mutton_curry
            };

    public static String[] whiteRiceArray =
            {
                    "Small Bowl",
                    "Large Bowl",
                    "Jumbo Bowl"
            };

    public static double[] riceprice =
            {
                    20,
                    30,
                    50
            };

    public static Integer[] riceDrawableArray =
            {
                    R.drawable.white_rice_small,
                    R.drawable.white_rice_medium,
                    R.drawable.white_rice_jumbo
            };

    public static String[] lunchBoxArray =
            {
                    "Lunch Box",
                    "Thali"
            };

    public static double[] lunchprice =
            {
                    50,
                    75
            };

    public static Integer[] lunchDrawableArray =
            {
                    R.drawable.lunch_box,
                    R.drawable.thali
            };

    }
