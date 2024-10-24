package com.ien.ienapp.entity;

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
@Table(name = "provincias")
public class Provincias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "de_provincia", nullable = false)
    private String deProvincia;

    @ManyToOne
    @JoinColumn(name = "id_pais", nullable = false)
    private Paises pais;

    @Column(name = "fe_registro")
    private LocalDate feRegistro;

    @Column(name = "fe_modificacion")
    private LocalDate feModificacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeProvincia() {
        return deProvincia;
    }

    public void setDeProvincia(String deProvincia) {
        this.deProvincia = deProvincia;
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
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