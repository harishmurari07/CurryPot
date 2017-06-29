package com.example.harishmurari.curries.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by kaika on 6/28/2017.
 */

public class CurryContract {

    private CurryContract() {}

    public static final String CONTENT_AUTHORITY = "com.example.harishmurari.curries";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_CURRY = "curry";

    public static final class CurryEntry implements BaseColumns {

        /**
         * The content URI to access the plant data in the provider
         */
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_CURRY)
                .build();
        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of plants.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CURRY;
        /**
         * The MIME type of the {@link #CONTENT_URI} for a single plant.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CURRY;

        /**
         * Name of plant database table.
         */
        public final static String TABLE_NAME = "curries";

        /**
         * Columns of plant database table.
         * _id is implied as a subclass of BaseColumns.
         */
        public static final String COLUMN_CURRY_NAME = "name";
        public static final String COLUMN_CURRY_DESCRIPTION = "description";
        public static final String COLUMN_CURRY_PRICE = "price";
        public static final String COLUMN_CART_QUANTITY = "cart_quantity";
        public static final String COLUMN_PURCHASED_QUANTITY = "purchased_quantity";
        public static final String COLUMN_CURRY_IMAGE = "curry_image";

        public static String getIdFromUri(Uri uri) {
            return uri.getLastPathSegment();
        }

        public static Uri createCurryUriWithId(int itemId) {
            return ContentUris.withAppendedId(CONTENT_URI, itemId);
        }
    }
}
