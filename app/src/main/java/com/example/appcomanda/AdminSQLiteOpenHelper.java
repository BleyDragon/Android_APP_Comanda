package com.example.appcomanda;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.*;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE clientes(documento integer primary key, nombre text, correo text, telefono integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS clientes");
        db.execSQL("CREATE TABLE clientes (documento integer primary key, nombre text,telefono integer)");
    }
}
