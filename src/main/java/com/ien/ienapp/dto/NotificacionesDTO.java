package com.ien.ienapp.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacionesDTO {
    private Integer id;

    @NotBlank(message = "Este campo es obligatorio")
    private String deMensaje;

    @NotNull(message = "El ID usuario es obligatorio")
    private Integer idUsuario;

    @NotNull(message = "la fecha de registro es obligatoria")
    private Date feRegistro;
    
    private Date feModificacion;
}
