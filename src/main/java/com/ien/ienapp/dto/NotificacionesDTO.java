package com.ien.ienapp.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacionesDTO {
    private Integer id;
    private String deMensaje;
    private Integer idUsuario;
    private Date feRegistro;
    private Date feModificacion;
}
