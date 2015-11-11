package com.meyourours.cito.contentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.meyourours.cito.database.FormulaDatabaseHelper;
import com.meyourours.cito.database.FormulaTable;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Norman on 7/30/15. Responsible for handling Formula Database
 */
public class FormulaContentProvider extends ContentProvider {

    // Database
    private FormulaDatabaseHelper database;

    // Used for the URI matcher
    private static final int FORMULAS = 10;
    private static final int FORMULA_ID = 20;

    private static final String AUTHORITY = "com.meyourours.cito.contentprovider";
    private static final String BASE_PATH = "formulas";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/formulas";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/formula";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, FORMULAS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", FORMULA_ID);
    }

    @Override
    public boolean onCreate() {
        database = new FormulaDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        checkColumns(projection);

        queryBuilder.setTables(FormulaTable.TABLE_FORMULA);

        int uriType = sURIMatcher.match(uri);
        switch(uriType) {
            case FORMULAS:
                break;
            case FORMULA_ID:
                queryBuilder.appendWhere(FormulaTable.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        long id = 0;
        switch(uriType) {
            case FORMULAS:
                id = sqlDB.insert(FormulaTable.TABLE_FORMULA, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        Log.d("FORM_CONTENT_PROVIDER", "Inserted new db at row " + id);
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case FORMULAS:
                rowsDeleted = sqlDB.delete(FormulaTable.TABLE_FORMULA, selection,
                        selectionArgs);
                break;
            case FORMULA_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(FormulaTable.TABLE_FORMULA,
                            FormulaTable.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(FormulaTable.TABLE_FORMULA,
                            FormulaTable.COLUMN_ID + "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case FORMULAS:
                rowsUpdated = sqlDB.update(FormulaTable.TABLE_FORMULA,
                        values,
                        selection,
                        selectionArgs);
                break;
            case FORMULA_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(FormulaTable.TABLE_FORMULA,
                            values,
                            FormulaTable.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(FormulaTable.TABLE_FORMULA,
                            values,
                            FormulaTable.COLUMN_ID + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    private void checkColumns(String[] projection) {
        String[] available = { FormulaTable.COLUMN_NAME,
                FormulaTable.COLUMN_CATEGORY, FormulaTable.COLUMN_USE_TIME, FormulaTable.COLUMN_USE_COUNT, FormulaTable.COLUMN_TAG,
                FormulaTable.COLUMN_ID };
        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
            // check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException("Unknown columns in projection");
            }
        }
    }

}
