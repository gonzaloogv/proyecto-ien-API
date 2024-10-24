package com.ien.ienapp.entity;

import java.math.BigInteger;
import java.util.Date;

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

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "inscripciones")
public class Inscripciones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_alumno", nullable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_carrera", nullable = false)
    private Carrera carrera;
    
    @Column(name = "nu_dni", nullable = false)
    private Integer nuDni;

    @Column(name = "de_nombre", nullable = false)
    private String deNombre;

    @Column(name = "de_apellido", nullable = false)
    private String deApellido;
    
    @Column(name = "nu_celular", nullable = false)
    private BigInteger nuCelular;

    @Column(name = "nu_telefono")
    private BigInteger nuTelefono;

    @Column(name = "de_direccion", nullable = false)
    private String deDireccion;

    @Column(name = "de_genero", nullable = false)
    private String deGenero;

    @Column(name = "de_mail")
    private String deMail;

    @Column(name = "fe_nacimiento", nullable = false)
    private Date feNacimiento;
    
    @Column(name = "fe_inscripcion", nullable = false)
    private Date feInscripcion;

    @ManyToOne
    @JoinColumn(name = "id_plan_estudio")
    private PlanesEstudios planesEstudios;

     @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;

    public Inscripciones() {}
}
