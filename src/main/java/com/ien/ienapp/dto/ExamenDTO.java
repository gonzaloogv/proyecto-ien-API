package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ExamenDTO {
    private Integer id;

    @NotNull(message = "La nota es obligatoria")
    @Min(value = 1, message = "Se requiere al menos un digito")
    @Max(value = 99999L, message = "El número entero es 5 y el número decimal es 2")
    private Double nuNota;

    @NotBlank(message = "La condicion es obligatoria")
    @Size(min = 1, max = 100, message = "La condicion debe tener un maximo de 100 caracteres")
    private String deCondicion;

    @NotNull(message = "El ID alumno es obligatorio")
    private Integer idAlumno;

    @NotNull(message = "El ID materia es obligatorio")
    private Integer idMateria;

    @NotNull(message = "El ID profesor es obligatorio")
    private Integer idProfesor;

    @NotBlank(message = "El examen es obligatorio")
    @Size(min = 1, max = 6, message = "El examen debe tener un maximo de 6 caracteres")
    private String tiExamen;

    @NotNull(message = "La fecha de examen es obligatoria")
    private Date feExamen;

    @NotNull(message = "La hora de examen es obligatoria")
    private LocalTime hrExamen;

    @NotBlank(message = "Este campo es obligatorio")
    private String snAusente;

    @NotNull(message = "La fecha de registro es obligatoria")
    private Date feRegistro;

    private Date feModificacion;
}
