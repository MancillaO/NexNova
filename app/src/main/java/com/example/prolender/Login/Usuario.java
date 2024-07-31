package com.example.prolender.Login;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String usuario;
    private String contraseña;

    private static List<Usuario> usuariosRegistrados = new ArrayList<>();

    public Usuario() {}

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
}