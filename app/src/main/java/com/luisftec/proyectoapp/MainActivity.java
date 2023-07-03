package com.luisftec.proyectoapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.luisftec.proyectoapp.entidad.Especies;
import com.luisftec.proyectoapp.entidad.Mascotas;
import com.luisftec.proyectoapp.util.DAOEspecies;
import com.luisftec.proyectoapp.util.DaoMascotas;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Spinner spEspecies;
    EditText txtNombre, txtColor, txtRaza, txtEdad, txtFecha;
    RadioGroup rbGrupoGenero;
    RadioButton rbMacho, rbHembra;
    Button btnRegistar;
    ImageView ivFlecha;
    List<Especies> listaEspecies = new ArrayList<>();
    Mascotas mascotas;
    Calendar calendar;

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

        // Utilizar expresiones regulares para extraer el valor numérico de la cadena de fecha
        Pattern pattern = Pattern.compile("\\d+"); // Buscar uno o más dígitos consecutivos
        Matcher matcher = pattern.matcher(fecha);

        int fechaConvertida;

        if (matcher.find()) {
            String numeroEnFecha = matcher.group(); // Obtener el número encontrado en la cadena
            fechaConvertida = Integer.parseInt(numeroEnFecha);
        } else {
            // Manejo de caso cuando no se encuentra un número en la cadena
            txtFecha.setError("Fecha inválida");
            return false;
        }

        if (valida) {
            generoSeleccionado = obtenerGeneroSeleccionado();
            mascotas = new Mascotas(nombre, color, raza, Integer.parseInt(edad), generoSeleccionado, fecha,esp);

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



        btnRegistar.setOnClickListener(v -> {
            if (capturarDatos()){
                DaoMascotas daoMascotas = new DaoMascotas(this);
                daoMascotas.abrirBD();
                String mensaje =  daoMascotas.registrarMascota(mascotas);
                mostrarMensaje(mensaje);
            }
        });
        txtFecha.setOnClickListener(v -> mostrarSelectorFecha());
        calendar = Calendar.getInstance();
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
    private void mostrarSelectorFecha() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> obtenerFechaSeleccionada(year, month, dayOfMonth),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        // Limitar la selección de fechas mínima y máxima
        calendar.add(Calendar.YEAR, -1); // Restar un año a la fecha actual
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        calendar.add(Calendar.YEAR, 1); // Agregar un año a la fecha actual
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        calendar.add(Calendar.YEAR, -1); // Restaurar la fecha actual

        datePickerDialog.show();
    }
    private void obtenerFechaSeleccionada(int year, int month, int dayOfMonth) {
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechaSeleccionada = dateFormat.format(calendar.getTime());
        txtFecha.setText(fechaSeleccionada);
    }

}
