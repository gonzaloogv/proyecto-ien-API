package com.ien.ienapp.control;

import com.ien.ienapp.dto.NotificacionesDTO;
import com.ien.ienapp.entity.Notificaciones;
import com.ien.ienapp.service.NotificacionesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionesController {

    @Autowired
    private NotificacionesService notificacionesService;

    @GetMapping
    public List<NotificacionesDTO> getAllNotificaciones() {
        List<Notificaciones> notificacionesList = notificacionesService.getAllNotificaciones();
        return notificacionesList.stream()
                .map(notificaciones -> notificacionesService.convertirNotificacionesDTO(notificaciones))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacionesDTO> getNotificacionesById(@PathVariable Integer id) {
        Optional<Notificaciones> notificaciones = notificacionesService.getNotificacionesPorId(id);
        if (notificaciones.isPresent()) {
            return ResponseEntity.ok(notificacionesService.convertirNotificacionesDTO(notificaciones.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crearNotificacion(@Valid @RequestBody NotificacionesDTO notificacionesDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage()) // Obtiene el mensaje de cada error
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }

        Notificaciones notificaciones = notificacionesService.convertirAEntidad(notificacionesDTO);

        try {
            Notificaciones newNotificaciones = notificacionesService.crearNotificaciones(notificaciones);
            return ResponseEntity.ok(notificacionesService.convertirNotificacionesDTO(newNotificaciones));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al crear mensaje: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        notificacionesService.deleteNotificacion(id);
    }
}
