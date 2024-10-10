package com.ien.ienapp.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.time.LocalTime;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@Table(name = "examenes")
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pkExamen;

    @Column(name = "nu_nota", nullable = false)
    private Double nuNota;

    @Column(name = "de_condicion", nullable = false)
    private String deCondicion;

    @ManyToOne
    @JoinColumn(name = "id_alumno", nullable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_materia", nullable = false)
    private Materia materia;

    @ManyToOne
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor profesor;

    @Column(name = "ti_examen", nullable = false)
    private String tiExamen;

    @Column(name = "fe_examen", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date feExamen;

    @Column(name = "hr_examen", nullable = false)
    private LocalTime hrExamen;

    @Column(name = "sn_ausente", nullable = false)
    private String snAusente;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;

    public Examen(){}
}
