package com.ien.ienapp.dto;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaTemaDTO {

    @NotNull(message = "El ID materia es obligatorio")
    private Integer idMateria;

    @NotNull(message = "El tema es obligatorio")
    private Integer idTema;

    @NotNull(message = "La fecha de registro obligatoria")
    private Date feRegistro;
}