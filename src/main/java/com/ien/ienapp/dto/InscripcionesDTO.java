package com.ien.ienapp.dto;

import java.math.BigInteger;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscripcionesDTO {

    @NotNull(message = "El ID alumno es obligatorio")
    private Integer idAlumno;

    @NotNull(message = "El ID carrera es obligatorio")
    private Integer idCarrera;

    @NotNull(message = "El ID plan estudio es obligatorio")
    private Integer idPlanEstudio;

    @NotNull(message = "La direccion es obligatorio")
    @Min(value = 10000, message = "El DNI debe tener al menos 5 dígitos")
    @Max(value = 9999999999L, message = "El DNI no puede tener más de 10 dígitos")
    private BigInteger nuDni;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 1, max = 100, message = "El nombre debe tener un maximo de 100 caracteres")
    private String deNombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 1, max = 100, message = "El apellido debe tener un maximo de 100 caracteres")
    private String deApellido;

    @NotNull(message = "El número de celular es obligatorio")
    private BigInteger nuCelular;

    private BigInteger nuTelefono;

    @NotBlank(message = "La direccion es obligatorio")
    @Size(min = 1, max = 100, message = "La direccion debe tener un maximo de 100 caracteres")
    private String deDireccion;

    @NotBlank(message = "El genero es obligatorio")
    private String deGenero;
    private String deMail;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private Date feNacimiento;

    @NotNull(message = "La fecha de inscripcion es obligatoria")
    private Date feInscripcion;

    @NotNull(message = "La fecha de registro es obligatoria")
    private Date feRegistro;
    private Date feModificacion;

    public InscripcionesDTO() {}
}