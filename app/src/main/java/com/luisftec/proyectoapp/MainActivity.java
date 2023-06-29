package com.luisftec.proyectoapp;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.luisftec.proyectoapp.entidad.Especies;
import com.luisftec.proyectoapp.entidad.Mascotas;
import com.luisftec.proyectoapp.util.DAOEspecies;
import com.luisftec.proyectoapp.util.DaoMascotas;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spEspecies;
    EditText txtNombre, txtColor, txtRaza, txtEdad, txtFecha;
    RadioGroup rbGrupoGenero;
    RadioButton rbMacho, rbHembra;
    Button btnRegistar;
    ImageView ivFlecha;
    List<Especies> listaEspecies = new ArrayList<>();
    Mascotas mascotas;

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

        btnRegistar.setOnClickListener(v -> {
            if(capturarDatos()){
                DaoMascotas daoMascotas = new DaoMascotas(this);
                daoMascotas.abrirBD();
                String mensaje = daoMascotas.registrarMascota(mascotas);
                mostrarMensaje(mensaje);
            }
        });
    }

    private boolean capturarDatos() {

        int esp = listaEspecies.get(spEspecies.getSelectedItemPosition()).getEsp_id();
        String nombre = txtNombre.getText().toString();
        String color = txtColor.getText().toString();
        String raza = txtRaza.getText().toString();
        String edad = txtEdad.getText().toString();
        String fecha = txtFecha.getText().toString();

        // Verificar qué botón de radio está seleccionado
        int radioButtonID = rbGrupoGenero.getCheckedRadioButtonId();
        String generoSeleccionado = "";

        if (radioButtonID != -1) {
            if (radioButtonID == R.id.rbMacho) {
                generoSeleccionado = "Macho";
            } else if (radioButtonID == R.id.rbHembra) {
                generoSeleccionado = "Hembra";
            }
        }

        boolean valida = true;
        if (nombre.equals("")) {
            txtNombre.setError("Nombre es obligatorio");
            valida = false;
        }
        if (color.equals("")) {
            txtColor.setError("Color es obligatorio");
            valida = false;
        }
        if (raza.equals("")) {
            txtRaza.setError("Raza es obligatorio");
            valida = false;
        }
        if (edad.equals("")) {
            txtEdad.setError("Edad es obligatorio");
            valida = false;
        }
        if (fecha.equals("")) {
            txtFecha.setError("Fecha es obligatoria");
            valida = false;
        }
        if (valida) {
            Toast.makeText(MainActivity.this, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
        }
        if (valida) {
            generoSeleccionado = obtenerGeneroSeleccionado();
            mascotas = new Mascotas(nombre, color, Integer.parseInt(edad), generoSeleccionado, Integer.parseInt(fecha), esp);
        }

        return valida;
    }

    private String obtenerGeneroSeleccionado() {
        int radioButtonID = rbGrupoGenero.getCheckedRadioButtonId();

        if (radioButtonID == R.id.rbMacho) {
            return "Macho";
        } else if (radioButtonID == R.id.rbHembra) {
            return "Hembra";
        }

        return ""; // En caso de que no se haya seleccionado ningún botón de opción
    }

    private void asignarReferencias() {
        spEspecies = findViewById(R.id.spEspecies);
        ivFlecha = findViewById(R.id.ivFlecha);
        txtNombre = findViewById(R.id.txtNombre);
        txtColor = findViewById(R.id.txtColor);
        txtRaza = findViewById(R.id.txtRaza);
        txtEdad = findViewById(R.id.txtEdad);
        txtFecha = findViewById(R.id.txtFecha);
        rbGrupoGenero = findViewById(R.id.rbGrupoGenero);
        rbMacho = findViewById(R.id.rbMacho);
        rbHembra = findViewById(R.id.rbHembra);
        btnRegistar = findViewById(R.id.btnRegistar);
    }

    private void mostrarMensaje(String mensaje) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("MENSAJE DE INFORMACION");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("ACEPTAR", null);
        ventana.create().show();
    }

    private void cargarEspecies() {
        DAOEspecies daoEspecies = new DAOEspecies(this);
        daoEspecies.abrirBD();
        listaEspecies = daoEspecies.cargarEspecies();

        ArrayAdapter<Especies> adaptador = new ArrayAdapter<>(this, R.layout.spinner_item_luisftec, listaEspecies);
        spEspecies.setAdapter(adaptador);
    }

    private void agregarEventoFlecha() {
        ivFlecha.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                spEspecies.performClick(); // Desplegar el Spinner
                return true;
            }
            return false;
        });
    }
}
