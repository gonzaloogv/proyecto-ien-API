
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
@Table(name = "aulas")
public class Aula {
    
    
    @Id
    @Column(name = "Id")
    private Long pkAula;

    @Column(name = "nu_capacidad_max", nullable = false)
    private Integer nuCapacidadMax;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;
    
    
    public Aula(){
    }
    
    public Aula(Long id, Integer nuCapacidadMax, Date feRegistro, Date feModificacion){
    
        this.pkAula = id;
        this.nuCapacidadMax = nuCapacidadMax;
        this.feRegistro = feRegistro;
        this.feModificacion = feModificacion;
        
    
    }
    
}
