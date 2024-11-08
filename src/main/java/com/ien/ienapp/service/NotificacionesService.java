package com.ien.ienapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ien.ienapp.dto.NotificacionesDTO;
import com.ien.ienapp.entity.Notificaciones;
import com.ien.ienapp.entity.Usuario;
import com.ien.ienapp.repository.NotificacionesRepository;
import com.ien.ienapp.repository.UsuarioRepository;

@Service
public class NotificacionesService {
    
    @Autowired
    private NotificacionesRepository notificacionesRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public NotificacionesDTO convertirNotificacionesDTO(Notificaciones notificaciones) {
        NotificacionesDTO dto = new NotificacionesDTO();
        dto.setId(notificaciones.getId());
        dto.setDeMensaje(notificaciones.getDeMensaje());
        dto.setFeRegistro(notificaciones.getFeRegistro());
        dto.setIdUsuario(notificaciones.getUsuario().getId());
        dto.setFeModificacion(notificaciones.getFeModificacion());
        return dto;
    }

    public Notificaciones convertirAEntidad(NotificacionesDTO dto) {
        Notificaciones notificaciones = new Notificaciones();
        dto.setId(notificaciones.getId());
        notificaciones.setDeMensaje(dto.getDeMensaje());
        notificaciones.setFeRegistro(dto.getFeRegistro());
        notificaciones.setFeModificacion(dto.getFeModificacion());
        
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID:" + dto.getIdUsuario()));
        notificaciones.setUsuario(usuario);

        return notificaciones;
    }

    public List<Notificaciones> getAllNotificaciones() {
        return notificacionesRepository.findAll();
    }
    public Optional<Notificaciones> getNotificacionesPorId(Integer id) {
        return notificacionesRepository.findById(id);
    }

    public Notificaciones crearNotificaciones(Notificaciones notificaciones) {
        return notificacionesRepository.save(notificaciones);
    }

    public void deleteNotificacion(Integer id) {
        notificacionesRepository.deleteById(id);
    }
} 