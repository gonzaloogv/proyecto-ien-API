package com.ien.ienapp.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperacionDTO {

    private Integer id;

    @NotBlank(message = "Este campo es obligatorio")
    @Size(min = 1, max = 100, message = "Debe tener un maximo de 100 caracteres")
    private String deOperacion;

    @NotNull(message = "El ID modulo es obligatorio")
    private Integer idModulo; // Usamos el ID del módulo en vez del objeto completo

    @NotNull(message = "La fecha de registro es obligatoria")
    private Date feRegistro;

    private Date feModificacion;

    public OperacionDTO() {}
}
