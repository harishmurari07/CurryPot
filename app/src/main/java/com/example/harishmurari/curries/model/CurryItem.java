package com.example.harishmurari.curries.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by harishmurari on 6/5/2017.
 */

public class CurryItem implements Parcelable{

    private String curryName;
    private double curryPrice;
    private int curryImage;

    public CurryItem(String curryName, double curryPrice, int curryImage) {
        this.curryName = curryName;
        this.curryPrice = curryPrice;
        this.curryImage = curryImage;
    }

    protected CurryItem(Parcel in) {
        curryName = in.readString();
        curryPrice = in.readDouble();
        curryImage = in.readInt();
    }

    public static final Creator<CurryItem> CREATOR = new Creator<CurryItem>() {
        @Override
        public CurryItem createFromParcel(Parcel in) {
            return new CurryItem(in);
        }

        @Override
        public CurryItem[] newArray(int size) {
            return new CurryItem[size];
        }
    };

    public String getCurryName() {
        return curryName;
    }

    public double getCurryPrice() { return curryPrice; }

    public int getCurryImage() {
        return curryImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(curryName);
        dest.writeDouble(curryPrice);
        dest.writeInt(curryImage);
    }
}