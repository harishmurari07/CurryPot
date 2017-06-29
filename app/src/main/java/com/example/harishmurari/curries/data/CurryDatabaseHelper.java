package com.example.harishmurari.curries.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.harishmurari.curries.MyData;

/**
 * Created by kaika on 6/28/2017.
 */

public class CurryDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "curries.db";
    private static final int DATABASE_VERSION = 1;

    private Context context;

    public CurryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_CURRY_TABLE = "CREATE TABLE " + CurryContract.CurryEntry.TABLE_NAME + " ("
                + CurryContract.CurryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CurryContract.CurryEntry.COLUMN_CURRY_NAME + " TEXT NOT NULL, "
                + CurryContract.CurryEntry.COLUMN_CURRY_DESCRIPTION + " TEXT NOT NULL, "
                + CurryContract.CurryEntry.COLUMN_CURRY_PRICE + " INTEGER NOT NULL, "
                + CurryContract.CurryEntry.COLUMN_CURRY_IMAGE + " INTEGER NOT NULL, "
                + CurryContract.CurryEntry.COLUMN_CART_QUANTITY + " INTEGER NOT NULL, "
                + CurryContract.CurryEntry.COLUMN_PURCHASED_QUANTITY + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_CURRY_TABLE);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        if (db.isReadOnly()) {
            return;
        }

        Cursor cursor = db.query(CurryContract.CurryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            return;
        }
        cursor.close();

        String[] curryNames = MyData.curriesArray;
        String[] curryDescription = MyData.curryDescription;
        double[] curryPrice = MyData.curryPrice;
        Integer[] curryImages = MyData.drawableArray;

        if (curryNames.length != curryDescription.length
                && curryDescription.length != curryPrice.length) {
            throw new IllegalStateException("There should be an equal number of curry names/descriptions/prices");
        }

        for (int i= 0; i < curryNames.length; i++) {
            ContentValues values = new ContentValues();
            values.put(CurryContract.CurryEntry.COLUMN_CURRY_NAME, curryNames[i]);
            values.put(CurryContract.CurryEntry.COLUMN_CURRY_DESCRIPTION, curryDescription[i]);
            values.put(CurryContract.CurryEntry.COLUMN_CURRY_PRICE, curryPrice[i]);
            values.put(CurryContract.CurryEntry.COLUMN_CURRY_IMAGE, curryImages[i]);
            values.put(CurryContract.CurryEntry.COLUMN_CART_QUANTITY, 0);
            values.put(CurryContract.CurryEntry.COLUMN_PURCHASED_QUANTITY, 0);


            db.insert(CurryContract.CurryEntry.TABLE_NAME, null, values);
        }

        context.getContentResolver().notifyChange(CurryContract.CurryEntry.CONTENT_URI, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + CurryContract.CurryEntry.TABLE_NAME);
        onCreate(db);
    }
}
