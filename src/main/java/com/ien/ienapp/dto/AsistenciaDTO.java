package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.util.Date;

@Getter
@Setter
public class AsistenciaDTO {
    private Integer id;

    @NotNull(message = "El ID de ComisionDetalle es obligatorio")
    private Integer idComisionDetalle;

    @NotNull(message = "La fecha de asistencia es obligatoria")
    private Date feAsistencia;

    @NotNull(message = "El número de asistencia es obligatorio")
    @Min(value = 0, message = "La asistencia no puede ser menor a 0")
    @Max(value = 100, message = "La asistencia no puede ser mayor a 100")
    private Double nuAsistencia;
}
