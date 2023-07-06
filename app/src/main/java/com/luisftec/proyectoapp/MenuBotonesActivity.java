package com.luisftec.proyectoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuBotonesActivity extends AppCompatActivity {

    Button btnListar, btnMapa, btnOtros, btnTurno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_botones);

        // referencia al boton de listar
        btnListar = findViewById(R.id.btnListar);

        // listener para el boton de listar
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // accion cuando se presiona el boton de listar

                // abrir la pantalla de listar para ver la informacion
                startActivity(new Intent(MenuBotonesActivity.this, ListarActivity.class));
            }
        });

        // referencia al boton de otros
        btnOtros = findViewById(R.id.btnOtros);

        // listener para el boton de otros utilizando lambda
        btnOtros.setOnClickListener(v -> {
            // accion cuando se presiona el boton de otros

            // abrir la pantalla de otros para explorar opciones adicionales
            startActivity(new Intent(this, MapaLatLonActivity.class));
        });

        // asignar referencia al boton de mapa
        asignarReferencia();
        // Referencia al botón de turno
        btnTurno = findViewById(R.id.btnTurno);

        // Listener para el botón de turno utilizando lambda
        btnTurno.setOnClickListener(v -> {
            // Acción cuando se presiona el botón de turno

            // Abrir la pantalla de turno
            startActivity(new Intent(this, TurnoActivity.class));
        });
    }

    // metodo para asignar referencia al boton de mapa
    private void asignarReferencia() {
        // referencia al boton de mapa
        btnMapa = findViewById(R.id.btnMapa);

        // listener para el boton de mapa utilizando lambda
        btnMapa.setOnClickListener(v -> {
            // accion cuando se presiona el boton de mapa

            // abrir la pantalla de mapa para visualizar la ubicacion
            startActivity(new Intent(this, MapaActivity.class));
        });
    }
}
