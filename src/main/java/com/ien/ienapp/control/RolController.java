package com.ien.ienapp.control;

import com.ien.ienapp.dto.RolesDTO;
import com.ien.ienapp.entity.Rol;
import com.ien.ienapp.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody RolesDTO rolesDTO) {
        Rol nuevoRol = rolService.crearRol(rolesDTO);
        return ResponseEntity.ok(nuevoRol);
    }

    @GetMapping
    public ResponseEntity<List<RolesDTO>> obtenerRoles() {
        List<RolesDTO> roles = rolService.obtenerRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Rol>> obtenerRolPorId(@PathVariable Integer id) {
        Optional<Rol> rol = rolService.obtenerRolesPorID(id);
        return rol.isPresent() ? ResponseEntity.ok(rol) : ResponseEntity.notFound().build();
    }
}
