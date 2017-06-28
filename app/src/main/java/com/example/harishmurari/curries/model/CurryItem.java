package com.example.harishmurari.curries.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.harishmurari.curries.data.CurryContract;

/**
 * Created by harishmurari on 6/5/2017.
 */

public class CurryItem {

    public int id;
    public String curryName;
    public String curryDescription;
    public int curryPrice;
    public int cartQuantity;
    public int purchasedQuantity;

    public CurryItem(int id, String curryName, int curryPrice, String curryDescription, int cartQuantity, int purchasedQuantity) {
        this.id = id;
        this.curryName = curryName;
        this.curryDescription = curryDescription;
        this.curryPrice = curryPrice;
        this.cartQuantity = cartQuantity;
        this.purchasedQuantity = purchasedQuantity;
    }

    public CurryItem(Cursor c) {
        int columnId = c.getColumnIndex(CurryContract.CurryEntry._ID);
        int columnName = c.getColumnIndex(CurryContract.CurryEntry.COLUMN_CURRY_NAME);
        int columnDescription = c.getColumnIndex(CurryContract.CurryEntry.COLUMN_CURRY_DESCRIPTION);
        int columnPrice = c.getColumnIndex(CurryContract.CurryEntry.COLUMN_CURRY_PRICE);
        int columnCartQuantity = c.getColumnIndex(CurryContract.CurryEntry.COLUMN_CART_QUANTITY);
        int columnPurchasedQuantity = c.getColumnIndex(CurryContract.CurryEntry.COLUMN_PURCHASED_QUANTITY);

        if (c.getCount() == 0) {
            return;
        }

        if (columnId != -1) {
            this.id = c.getInt(columnId);
        }
        if (columnName != -1) {
            this.curryName = c.getString(columnName);
        }
        if (columnDescription != -1) {
            this.curryDescription = c.getString(columnDescription);
        }
        if (columnPrice != -1) {
            this.curryPrice = c.getInt(columnPrice);
        }
        if (columnCartQuantity != -1) {
            this.cartQuantity = c.getInt(columnCartQuantity);
        }
        if (columnPurchasedQuantity != -1) {
            this.purchasedQuantity = c.getInt(columnPurchasedQuantity);
        }
    }

}