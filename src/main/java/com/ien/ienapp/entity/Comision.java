
package com.ien.ienapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@Table(name = "comisiones")
public class Comision {
    
    @Id
    @Column(name = "Id")
    private Integer pkComision;

    @Column(name = "de_descripcion", nullable = false)
    private String deDescripcion;

    @Column(name = "nu_codigo_comision", nullable = false)
    private String nuCodigoComision;

    @Column(name = "de_carrera", nullable = false)
    private String deCarrera;

    @Column(name = "nu_anio_comision", nullable = false)
    private Integer nuAnioComision;

    @Column(name = "nu_anio_de_materia", nullable = false)
    private String nuAnioDeMateria;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;

    
}
