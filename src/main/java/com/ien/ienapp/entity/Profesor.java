package com.ien.ienapp.entity;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "profesores")
public class Profesor {
    @Id
    private Integer id;
    
    @Column(name = "nu_matricula")
    private String nuMatricula;

    @Column(name = "fe_baja")
    private Date feBaja;

    @Column(name = "fe_ingreso")
    private Date feIngreso;

    @Column(name = "fe_modificacion")
    private Date feModificacion;

    @Column(name = "fe_registro")
    private Date feRegistro;

    public Profesor() {};
}