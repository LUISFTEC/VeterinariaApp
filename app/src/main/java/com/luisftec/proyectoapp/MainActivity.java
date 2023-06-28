package com.luisftec.proyectoapp;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.luisftec.proyectoapp.entidad.Especies;
import com.luisftec.proyectoapp.util.DAOEspecies;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spEspecies;
    ImageView ivFlecha;

    List<Especies> listaEspecies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
        cargarEspecies();
        agregarEventoFlecha();

        spEspecies.setOnTouchListener((v, event) -> {
            return true; // Evitar que el Spinner maneje el evento táctil
        });
    }

    private  void  asignarReferencias(){

        spEspecies = findViewById(R.id.spEspecies);
        ivFlecha = findViewById(R.id.ivFlecha);

    }

    private void cargarEspecies(){
        DAOEspecies daoEspecies = new DAOEspecies(this);
        daoEspecies.abrirBD();
        listaEspecies = daoEspecies.cargarEspecies();

        ArrayAdapter<Especies> adaptador = new ArrayAdapter<>(this,R.layout.spinner_item_luisftec, listaEspecies);
        spEspecies.setAdapter(adaptador);

    }

    private void agregarEventoFlecha() {
        ivFlecha.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // Acción que deseas realizar al tocar la flecha
                spEspecies.performClick(); // Desplegar el Spinner
                return true;
            }
            return false;
        });
    }
}