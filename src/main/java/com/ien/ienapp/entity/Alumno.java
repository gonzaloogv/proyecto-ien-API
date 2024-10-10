
package com.ien.ienapp.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fe_egreso")
    private LocalDateTime feEgreso;

    @Column(name = "fe_ingreso")
    private LocalDateTime feIngreso;

    @Column(name = "fe_modificacion")
    private LocalDateTime feModificacion;

    @Column(name = "fe_registro")
    private LocalDateTime feRegistro;

    @Column(name = "id_plan_estudio")
    private Long idPlanEstudio; // Relación con la tabla planes de estudio

    @Column(name = "nu_legajo")
    private String nuLegajo;

    @Column(name = "nu_promedio")
    private Double nuPromedio;

    @Column(name = "nu_promedio_gral")
    private Double nuPromedioGral;

    @Column(name = "ti_estado_inscripcion")
    private String tiEstadoInscripcion;

    @Column(name = "fk_al_rrhh")
    private Long fkAlRrhh; // Relación con otra tabla (RRHH)

    @Column(name = "id_rol")
    private Long idRol; // Relación con la tabla roles

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFeEgreso() { return feEgreso; }
    public void setFeEgreso(LocalDateTime feEgreso) { this.feEgreso = feEgreso; }

    public LocalDateTime getFeIngreso() { return feIngreso; }
    public void setFeIngreso(LocalDateTime feIngreso) { this.feIngreso = feIngreso; }

    public LocalDateTime getFeModificacion() { return feModificacion; }
    public void setFeModificacion(LocalDateTime feModificacion) { this.feModificacion = feModificacion; }

    public LocalDateTime getFeRegistro() { return feRegistro; }
    public void setFeRegistro(LocalDateTime feRegistro) { this.feRegistro = feRegistro; }

    public Long getIdPlanEstudio() { return idPlanEstudio; }
    public void setIdPlanEstudio(Long idPlanEstudio) { this.idPlanEstudio = idPlanEstudio; }

    public String getNuLegajo() { return nuLegajo; }
    public void setNuLegajo(String nuLegajo) { this.nuLegajo = nuLegajo; }

    public Double getNuPromedio() { return nuPromedio; }
    public void setNuPromedio(Double nuPromedio) { this.nuPromedio = nuPromedio; }

    public Double getNuPromedioGral() { return nuPromedioGral; }
    public void setNuPromedioGral(Double nuPromedioGral) { this.nuPromedioGral = nuPromedioGral; }

    public String getTiEstadoInscripcion() { return tiEstadoInscripcion; }
    public void setTiEstadoInscripcion(String tiEstadoInscripcion) { this.tiEstadoInscripcion = tiEstadoInscripcion; }

    public Long getFkAlRrhh() { return fkAlRrhh; }
    public void setFkAlRrhh(Long fkAlRrhh) { this.fkAlRrhh = fkAlRrhh; }

    public Long getIdRol() { return idRol; }
    public void setIdRol(Long idRol) { this.idRol = idRol; }
}