package com.luisftec.proyectoapp.util;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.luisftec.proyectoapp.entidad.Mascotas;
import java.util.ArrayList;
import java.util.List;

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
            valores.put("masc_raza",mascotas.getMasc_raza());
            valores.put("masc_edad",mascotas.getMasc_edad());
            valores.put("masc_genero",mascotas.getMasc_genero());
            valores.put("masc_fecha", mascotas.getMasc_fecha());
            valores.put("esp_id", mascotas.getEsp_id());
            long resultado = sqlDB.insert("MASCOTAS",null,valores);
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

    public String modificarMascota(Mascotas mascotas){

        String mensaje = " ";
        try{
            ContentValues valores = new ContentValues();
            valores.put("masc_nombre",mascotas.getMasc_nombre());
            valores.put("masc_color",mascotas.getMasc_color());
            valores.put("masc_raza",mascotas.getMasc_raza());
            valores.put("masc_edad",mascotas.getMasc_edad());
            valores.put("masc_genero",mascotas.getMasc_genero());
            valores.put("masc_fecha", mascotas.getMasc_fecha());
            valores.put("esp_id", mascotas.getEsp_id());
            long resultado = sqlDB.update("MASCOTAS", valores,"masc_id="+mascotas.getMasc_id(),null);
            if (resultado==-1){
                mensaje = " Error al modificar Mascota";
            }else {
                mensaje= "Mascota se modifico conrrectamente";
            }
        }catch (Exception e){
            Log.d("==>",e.toString());
        }
        return mensaje;
    }
    public List<Mascotas> cargarMascota(){
        List<Mascotas> listaMascotas = new ArrayList<>();
        try {
            //ejecuta y devueleve la tabla o similar
            Cursor c = sqlDB.rawQuery("SELECT * FROM mascotas",null);
            while (c.moveToNext()){
                listaMascotas.add(new Mascotas(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getInt(4),
                        c.getString(5),
                        c.getString(6),
                        c.getInt(7)));
            }
        }catch (Exception e){
            Log.d("==>",e.toString());
        }
        return  listaMascotas;
    }

}
