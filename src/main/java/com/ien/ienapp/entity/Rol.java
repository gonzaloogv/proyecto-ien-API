
package com.ien.ienapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@Table(name = "roles")
public class Rol {
    
    @Id
    @Column(name = "Id")
    private Integer pkRol;

    @Column(name = "de_rol", nullable = false)
    private String deRol;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;
    
    
}
