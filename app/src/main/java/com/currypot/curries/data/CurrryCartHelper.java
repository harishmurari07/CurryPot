package com.currypot.curries.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by kaika on 6/28/2017.
 * Helper class for some CurryProvider operations
 */



public class CurrryCartHelper {

    /**
     * Check out all items in the shopping cart and save them to the purchase history
     * @param context the context to use for the content resolver
     */
    public static void checkoutCart(Context context) {

        String[] projection = {
                CurryContract.CurryEntry._ID,
                CurryContract.CurryEntry.COLUMN_CURRY_NAME,
                CurryContract.CurryEntry.COLUMN_CURRY_PRICE,
                CurryContract.CurryEntry.COLUMN_CART_QUANTITY
        };

        String selection = CurryContract.CurryEntry.COLUMN_CART_QUANTITY + " > 0";
        Cursor query = context.getContentResolver().query(
                CurryContract.CurryEntry.CONTENT_URI,
                projection,
                selection,
                null,
                null);
        if (query == null) {
            return;
        }

        while (query.moveToNext()) {
            int cartQuantity = query.getInt(query.getColumnIndexOrThrow(CurryContract.CurryEntry.COLUMN_CART_QUANTITY));
            ContentValues purchaseValues = new ContentValues();
            purchaseValues.put(CurryContract.CurryEntry.COLUMN_CART_QUANTITY, 0);
            purchaseValues.put(CurryContract.CurryEntry.COLUMN_PURCHASED_QUANTITY, cartQuantity);
            String where = CurryContract.CurryEntry._ID + "= ?";
            String[] selectionArgs = {query.getString(query.getColumnIndexOrThrow(CurryContract.CurryEntry._ID))};
            context.getContentResolver().update(CurryContract.CurryEntry.CONTENT_URI, purchaseValues, where, selectionArgs);
        }

        query.close();
    }

    /**
     * Remove all quantities from the cart for a specific item
     *
     * @param context the context to use for the content resolver
     * @param itemId  the specific item id to remove from the cart
     */
    public static void removeFromCart(Context context, int itemId) {
        ContentValues values = new ContentValues();
        values.put(CurryContract.CurryEntry.COLUMN_CART_QUANTITY, 0);
        context.getContentResolver().update(
                CurryContract.CurryEntry.CONTENT_URI,
                values,
                CurryContract.CurryEntry._ID + " = " + itemId,
                null);

    }

    /**
     * Subtract a specified quantity of items from the cart
     * @param context the context to use for the content resolver
     * @param itemId the specific item id to subtract from the cart
     * @param subtractQuantity the number of items to subtract
     */
    public static void subtractCartQuantity(Context context, int itemId, int subtractQuantity) {
        int quantity = getCartQuantity(context, itemId);
        ContentValues values = new ContentValues();
        values.put(CurryContract.CurryEntry.COLUMN_CART_QUANTITY, Math.max(1, quantity - subtractQuantity));
        context.getContentResolver().update(
                CurryContract.CurryEntry.CONTENT_URI,
                values,
                CurryContract.CurryEntry._ID + " = " + itemId,
                null);
    }

    /**
     * Adds a specified quantity of items to the cart
     * @param context the context to use for the content resolver
     * @param itemId the specific item id to add to the cart
     * @param addQuantity the number of items to add
     */
    public static void addCartQuantity(Context context, int itemId, int addQuantity) {
        int quantity = getCartQuantity(context, itemId);
        ContentValues values = new ContentValues();
        values.put(CurryContract.CurryEntry.COLUMN_CART_QUANTITY, quantity + addQuantity);
        context.getContentResolver().update(
                CurryContract.CurryEntry.CONTENT_URI,
                values,
                CurryContract.CurryEntry._ID + " = " + itemId,
                null);
    }

    /**
     * Removes an item from the list of purchases
     * @param context the context to use for the content resolver
     * @param itemId the specific item to remove from the purchase list
     */
    public static void removePurchase(Context context, int itemId) {
        ContentValues values = new ContentValues();
        values.put(CurryContract.CurryEntry.COLUMN_PURCHASED_QUANTITY, 0);
        context.getContentResolver().update(
                CurryContract.CurryEntry.CONTENT_URI,
                values,
                CurryContract.CurryEntry._ID + " = " + itemId,
                null);
    }

    /**
     * Returns the number of items in the cart for a specific item
     * @param context the context to use for the content resolver
     * @param itemId the specific item id
     * @return the cart quantity for the item with id itemId
     */
    private static int getCartQuantity(Context context, int itemId) {
        String[] projection = {
                CurryContract.CurryEntry.COLUMN_CART_QUANTITY
        };
        Cursor cursor = context.getContentResolver().query(
                CurryContract.CurryEntry.createCurryUriWithId(itemId),
                projection,
                null,
                null,
                null);

        if (cursor == null) {
            throw new IllegalArgumentException(itemId + " not found");
        }

        cursor.moveToFirst();
        int cartQuantity = cursor.getInt(cursor.getColumnIndexOrThrow(CurryContract.CurryEntry.COLUMN_CART_QUANTITY));
        cursor.close();
        return cartQuantity;
    }
}
