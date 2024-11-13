package com.ien.ienapp.control;

import com.ien.ienapp.dto.RRHHDTO;
import com.ien.ienapp.entity.Administrativo;
import com.ien.ienapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/administrativo")
public class AdministrativoController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<?> crearAdmin(@RequestBody RRHHDTO rrhhDTO) {
        try {
            Administrativo administrativo = adminService.crearAdministrativo(rrhhDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Admin creado exitosamente");
            response.put("alumnoId", administrativo.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error de restricción: " + e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error de integridad de datos: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error al crear el admin: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RRHHDTO> obtenerAdmin(@PathVariable Integer id) {
        Administrativo administrativo = adminService.obtAdminPorID(id);
        RRHHDTO rrhhDTO = adminService.convertirAAdminDTO(administrativo);
        return ResponseEntity.ok(rrhhDTO);
    }


    @GetMapping
    public ResponseEntity<List<RRHHDTO>> obtenerTodosLosAdmins() {
        List<RRHHDTO> administrativo = adminService.obtenerAdmin();
        return ResponseEntity.ok(administrativo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarAdmin(@PathVariable Integer id, @RequestBody RRHHDTO rrhhDTO) {
        try {
            adminService.actualizarAdministrativo(id, rrhhDTO);
            return ResponseEntity.ok("Admin actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el admin: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAdmin(@PathVariable Integer id) {
        try {
            adminService.eliminarAdmin(id);
            return ResponseEntity.ok("Admin eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el admin: " + e.getMessage());
        }
    }
}
