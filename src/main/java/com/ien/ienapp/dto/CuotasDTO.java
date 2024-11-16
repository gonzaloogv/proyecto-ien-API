package com.ien.ienapp.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CuotasDTO {
    private Integer id;

    @NotNull(message = "Este campo es obligatorio")
    @Min(value = 1, message = "Se requiere al menos un digito")
    @Max(value = 99999999L, message = "El número entero es 8 y el número decimal es 2")
    private Double prCuota;
    
    @NotNull(message = "El ID alumno es obligatorio")
    private Integer idAlumno;

    @NotNull(message = "La fecha de registro obligatoria")
    private Date feRegistro;

    private Date feModificacion;
}
