package com.example.prolender.Login;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String usuario;
    private String contraseña;

    private static List<Usuario> usuariosRegistrados = new ArrayList<>();

    public Usuario() {}

    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public static void registrarUsuario(Context context, Usuario usuario) {
        // Obtiene las preferencias compartidas (SharedPreferences) para el almacenamiento de datos
        SharedPreferences sharedPreferences = context.getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        // Editor para realizar cambios en las preferencias
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Guarda el usuario y la contraseña
        editor.putString(usuario.getUsuario(), usuario.getContraseña());
        // Aplica los cambios
        editor.apply();
    }

    public static boolean esUsuarioValido(Context context, String usuario, String contraseña) {
        // Obtiene las preferencias compartidas para el almacenamiento de datos
        SharedPreferences sharedPreferences = context.getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        // Recupera la contraseña almacenada para el usuario dado
        String storedPassword = sharedPreferences.getString(usuario, null);
        // Compara la contraseña almacenada con la proporcionada
        return contraseña.equals(storedPassword);
    }




    /*
    public static void registrarUsuario(Usuario usuario) {
        usuariosRegistrados.add(usuario);
    }

    public static boolean esUsuarioValido(String usuario, String contraseña) {
        for (Usuario u : usuariosRegistrados) {
            if (u.getUsuario().equals(usuario) && u.getContraseña().equals(contraseña)) {
                return true;
            }
        }
        return false;
    }

     */


}