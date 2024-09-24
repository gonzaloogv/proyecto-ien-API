
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
@Table(name = "rrhh")
public class RRHH {
    
    @Id
    @Column(name = "Id")
    private Integer pk_rrhh;
    
    @Column(name = "nu_dni", nullable = false)
    private Integer nuDni;
    
   @Column(name = "de_nombre", nullable = false)
    private String deNombre;

    @Column(name = "de_apellido", nullable = false)
    private String deApellido;

    @Column(name = "nu_telefono1")
    private Integer nuTelefono1;

    @Column(name = "nu_telefono2")
    private Integer nuTelefono2;

    @Column(name = "nu_telefono3")
    private Integer nuTelefono3;

    @Column(name = "nu_celular1", nullable = false)
    private Integer nuCelular1;

    @Column(name = "nu_celular2")
    private Integer nuCelular2;

    @Column(name = "nu_celular3")
    private Integer nuCelular3;

    @Column(name = "de_direccion", nullable = false)
    private String deDireccion;

    @Column(name = "de_genero", nullable = false)
    private String deGenero;

    @Column(name = "id_estadocivil", nullable = false)
    private Integer idEstadoCivil;

    @Column(name = "id_categoria_rrhh", nullable = false)
    private Integer idCategoriaRRHH;

    @Column(name = "de_mail", length = 100)
    private String deMail;

    @Column(name = "fe_nacimiento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feNacimiento;

    @Column(name = "fe_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feModificacion;

    
}
