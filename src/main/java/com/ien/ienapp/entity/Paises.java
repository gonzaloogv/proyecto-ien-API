package com.ien.ienapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.time.LocalDate;

@Entity
@Table(name = "paises")
public class Paises {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "de_pais", nullable = false)
    private String dePais;

    @Column(name = "fe_registro")
    private LocalDate feRegistro;

    @Column(name = "fe_modificacion")
    private LocalDate feModificacion;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDePais() {
        return dePais;
    }

    public void setDePais(String dePais) {
        this.dePais = dePais;
    }

    public LocalDate getFeRegistro() {
        return feRegistro;
    }

    public void setFeRegistro(LocalDate feRegistro) {
        this.feRegistro = feRegistro;
    }

    public LocalDate getFeModificacion() {
        return feModificacion;
    }

    public void setFeModificacion(LocalDate feModificacion) {
        this.feModificacion = feModificacion;
    }
}
