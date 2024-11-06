package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class ExamenDTO {
    private Integer id;
    private Double nuNota;
    private String deCondicion;
    private Integer idAlumno;
    private Integer idMateria;
    private Integer idProfesor;
    private String tiExamen;
    private Date feExamen;
    private LocalTime hrExamen;
    private String snAusente;
    private Date feRegistro;
    private Date feModificacion;
}
