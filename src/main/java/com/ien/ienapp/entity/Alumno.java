
package com.ien.ienapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@Table(name = "alumnos")
public class Alumno {
    
    @Id
    @Column(name = "Id")
    private Long pkAlumno;

    @Column(name = "nu_legajo", nullable = false)
    private String nuLegajo;

    @Column(name = "nu_promedio")
    private Double nuPromedio;

    @Column(name = "fe_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feIngreso;

    @Column(name = "fe_egreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feEgreso;

    @Column(name = "nu_promedio_gral")
    private Double nuPromedioGral;

    @Column(name = "id_plan_estudio", nullable = false)
    private Integer idPlanEstudio;

    @Column(name = "ti_estado_inscripcion", nullable = false)
    private String tiEstadoInscripcion;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;

    @ManyToOne
    @JoinColumn(name = "fk_al_rrhh", nullable = false)
    private RRHH rrhh;
    
    
    public Alumno() {
    }

    public Alumno(Long id,String nuLegajo, Double nuPromedio, Date feIngreso, Date feEgreso, Double nuPromedioGral, Integer idPlanEstudio, String tiEstadoInscripcion, Date feRegistro, Date feModificacion) {
        this.pkAlumno = id;
        this.nuLegajo = nuLegajo;
        this.nuPromedio = nuPromedio;
        this.feIngreso = feIngreso;
        this.feEgreso = feEgreso;
        this.nuPromedioGral = nuPromedioGral;
        this.idPlanEstudio = idPlanEstudio;
        this.tiEstadoInscripcion = tiEstadoInscripcion;
        this.feRegistro =  feRegistro;
        this.feModificacion = feModificacion;
    }


    
    
}
