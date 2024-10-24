package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MateriaDTO {
    private Integer id;
    private String deNombre;
    private Double taAsistenciaObligatoria; 
    private Integer idCarrera; 
    private Date feRegistro;
}
