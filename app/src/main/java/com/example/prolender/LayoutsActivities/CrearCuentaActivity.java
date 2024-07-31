package com.example.prolender.LayoutsActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prolender.Login.Usuario;
import com.example.prolender.R;

public class CrearCuentaActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        edtEmail = findViewById(R.id.email);
        edtPassword = findViewById(R.id.password);
    }

    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnIniciarSesion) {
            Intent miIntent = new Intent(CrearCuentaActivity.this, MainActivity.class);
            startActivity(miIntent);
            finish();
        } else if (id == R.id.btnContinuar) {
            onRegisterClick(view);
        }
    }

    public void onRegisterClick(View view) {
        String email = edtEmail.getText().toString(); // Obtiene el email ingresado
        String password = edtPassword.getText().toString(); // Obtiene la contraseña ingresada

        if (!email.isEmpty() && !password.isEmpty()) {
            // Verifica si la contraseña tiene al menos 8 caracteres
            if (password.length() >= 8) {
                Usuario newUser = new Usuario(email, password); // Crea un nuevo usuario
                Usuario.registrarUsuario(this, newUser); // Registra el nuevo usuario

                // Muestra un mensaje de éxito
                Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent); // Inicia la actividad principal
                finish(); // Finaliza la actividad actual
            } else {
                // Muestra un mensaje de error si la contraseña es muy corta
                Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Muestra un mensaje de error si hay campos vacíos
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }




    /*
    public void onRegisterClick(View view) {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
            Usuario newUser = new Usuario();
            newUser.setUsuario(email);
            newUser.setContraseña(password);
            Usuario.registrarUsuario(newUser);

            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

     */
}

