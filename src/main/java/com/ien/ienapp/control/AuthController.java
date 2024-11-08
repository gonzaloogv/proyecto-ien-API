package com.ien.ienapp.control;

import com.ien.ienapp.dto.UsuarioDTO;
import com.ien.ienapp.entity.Usuario;
import com.ien.ienapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody LoginRequest loginRequest) {
        // validar el nombre de usuario y la contraseña
        Usuario usuario = usuarioService.getAllUsuarios().stream()
            .filter(u -> u.getDeNombreCuenta().equals(loginRequest.getUsername()))
            .findFirst()
            .orElse(null);

        if (usuario == null || !usuario.getDeContrasenia().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        UsuarioDTO usuarioDTO = usuarioService.convertirUsuarioDTO(usuario);
        return ResponseEntity.ok(usuarioDTO);
    }

    // clase interna para recibir las credenciales del login
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
