package com.ien.ienapp.dto;

import java.math.BigInteger;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscripcionesDTO {
    private Integer idAlumno;
    private Integer idCarrera;
    private Integer idPlanEstudio;
    private Integer nuDni;
    private String deNombre;
    private String deApellido;
    private BigInteger nuCelular;
    private BigInteger nuTelefono;
    private String deDireccion;
    private String deGenero;
    private String deMail;
    private Date feNacimiento;
    private Date feInscripcion;
    private Date feRegistro;
    private Date feModificacion;

    public InscripcionesDTO() {}
}