package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class TemaDTO {
    private Integer id;

    @NotNull(message = "Title cannot be null")
    private String deTitulo;

    @NotNull(message = "Description cannot be null")
    private String deDescripcion;

    @NotNull(message = "Registration date cannot be null")
    private LocalDate feRegistro;

    private Integer idMateria;
}
