package com.ien.ienapp.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    private Integer id;

    @Column(name = "nu_legajo", nullable = false)
    private String nuLegajo;

    @Column(name = "fe_ingreso")
    @Temporal(TemporalType.DATE)
    private Date feIngreso;

    @Column(name = "fe_egreso")
    @Temporal(TemporalType.DATE)
    private Date feEgreso;

    @Column(name = "nu_promedio_gral")
    private Double nuPromedioGral;

    @Column(name = "ti_estado_inscripcion") // Corregido
    private String tiEstadoInscripcion; // Corregido

    @ManyToOne
    @JoinColumn(name = "id_plan_estudio", nullable = false) // Relación con PlanEstudio
    private PlanesEstudios planesEstudios;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP) // Ajustado para incluir tiempo
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP) // Ajustado para incluir tiempo
    private Date feModificacion;

    // Relación con la tabla rrhh
    @ManyToOne
    @JoinColumn(name = "id_rrhh", nullable = false) // nullable si quieres permitir nulos
    private RRHH rrhh;

    public Alumno() {} // Constructor vacío
}
