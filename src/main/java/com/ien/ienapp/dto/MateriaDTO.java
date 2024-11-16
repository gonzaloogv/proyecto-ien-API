package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MateriaDTO {
    private Integer id;

    @NotBlank(message = "El nombres es obligatorio")
    @Size(min = 1, max = 100, message = "El nombre debe tener un maximo de 100 caracteres")
    private String deNombre;

    private Double taAsistenciaObligatoria;


    private Integer idCarrera;
    
    @NotNull(message = "La fecha de registro es obligatoria")
    private Date feRegistro;
}
