
package com.ien.ienapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "de_rol", nullable = false)
    private String deRol;

    @Column(name = "fe_modificacion")
    private LocalDateTime feModificacion;

    @Column(name = "fe_registro", nullable = false)
    private LocalDateTime feRegistro;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDeRol() { return deRol; }
    public void setDeRol(String deRol) { this.deRol = deRol; }

    public LocalDateTime getFeModificacion() { return feModificacion; }
    public void setFeModificacion(LocalDateTime feModificacion) { this.feModificacion = feModificacion; }

    public LocalDateTime getFeRegistro() { return feRegistro; }
    public void setFeRegistro(LocalDateTime feRegistro) { this.feRegistro = feRegistro; }
}