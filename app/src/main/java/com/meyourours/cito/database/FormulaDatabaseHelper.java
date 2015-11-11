package com.meyourours.cito.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by norman on 7/30/15.
 */
public class FormulaDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "formula.db";
    private static final int VERSION = 1;

    public FormulaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        FormulaTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        FormulaTable.onUpgrade(db, oldVersion, newVersion);
    }
}
