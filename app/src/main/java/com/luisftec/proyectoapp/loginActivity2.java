package com.luisftec.proyectoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class loginActivity2 extends AppCompatActivity {

    Button btnIngesar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        asiganarReferencias();
    }
    private void asiganarReferencias(){
        btnIngesar =findViewById(R.id.btnIngesar);
        btnIngesar.setOnClickListener(v -> {
            Intent intent= new Intent(this, MenuBotonesActivity.class);
            startActivity(intent);
        });
    }

}