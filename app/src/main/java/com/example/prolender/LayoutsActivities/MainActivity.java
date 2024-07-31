package com.example.prolender.LayoutsActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prolender.Login.Usuario;
import com.example.prolender.R;

public class MainActivity extends AppCompatActivity {

    private EditText edtU, edtC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtU = findViewById(R.id.email);
        edtC = findViewById(R.id.password);
    }

    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnCrear) {
            Intent miIntent = new Intent(MainActivity.this, CrearCuentaActivity.class);
            startActivity(miIntent);
            finish();
        } else if (id == R.id.btnContinuar) {
            onLoginClick(view);
        }
    }

    public void onLoginClick(View view) {
        String usuario = edtU.getText().toString(); // Obtiene el nombre de usuario ingresado
        String contraseña = edtC.getText().toString(); // Obtiene la contraseña ingresada

        // Verifica si el usuario es válido
        if (Usuario.esUsuarioValido(this, usuario, contraseña)) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent); // Inicia la actividad de la página de inicio
            finish(); // Finaliza la actividad actual
        } else {
            // Muestra un mensaje de error si las credenciales son incorrectas
            Toast.makeText(this, "Usuario o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }


    /*
    public void onLoginClick(View view) {
        String usuario = edtU.getText().toString();
        String contraseña = edtC.getText().toString();

        if (Usuario.esUsuarioValido(usuario, contraseña)) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Usuario o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

     */
}