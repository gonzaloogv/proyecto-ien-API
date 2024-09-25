package com.proyecto.api.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    // Método básico de autenticación (puede ser reemplazado por lógica real de autenticación)
    public boolean authenticate(String usuario, String password) {
        // Aquí debes agregar tu lógica real para validar el usuario y contraseña.
        // Esta es solo una simulación básica:
        if ("usuarioValido".equals(usuario) && "passwordValido".equals(password)) {
            return true;
        }
        return false;
    }
}
