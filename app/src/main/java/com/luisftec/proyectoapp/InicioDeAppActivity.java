package com.luisftec.proyectoapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class InicioDeAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_de_app);

        Button botonRegistrarse = findViewById(R.id.btnRegistrarse);
        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioDeAppActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
        Button botonIniciarSesion = findViewById(R.id.btnIniciarSesion);
             botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioDeAppActivity.this, IniciarSeccionActivity.class);
                startActivity(intent);
            }
        });
    }
}
