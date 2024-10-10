
package com.ien.ienapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
