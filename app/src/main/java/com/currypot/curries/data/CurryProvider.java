package com.currypot.curries.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by kaika on 6/28/2017.
 */

public class CurryProvider extends ContentProvider{

    private static final String TAG = CurryProvider.class.getSimpleName();

    private CurryDatabaseHelper databaseHelper;

    public static final int MATCH_CURRY = 100;
    public static final int MATCH_CURRY_ID = 101;

    private static final UriMatcher uriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CurryContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, CurryContract.PATH_CURRY, MATCH_CURRY);
        matcher.addURI(authority, CurryContract.PATH_CURRY + "/#", MATCH_CURRY_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        databaseHelper = new CurryDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;

        switch (uriMatcher.match(uri)) {
            case MATCH_CURRY:
                cursor = databaseHelper.getReadableDatabase().query(
                        CurryContract.CurryEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null /* groupBy */,
                        null /* having */,
                        sortOrder);
                break;
            case MATCH_CURRY_ID:
                String plantId = CurryContract.CurryEntry.getIdFromUri(uri);
                cursor = databaseHelper.getReadableDatabase().query(
                        CurryContract.CurryEntry.TABLE_NAME,
                        projection,
                        CurryContract.CurryEntry._ID + " = " + plantId,
                        selectionArgs,
                        null /* groupBy */,
                        null /* having */,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case MATCH_CURRY:
                return CurryContract.CurryEntry.CONTENT_LIST_TYPE;
            case MATCH_CURRY_ID:
                return CurryContract.CurryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = uriMatcher.match(uri);
        long id;
        switch (match) {
            case MATCH_CURRY:
                id = databaseHelper.getWritableDatabase()
                        .insert(CurryContract.CurryEntry.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Insert is not supported for " + uri);
        }
        if (id == -1) {
            Log.e(TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int rowsDeleted;

        final int match = uriMatcher.match(uri);
        switch (match) {
            case MATCH_CURRY:
                rowsDeleted = database.delete(
                        CurryContract.CurryEntry.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String where, @Nullable String[] whereArgs) {
        final SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int rowsUpdated;

        final int match = uriMatcher.match(uri);
        switch (match) {
            case MATCH_CURRY:
                rowsUpdated = database.update(
                        CurryContract.CurryEntry.TABLE_NAME,
                        values,
                        where,
                        whereArgs);
                break;
            default:
                throw new IllegalArgumentException("Updating is not supported for " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}
