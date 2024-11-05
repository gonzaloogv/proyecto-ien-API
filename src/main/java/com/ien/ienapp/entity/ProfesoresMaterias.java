package com.ien.ienapp.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "profesores_materias")
public class ProfesoresMaterias {
    @EmbeddedId
    private ProfesoresMateriasId id; 

    @ManyToOne
    @MapsId("idMateria") 
    @JoinColumn(name = "id_materia", nullable = false)
    private Materia materia;

    @ManyToOne
    @MapsId("idProfesor") 
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor profesor;

    @Column(name = "ti_cargo")
    private String tiCargo;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP) 
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;

    public ProfesoresMaterias() {}
}
