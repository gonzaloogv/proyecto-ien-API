package com.proyecto.api.dto;

public class LoginRequest {
    private String usuario;
    private String password;

    // Constructor vacío
    public LoginRequest() {}

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
