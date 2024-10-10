package com.ien.ienapp.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "rrhh")
public class RRHH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nu_dni", nullable = false)
    private Integer nuDni;

    @Column(name = "de_nombre", nullable = false, length = 20)
    private String deNombre;

    @Column(name = "de_apellido", nullable = false, length = 20)
    private String deApellido;

    @Column(name = "nu_celular1", nullable = false)
    private BigInteger nuCelular1;

    @Column(name = "nu_celular2")
    private BigInteger nuCelular2;

    @Column(name = "nu_celular3")
    private BigInteger nuCelular3;

    @Column(name = "nu_telefono1")
    private BigInteger nuTelefono1;

    @Column(name = "nu_telefono2")
    private BigInteger nuTelefono2;

    @Column(name = "nu_telefono3")
    private BigInteger nuTelefono3;

    @Column(name = "de_direccion", length = 100)
    private String deDireccion;

    @Column(name = "de_genero", nullable = false, length = 10)
    private String deGenero;

    @ManyToOne
    @JoinColumn(name = "id_estado_civil", nullable = false)
    private EstadoCivil estadoCivil;

    @ManyToOne
    @JoinColumn(name = "id_categoria_rrhh", nullable = false)
    private CategoriaRrhh categoriaRrhh;

    @ManyToOne
    @JoinColumn(name = "id_localidad", nullable = false)
    private Localidades localidad;

    @Column(name = "de_mail", nullable = false, length = 50)
    private String deMail;

    @Column(name = "fe_nacimiento", nullable = false)
    private Date feNacimiento;

    @Column(name = "fe_registro")
    private Date feRegistro;

    @Column(name = "fe_modificacion")
    private Date feModificacion;

    public RRHH() {}
}
