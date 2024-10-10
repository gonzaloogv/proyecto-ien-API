package com.ien.ienapp.entity;

import javax.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@Table(name = "carreras")
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "de_carrera", nullable = false)
    private String deCarrera;

    @Column(name = "de_sede", nullable = false)
    private String deSede;

    @Column(name = "nu_duracion_horas", nullable = false)
    private Integer nuDuracionHoras;

    @Column(name = "de_responsable", nullable = false)
    private String deResponsable;

    @ManyToOne
    @JoinColumn(name = "id_plan_estudio", nullable = false)
    private PlanesEstudios planesEstudio;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;

    @Column(name = "ti_carrera")
    private String tiCarrera;

    public Carrera() {}
}
