package com.luisftec.proyectoapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegistroActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasenia;
    private EditText contrasenia1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.correo);
        contrasenia = findViewById(R.id.contrasenia);
        contrasenia1 = findViewById(R.id.txtContrasenia);
    }
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void registrarUsuario(View view){

        if (contrasenia.getText().toString().equals(contrasenia1.getText().toString())){

            mAuth.createUserWithEmailAndPassword(correo.getText().toString(), contrasenia.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Usuario creado", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent i = new Intent(getApplicationContext(),InicioDeAppActivity.class );
                        startActivity(i);

                        //updateUI(user);
                    }else {
                        Toast.makeText(getApplicationContext(), "Autentication failed", Toast.LENGTH_SHORT).show();
                       // updateUI(null);
                    }
                }
            });

        }else {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }

    }
}