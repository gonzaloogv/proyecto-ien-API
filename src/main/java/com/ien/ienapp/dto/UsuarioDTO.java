package com.ien.ienapp.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Integer id;
    private Integer idRol;
    private Integer idRRHH;
    private String snActivo;
    private String deNombreCuenta;
    private String deContrasenia;
    private Date feRegistro;
    private Date feModificacion;
}
