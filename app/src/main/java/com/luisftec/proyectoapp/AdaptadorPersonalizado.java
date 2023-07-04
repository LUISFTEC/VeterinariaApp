package com.luisftec.proyectoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luisftec.proyectoapp.entidad.Mascotas;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.MiviewHolder>{

    private Context context;
    private List<Mascotas> listaMascotas = new ArrayList<>();

    public AdaptadorPersonalizado(Context context, List<Mascotas>listaMascotas){
        this.context = context;
        this.listaMascotas = listaMascotas;
    }


    @NonNull
    @Override
    public AdaptadorPersonalizado.MiviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.fila,parent,false);
        return new MiviewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizado.MiviewHolder holder, int position) {
        holder.filaNombre.setText(listaMascotas.get(position).getMasc_nombre()+"");
        holder.filaColor.setText(listaMascotas.get(position).getMasc_color()+"");
        holder.filaRaza.setText(listaMascotas.get(position).getMasc_raza()+"");
        holder.filaEdad.setText(listaMascotas.get(position).getMasc_edad()+"");
        holder.filaGenero.setText(listaMascotas.get(position).getMasc_genero()+"");
        holder.filaFecha.setText(listaMascotas.get(position).getMasc_fecha());
    }

    @Override
    public int getItemCount() {
        return listaMascotas.size();
    }

    public class MiviewHolder extends  RecyclerView.ViewHolder{
        TextView filaNombre, filaColor,filaRaza,filaEdad,filaGenero,filaFecha;
        public MiviewHolder(@NonNull View itemView) {
            super(itemView);
            filaNombre = itemView.findViewById(R.id.filaNombre);
            filaColor = itemView.findViewById(R.id.filaColor);
            filaRaza = itemView.findViewById(R.id.filaRaza);
            filaEdad = itemView.findViewById(R.id.filaEdad);
            filaGenero = itemView.findViewById(R.id.filaGenero);
            filaFecha = itemView.findViewById(R.id.filaFecha);
        }
    }
}
