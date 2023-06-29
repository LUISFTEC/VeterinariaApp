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

import androidx.appcompat.app.AppCompatActivity;

import com.luisftec.proyectoapp.entidad.Especies;
import com.luisftec.proyectoapp.entidad.Mascotas;
import com.luisftec.proyectoapp.util.DAOEspecies;

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
        //obtenerGeneroSeleccionado();


        spEspecies.setOnTouchListener((v, event) -> {
            return true; // Evitar que el Spinner maneje el evento táctil
        });
        rbMacho.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this, "Género seleccionado: Macho", Toast.LENGTH_SHORT).show();
            }
        });

        rbHembra.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this, "Género seleccionado: Hembra", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void capturarDatos() {
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
        // Hacer algo con los datos capturados (nombre, color, raza, edad y género seleccionado)
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
            // Realizar acciones adicionales si la validación pasa
            // Por ejemplo, guardar los datos en una base de datos, mostrar un mensaje de éxito, etc.

            // Ejemplo de uso de la variable generoSeleccionado
            Toast.makeText(MainActivity.this, "Género seleccionado: " + generoSeleccionado, Toast.LENGTH_SHORT).show();
        }
    }

    private  void  asignarReferencias(){

        spEspecies = findViewById(R.id.spEspecies);
        ivFlecha = findViewById(R.id.ivFlecha);
        txtNombre = findViewById(R.id.txtNombre);
        txtColor = findViewById(R.id.txtColor);
        txtRaza = findViewById(R.id.txtRaza);
        txtEdad = findViewById(R.id.txtEdad);
        txtFecha = findViewById(R.id.txtFecha);
        rbGrupoGenero =findViewById(R.id.rbGrupoGenero);
        rbMacho = findViewById(R.id.rbMacho);
        rbHembra = findViewById(R.id.rbHembra);
        btnRegistar = findViewById(R.id.btnRegistar);
        btnRegistar.setOnClickListener(v -> {
            capturarDatos();
        });


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