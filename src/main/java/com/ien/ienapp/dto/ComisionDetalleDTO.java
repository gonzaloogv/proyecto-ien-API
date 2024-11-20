package com.ien.ienapp.dto;

import java.time.LocalTime;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComisionDetalleDTO {
    private Integer id;

    // fechas
    
    @NotNull(message = "La fecha de registro es obligatoria")
    private Date feRegistro;

    private Date feModificacion;

    // comisiones

    @NotNull(message = "El ID comision es obligatorio")
    private Integer idComision;

    @NotBlank(message = "La descripcion es necesaria")

    private String deDescripcion;

    private String nuCodigoComision;

    private Integer nuAnioComision;

    private String deCarrera;

    private String nuAnioDeMateria;

    // alumnos

    @NotNull(message = "El ID alumnos es obligatorio")
    private Integer idAlumnos;

    // materias

    @NotNull(message = "El ID materia es obligatorio")
    private Integer idMateria;

    // aula

    @NotNull(message = "El ID aula es obligatorio")
    private Integer idAula;

    private String deAula;

    private Integer nuCapacidadMax;

    // horario

    @NotNull(message = "El ID horario es obligatorio")
    private Integer idHorario;

    private LocalTime hrInicio;

    private LocalTime hrFin;

    private Integer nuDia;
}
