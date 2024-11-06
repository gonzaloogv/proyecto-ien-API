package com.ien.ienapp.entity;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter
public class InscripcionesId implements Serializable {

    private Integer idAlumno;
    private Integer idCarrera;
}
