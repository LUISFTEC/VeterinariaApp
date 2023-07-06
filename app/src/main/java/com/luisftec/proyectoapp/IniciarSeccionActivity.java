package com.luisftec.proyectoapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IniciarSeccionActivity extends AppCompatActivity {
    private EditText txtUsuario, txtContrasenia;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_seccion);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtContrasenia = findViewById(R.id.txtContrasenia);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onStart(){
        super.onStart();
        FirebaseUser  currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);
    }

    public void iniciarSeccion(View view){
        mAuth.signInWithEmailAndPassword(txtUsuario.getText().toString(), txtContrasenia.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //Toast.makeText(getApplicationContext(), "Usuario creado", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent i = new Intent(getApplicationContext(),MenuBotonesActivity.class );
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Autenticacion exitosa", Toast.LENGTH_SHORT).show();
                    //Intent i = new Intent(getApplicationContext(),InicioDeAppActivity.class );
                    //startActivity(i);

                    //updateUI(user);
                }else {
                    Toast.makeText(getApplicationContext(), "Autenticacion fallida", Toast.LENGTH_SHORT).show();
                    // updateUI(null);
                }
            }
        });

    }
}