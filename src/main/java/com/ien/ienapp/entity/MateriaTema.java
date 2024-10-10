package com.ien.ienapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "materias_temas")
public class MateriaTema {

    @EmbeddedId
    private MateriaTemaId id;

    // Getters y Setters
    public MateriaTemaId getId() {
        return id;
    }

    public void setId(MateriaTemaId id) {
        this.id = id;
    }

    public Long getIdMateria() {
        return id.getIdMateria();
    }

    public Long getIdTema() {
        return id.getIdTema();
    }

    public void setIdMateria(Long idMateria) {
        this.id.setIdMateria(idMateria);
    }

    public void setIdTema(Long idTema) {
        this.id.setIdTema(idTema);
    }
}
