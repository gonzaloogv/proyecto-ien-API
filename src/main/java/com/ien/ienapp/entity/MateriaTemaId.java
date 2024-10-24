package com.ien.ienapp.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode // Para asegurar que se comparen correctamente los objetos
@NoArgsConstructor
@AllArgsConstructor
public class MateriaTemaId implements Serializable {

    private Integer idMateria; // ID de la materia
    private Integer idTema; // ID del tema
}
