package com.luisftec.proyectoapp.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(Context context){

        super(context, "MASCOTERIA.db", null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query;
        query = "CREATE TABLE especies("+
                "esp_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "esp_nombre TEXT NOT NULL);";
        db.execSQL(query);
        query = "INSERT INTO especies VALUES(NULL, 'CANINOS');";
        db.execSQL(query);
        query = "INSERT INTO especies VALUES(NULL, 'FELINOS');";
        db.execSQL(query);
        query = "INSERT INTO especies VALUES(NULL, 'OTROS');";
        db.execSQL(query);
        query = "CREATE TABLE MASCOTAS("+
                "masc_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "masc_nombre TEXT NOT NULL,"+
                "masc_edad INTEGER NOT NULL,"+
                "masc_color TEXT NOT NULL,"+
                "masc_genero TEXT NOT NULL,"+
                "masc_fecha INTEGER NOT NULL,"+
                "esp_id INTEGER NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
