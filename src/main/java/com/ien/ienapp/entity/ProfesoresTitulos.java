package com.ien.ienapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "profesores_titulos")
public class ProfesoresTitulos {
    @EmbeddedId
    private ProfesoresTitulosId id; 

    @ManyToOne
    @MapsId("idTitulo") 
    @JoinColumn(name = "id_titulo", nullable = false)
    private Titulos titulos;

    @ManyToOne
    @MapsId("idProfesor") 
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor profesor;

    @Column(name = "re_imagen_titulo")
    private String reImagenTitulo;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP) 
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;

    public ProfesoresTitulos() {}
}
