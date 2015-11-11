package com.meyourours.cito.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by norman on 8/19/15.
 */
public class TagsDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tags.db";
    private static final int VERSION = 1;

    public TagsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TagsTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        TagsTable.onUpgrade(db, oldVersion, newVersion);
    }
}
