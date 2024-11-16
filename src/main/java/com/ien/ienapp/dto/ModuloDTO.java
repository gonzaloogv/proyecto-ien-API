package com.ien.ienapp.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuloDTO {

    private Integer id;

    @NotBlank(message = "El modulo es obligatorio")
    @Size(min = 1, max = 100, message = "El modulo debe tener un maximo de 100 caracteres")
    private String deModulo;

    @NotNull(message = "La fecha de registro es obligatoria")
    private Date feRegistro;
    
    private Date feModificacion;

    public ModuloDTO() {}
}