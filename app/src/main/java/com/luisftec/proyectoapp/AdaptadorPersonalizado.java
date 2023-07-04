package com.luisftec.proyectoapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.luisftec.proyectoapp.entidad.Mascotas;
import com.luisftec.proyectoapp.util.DaoMascotas;

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
        holder.filaEditar.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("p_id",listaMascotas.get(position).getMasc_id()+"");
            intent.putExtra("p_nombre",listaMascotas.get(position).getMasc_nombre()+"");
            intent.putExtra("p_color",listaMascotas.get(position).getMasc_color()+"");
            intent.putExtra("p_raza",listaMascotas.get(position).getMasc_raza()+"");
            intent.putExtra("p_edad",listaMascotas.get(position).getMasc_edad()+"");
            intent.putExtra("p_genero",listaMascotas.get(position).getMasc_genero()+"");
            intent.putExtra("p_fecha",listaMascotas.get(position).getMasc_fecha()+"");
            intent.putExtra("p_especie",listaMascotas.get(position).getEsp_id()+"");
            context.startActivity(intent);
        });
        holder.filaEliminar.setOnClickListener(v -> {
            confirmar(listaMascotas.get(position).getMasc_id());
        });
    }
    private void confirmar(int id) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(context);
        ventana.setTitle("MENSAJE DE INFORMACION");
        ventana.setMessage("Eliminaras la mascota!");
        ventana.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DaoMascotas daoMascotas = new DaoMascotas(context);
                daoMascotas.abrirBD();
                String mensaje = daoMascotas.eliminarMascota(id);
                mostrarMensaje(mensaje);
            }
        });
        ventana.setNegativeButton("Cancelar",null);
        ventana.create().show();
    }
    private void mostrarMensaje(String mensaje) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(context);
        ventana.setTitle("MENSAJE DE INFORMACION");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, ListarActivity.class);
                context.startActivity(intent);
            }
        });
        ventana.create().show();
    }

    @Override
    public int getItemCount() {
        return listaMascotas.size();
    }

    public class MiviewHolder extends  RecyclerView.ViewHolder{
        TextView filaNombre, filaColor,filaRaza,filaEdad,filaGenero,filaFecha;
        ImageButton filaEliminar, filaEditar;
        public MiviewHolder(@NonNull View itemView) {
            super(itemView);
            filaNombre = itemView.findViewById(R.id.filaNombre);
            filaColor = itemView.findViewById(R.id.filaColor);
            filaRaza = itemView.findViewById(R.id.filaRaza);
            filaEdad = itemView.findViewById(R.id.filaEdad);
            filaGenero = itemView.findViewById(R.id.filaGenero);
            filaFecha = itemView.findViewById(R.id.filaFecha);
            filaEditar = itemView.findViewById(R.id.filaEditar);
            filaEliminar = itemView.findViewById(R.id.filaEliminar);
        }
    }
}
