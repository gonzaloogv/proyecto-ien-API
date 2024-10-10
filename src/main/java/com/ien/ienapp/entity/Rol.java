
package com.ien.ienapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GenerationType;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "de_rol", nullable = false)
    private String deRol;

    @ManyToMany(mappedBy = "roles")
    private List<Operacion> operaciones;

    @Column(name = "fe_registro", nullable = false)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    private Date feModificacion;

    public Rol(){}
}