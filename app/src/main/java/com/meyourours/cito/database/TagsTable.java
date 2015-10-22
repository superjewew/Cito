package com.meyourours.cito.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by norman on 8/19/15.
 */
public class TagsTable {

    public static final String TABLE_TAGS = "tags";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TAGS = "tags";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_TAGS
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TAGS + " text);";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(FormulaTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_TAGS);
        onCreate(database);
    }
}
