package com.ien.ienapp.entity;

import java.io.Serializable;
import java.util.Objects;

public class MateriaTemaId implements Serializable {
    private Long idMateria;
    private Long idTema;

    public MateriaTemaId() {}

    public MateriaTemaId(Long idMateria, Long idTema) {
        this.idMateria = idMateria;
        this.idTema = idTema;
    }

    // Getters and Setters
    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }

    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MateriaTemaId)) return false;
        MateriaTemaId that = (MateriaTemaId) o;
        return Objects.equals(idMateria, that.idMateria) && Objects.equals(idTema, that.idTema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMateria, idTema);
    }
}
