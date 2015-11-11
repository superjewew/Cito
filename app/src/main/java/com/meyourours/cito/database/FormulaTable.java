package com.meyourours.cito.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by norman on 7/30/15.
 */
public class FormulaTable {

    // Database Table
    public static final String TABLE_FORMULA = "formula";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_USE_COUNT = "usecount";
    public static final String COLUMN_USE_TIME = "usetime";
    public static final String COLUMN_TAG = "TAG";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_FORMULA
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_CATEGORY + " text not null, "
            + COLUMN_USE_COUNT + " integer, "
            + COLUMN_USE_TIME + " integer, "
            + COLUMN_TAG + " text);";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(FormulaTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_FORMULA);
        onCreate(database);
    }
}
