package com.luisftec.proyectoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luisftec.proyectoapp.entidad.Mascotas;
import com.luisftec.proyectoapp.util.BaseDatos;
import com.luisftec.proyectoapp.util.DaoMascotas;

import java.util.ArrayList;
import java.util.List;

public class ListarActivity extends AppCompatActivity {
    FloatingActionButton btnNuevo;

    List<Mascotas> listaMascota = new ArrayList<>();
    DaoMascotas daoMascotas = new DaoMascotas(this);
    AdaptadorPersonalizado adaptador;
    RecyclerView rvMascotas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        asignarReferencias();
        mostarMascotas();
    }
    private void asignarReferencias(){
        btnNuevo = findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        rvMascotas = findViewById(R.id.rvMascotas);
    }
    private void mostarMascotas(){
        daoMascotas.abrirBD();
        listaMascota = daoMascotas.cargarMascota();
        adaptador = new AdaptadorPersonalizado(this,listaMascota);
        rvMascotas.setAdapter(adaptador);
        rvMascotas.setLayoutManager(new LinearLayoutManager(this));
    }
}