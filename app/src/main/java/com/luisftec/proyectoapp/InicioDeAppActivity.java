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

        Button botonRedireccion = findViewById(R.id.btnRegistrarse);
        botonRedireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioDeAppActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}
