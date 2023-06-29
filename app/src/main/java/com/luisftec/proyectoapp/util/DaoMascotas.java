package com.luisftec.proyectoapp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.luisftec.proyectoapp.entidad.Mascotas;

public class DaoMascotas {

    BaseDatos bd;
    SQLiteDatabase sqlDB;
    Context context;

    public  DaoMascotas(Context context){
        this.context=context;
        bd = new BaseDatos(context);

    }
    public void abrirBD(){

        sqlDB = bd.getWritableDatabase();
    }

    public String registrarMascota(Mascotas mascotas){

        String mensaje = " ";

        try{
            ContentValues valores = new ContentValues();
            valores.put("masc_nombre",mascotas.getMasc_nombre());
            valores.put("masc_color",mascotas.getMasc_color());
            valores.put("masc_genero",mascotas.getMasc_genero());
            valores.put("masc_fecha", mascotas.getMasc_fecha());
            valores.put("esp_id ", mascotas.getEsp_id());
            long resultado = sqlDB.insert("especies",null,valores);
            if (resultado==-1){
                mensaje = " Error del registro de la Mascota";
            }else {
                mensaje= "Mascota se registro conrrectamente";
            }

        }catch (Exception e){
            Log.d("==>",e.toString());
        }
        return mensaje;
    }

}
