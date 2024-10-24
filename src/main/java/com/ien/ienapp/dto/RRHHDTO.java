package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
public class RRHHDTO {
    private Integer id;

    @NotNull(message = "El DNI es obligatorio")
    @Min(value = 10000, message = "El DNI debe tener al menos 5 dígitos")
    @Max(value = 9999999999L, message = "El DNI no puede tener más de 10 dígitos")
    private Integer nuDni;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 20, message = "El nombre debe tener entre 2 y 20 caracteres")
    private String deNombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 20, message = "El apellido debe tener entre 2 y 20 caracteres")
    private String deApellido;

    @NotNull(message = "El número de celular 1 es obligatorio")
    @Min(value = 100000, message = "El celular debe tener al menos 6 dígitos")
    private BigInteger nuCelular1;

    @Min(value = 100000, message = "El celular debe tener al menos 6 dígitos")
    private BigInteger nuCelular2;

    @Min(value = 100000, message = "El celular debe tener al menos 6 dígitos")
    private BigInteger nuCelular3;

    private BigInteger nuTelefono1;
    private BigInteger nuTelefono2;
    private BigInteger nuTelefono3;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres")
    private String deDireccion;

    @NotBlank(message = "El género es obligatorio")
    @Size(min = 1, max = 10, message = "El género debe tener entre 1 y 10 caracteres")
    private String deGenero;

    @NotNull(message = "El estado civil es obligatorio")
    private Integer idEstadoCivil;

    @NotNull(message = "La categoría RRHH es obligatoria")
    private Integer idCategoriaRrhh; 

    @NotNull(message = "La localidad es obligatoria")
    private Integer idLocalidad;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un correo válido")
    private String deMail;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private Date feNacimiento;

    private Date feRegistro;
    private Date feModificacion;

    //alumnos
    
}
