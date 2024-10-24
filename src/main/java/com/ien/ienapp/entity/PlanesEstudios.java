package com.ien.ienapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "planes_estudios")
public class PlanesEstudios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "de_plan", nullable = false)
    private String dePlan;

    @Column(name = "de_observacion")
    private String deObservacion;

    @Column(name = "nu_anio_plan", nullable = false)
    private Integer nuAnioPlan;

    @Column(name = "fe_registro", nullable = false)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    private Date feModificacion;

    public PlanesEstudios() {}
}