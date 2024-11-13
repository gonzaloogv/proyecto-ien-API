package com.ien.ienapp.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolesDTO {
    private Integer idRol;

    @NotBlank(message = "El rol es obligatorio")
    private String deRol;

    @NotNull(message = "La fecha de registro es obligatoria")
    private Date feRegistro;
    
    private Date feModificacion;
}
