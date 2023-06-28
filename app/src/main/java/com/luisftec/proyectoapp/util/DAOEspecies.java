package com.luisftec.proyectoapp.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.luisftec.proyectoapp.entidad.Especies;

import java.util.ArrayList;
import java.util.List;

public class DAOEspecies {
    BaseDatos bd;
    SQLiteDatabase sqlBD;
    Context context;


    public DAOEspecies(Context context){
        this.context = context;
        bd = new BaseDatos(context);

    }

    public void abrirBD(){
        sqlBD = bd.getWritableDatabase();
    }

    public List<Especies>  cargarEspecies(){
        List<Especies> listaEspecies = new ArrayList<>();
        try {
            Cursor c = sqlBD.rawQuery("SELECT * FROM especies",null);
            while (c.moveToNext()){
                listaEspecies.add(new Especies(c.getInt(0),c.getString(1)));
            }
        }catch (Exception e){
            Log.d("==>",e.toString());
        }

        return listaEspecies;
    }
}
