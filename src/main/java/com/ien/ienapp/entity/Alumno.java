
package com.ien.ienapp.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Entity
@Getter @Setter
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nu_legajo", nullable = false)
    private String nuLegajo;

    @Column(name = "fe_ingreso")
    private Date feIngreso;

    @Column(name = "fe_egreso")
    private Date feEgreso;

    @Column(name = "nu_promedio_gral")
    private Double nuPromedioGral;

    @ManyToOne
    @JoinColumn(name = "id_plan_estudio")
    private PlanesEstudios planesEstudios;

    @Column(name = "ti_estado_inscripcion")
    private String tiEstadoInscripcion;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;

    @OneToOne
    @JoinColumn(name = "Id", referencedColumnName = "Id") // Relación directa usando Id
    private RRHH rrhh;

    public Alumno(){}
}