package com.luisftec.proyectoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TurnoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> listaHorarios;
    private HorariosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turno);

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.rvNoAtencion);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear la lista de horarios
        listaHorarios = new ArrayList<>();
        listaHorarios.add("Lunes: 07:30 AM - 05:30 PM");
        listaHorarios.add("Martes: 07:30 AM - 05:30 PM");
        listaHorarios.add("Miércoles: 07:30 AM - 05:30 PM");
        listaHorarios.add("Jueves: 07:30 AM - 05:30 PM");
        listaHorarios.add("Viernes: 07:30 AM - 05:30 PM");
        listaHorarios.add("Sábado: 08:00 AM - 12:00 PM");
        listaHorarios.add("Domingo: Cerrado");

        // Agregar el texto adicional al último elemento
        String ultimoHorario = listaHorarios.get(listaHorarios.size() - 1);
        String numeroContacto = "+51 999999999"; // capturamos el nuemro o ingrese el dado
        ultimoHorario += "\n\nAtención virtual o asesoría, llamar al número: " + numeroContacto;
        listaHorarios.set(listaHorarios.size() - 1, ultimoHorario);

        // Crear el adaptador y establecerlo en el RecyclerView
        adapter = new HorariosAdapter(listaHorarios);
        recyclerView.setAdapter(adapter);
        Button btnWhatsApp = findViewById(R.id.btnWhatsApp);
        Button btnInstagram = findViewById(R.id.btnInstagram);

        btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.whatsapp.com"); // Reemplaza con el enlace de WhatsApp
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/"); // Reemplaza con el enlace de Instagram
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }
    public class HorariosAdapter extends RecyclerView.Adapter<HorariosAdapter.ViewHolder> {
        private List<String> horarios;

        public HorariosAdapter(List<String> horarios) {
            this.horarios = horarios;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String horario = horarios.get(position);
            holder.bind(horario);
        }
        @Override
        public int getItemCount() {
            return horarios.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View itemView) {
                super(itemView);
            }
            public void bind(String horario) {
                TextView textView = itemView.findViewById(android.R.id.text1);
                textView.setText(horario);
            }
        }
    }
}
