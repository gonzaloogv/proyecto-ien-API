package com.ien.ienapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@Table(name = "comisiones")
public class Comision {
    
    @Id
    @Column(name = "Id")
    private Integer idComision;

    @Column(name = "de_descripcion", nullable = false)
    private String deDescripcion;

    @Column(name = "nu_codigo_comision", nullable = false)
    private String nuCodigoComision;

    @Column(name = "nu_anio_comision", nullable = false)
    private Integer nuAnioComision;

    @Column(name = "de_carrera", nullable = false)
    private String deCarrera;

    @Column(name = "nu_anio_de_materia", nullable = false)
    private String nuAnioDeMateria;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;

    public Comision(){};
}
