package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MateriaDTO {
    private Integer id;
    private String deNombre;
    private Double taAsistenciaObligatoria; // Asegurado como Double
    private Integer idCarrera; // ID de la carrera a la que pertenece
    private Date feRegistro; // Fecha de registro
}
