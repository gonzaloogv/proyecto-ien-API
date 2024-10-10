package com.ien.ienapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.time.LocalDate;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Setter @Getter
@Table(name = "localidades")
public class Localidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "de_localidad", nullable = false)
    private String localidad;

    @ManyToOne
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincias provincias;

    @Column(name = "fe_registro")
    private LocalDate feRegistro;

    @Column(name = "fe_modificacion")
    private LocalDate feModificacion;

    // Getters y Setters
    public Localidades() {}
}