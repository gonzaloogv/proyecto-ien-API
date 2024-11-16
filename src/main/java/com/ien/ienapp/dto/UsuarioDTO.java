package com.ien.ienapp.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Integer id;

    @NotNull(message = "El id Rol es obligatorio")
    private Integer idRol;

    @NotNull(message = "El id RRHH es obligatorio")
    private Integer idRRHH;

    @NotBlank(message = "El estado es obligatorio")
    private String snActivo;

    @NotBlank(message = "El nombre de la cuenta es obligatorio")
    @Size(min = 5, max = 12, message = "La dirección debe tener entre 5 y 12 caracteres")
    private String deNombreCuenta;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 5, max = 15, message = "La dirección debe tener entre 5 y 15 caracteres")
    private String deContrasenia;

    @NotNull(message = "La fecha de registro es obligatoria")
    private Date feRegistro;

    private Date feModificacion;
}
