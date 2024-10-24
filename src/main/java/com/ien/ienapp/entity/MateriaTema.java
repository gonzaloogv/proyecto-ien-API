package com.ien.ienapp.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "materias_temas")
public class MateriaTema {

    @EmbeddedId
    private MateriaTemaId id; 

    @ManyToOne
    @MapsId("idMateria") 
    @JoinColumn(name = "id_materia", nullable = false)
    private Materia materia;

    @ManyToOne
    @MapsId("idTema") 
    @JoinColumn(name = "id_tema", nullable = false)
    private Tema tema;

    @Column(name = "fe_registro", nullable = false)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    private Date feModificacion;
}
