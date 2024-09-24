
package com.ien.ienapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@Table(name = "administrativos")
public class Administrativo {
    
    @Id
    @Column(name = "Id")
    private Integer pkAdministrativo;

    @ManyToOne
    @JoinColumn(name = "fk_ad_rrhh", nullable = false)
    private RRHH rrhh;

    @Column(name = "fe_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feIngreso;

    @Column(name = "fe_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feBaja;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;
    
}
