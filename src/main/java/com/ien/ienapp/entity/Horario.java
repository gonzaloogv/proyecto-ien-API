
package com.ien.ienapp.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@Table(name = "horarios")
public class Horario {
    
    @Id
    @Column(name = "Id")
    private Integer pkHorario;

    @Column(name = "hr_inicio", nullable = false)
    private String hrInicio;

    @Column(name = "hr_fin", nullable = false)
    private String hrFin;

    @Column(name = "nu_dia", nullable = false)
    private Integer nuDia;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;
    
}
