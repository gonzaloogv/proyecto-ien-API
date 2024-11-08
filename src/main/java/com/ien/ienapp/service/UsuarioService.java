package com.ien.ienapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ien.ienapp.dto.UsuarioDTO;
import com.ien.ienapp.entity.RRHH;
import com.ien.ienapp.entity.Rol;
import com.ien.ienapp.entity.Usuario;
import com.ien.ienapp.repository.RRHHRepository;
import com.ien.ienapp.repository.RolRepository;
import com.ien.ienapp.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RRHHRepository rrhhRepository;

    @Autowired
    private RolRepository rolRepository;

    public UsuarioDTO convertirUsuarioDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setIdRRHH(usuario.getRrhh().getId());
        dto.setIdRol(usuario.getRol().getId());
        dto.setSnActivo(usuario.getSnActivo());
        dto.setDeContrasenia(usuario.getDeContrasenia());
        dto.setDeNombreCuenta(usuario.getDeNombreCuenta());
        dto.setFeRegistro(usuario.getFeRegistro());
        dto.setFeModificacion(usuario.getFeModificacion());
        return dto;
    }

    public Usuario convertirAEntidad(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        dto.setId(usuario.getId());
        usuario.setDeContrasenia(dto.getDeContrasenia());
        usuario.setDeNombreCuenta(dto.getDeNombreCuenta());
        usuario.setFeModificacion(dto.getFeModificacion());
        usuario.setFeRegistro(dto.getFeRegistro());
        usuario.setSnActivo(dto.getSnActivo());

        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID:" + dto.getIdRol()));
        usuario.setRol(rol);

        RRHH rrhh = rrhhRepository.findById(dto.getIdRRHH())
                .orElseThrow(() -> new RuntimeException("RRHH no encontrado con ID:" + dto.getIdRRHH()));
        usuario.setRrhh(rrhh);

        return usuario;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioRepository.existsByDeNombreCuenta(usuario.getDeNombreCuenta())) {
            throw new RuntimeException("El nombre de usuario ya existe.");
        }
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Integer id) {
        rrhhRepository.deleteById(id);
    }
}