package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class AlumnoDTO {
    private Integer id;

    @NotBlank(message = "El número de legajo es obligatorio")
    private String nuLegajo;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    private Date feIngreso;

    private Date feEgreso;

    @Min(value = 0, message = "El promedio general no puede ser negativo")
    private Double nuPromedioGral;

    @NotBlank(message = "El estado de inscripción es obligatorio")
    private String tiEstadoInscripcion;

    @NotNull(message = "El plan de estudios es obligatorio")
    private Integer idPlanEstudio;

    private Date feRegistro;
}
