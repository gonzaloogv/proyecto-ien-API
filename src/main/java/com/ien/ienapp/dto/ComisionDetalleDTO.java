package com.ien.ienapp.dto;

import java.time.LocalTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComisionDetalleDTO {
    private Integer id;

    // fechas
    
    private Date feRegistro;

    private Date feModificacion;

    // comisiones
    private Integer idComision;

    private String deDescripcion;

    private String nuCodigoComision;

    private Integer nuAnioComision;

    private String deCarrera;

    private String nuAnioDeMateria;

    // alumnos

    private Integer idAlumnos;

    // materias

    private Integer idMateria;

    // aula

    private Integer idAula;

    private Integer nuCapacidadMax;

    // horario

    private Integer idHorario;

    private LocalTime hrInicio;

    private LocalTime hrFin;

    private Integer nuDia;
}
