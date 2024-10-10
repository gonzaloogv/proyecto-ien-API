
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
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @Column(name = "Id")
    private Integer pkUsuario;

    @Column(name = "de_nombre_cuenta", nullable = false)
    private String deNombreCuenta;

    @Column(name = "de_contraseña", nullable = false)
    private String deContraseña;

    @ManyToOne
    @JoinColumn(name = "id_rrhh", nullable = false)
    private RRHH rrhh;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @Column(name = "sn_activo", nullable = false)
    private String snActivo;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;
    
}
