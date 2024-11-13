package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CarreraDTO {
    private Integer id;

    @NotBlank(message = "La carrera es obligatoria")
    @Size(min = 1, max = 100, message = "La carrera debe tener un maximo de 100 caracteres")
    private String deCarrera;

    @NotBlank(message = "La sede es obligatoria")
    @Size(min = 1, max = 100, message = "La sede debe tener un maximo de 100 caracteres")
    private String deSede;

    @NotNull(message = "La duracion de horas es obligatoria")
    private Integer nuDuracionHoras;

    @NotBlank(message = "Este campo es obligatorio")
    @Size(min = 1, max = 100, message = "Debe tener un maximo de 100 caracteres")
    private String deResponsable;

    @NotNull(message = "El ID planes estudio es obligatorio")
    private Integer planesEstudioId;

    @NotBlank(message = "La carrera es obligatoria")
    @Size(min = 1, max = 10, message = "La carrera debe tener un maximo de 10 caracteres")
    private String tiCarrera;

    @NotNull(message = "La fecha de registro es obligatoria")
    private Date feRegistro;
}
