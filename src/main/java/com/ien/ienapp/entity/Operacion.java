
package com.ien.ienapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@Table(name = "operaciones")
public class Operacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "de_operacion", nullable = false)
    private String deOperacion;

    @ManyToOne
    @JoinColumn(name = "id_modulo", nullable = false)
    private Modulo modulo;

    @Column(name = "fe_registro", nullable = false)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    private Date feModificacion;
   
    public Operacion() {}
}
