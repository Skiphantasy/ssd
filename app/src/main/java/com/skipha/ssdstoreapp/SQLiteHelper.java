package com.skipha.ssdstoreapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper  extends SQLiteOpenHelper {
    // Table Names
    private static final String TABLE_SSD = "ssd";
    private static final String TABLE_RECEIPTS = "receipts";
    private static final String TABLE_ITEMS = "items";

    private static final String CREATE_TABLE_SSD = "CREATE TABLE "
            + TABLE_SSD + "(nombre TEXT, precio INTEGER, cantidad INTEGER)";
    private static final String CREATE_TABLE_ITEMS= "CREATE TABLE "
            + TABLE_ITEMS + "(nombre TEXT, precio INTEGER, cantidad INTEGER, codfactura INTEGER)";
    private static final String CREATE_TABLE_RECEIPTS = "CREATE TABLE "
            + TABLE_RECEIPTS + "(codfactura INTEGER)";

    public SQLiteHelper(Context contexto, String nombre,
                      SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_SSD);
        db.execSQL(CREATE_TABLE_RECEIPTS);
        db.execSQL(CREATE_TABLE_ITEMS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior,
                          int versionNueva) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SSD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIPTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }
}

