package com.example.harishmurari.curries.model;

/**
 * Created by harishmurari on 6/5/2017.
 */

public class CurryItem {

    private String curryName;
    private double curryPrice;
    private int curryImage;

    public CurryItem(String curryName, double curryPrice, int curryImage) {
        this.curryName = curryName;
        this.curryPrice = curryPrice;
        this.curryImage = curryImage;
    }

    public String getCurryName() {
        return curryName;
    }

    public double getCurryPrice() { return curryPrice; }

    public int getCurryImage() {
        return curryImage;
    }
}