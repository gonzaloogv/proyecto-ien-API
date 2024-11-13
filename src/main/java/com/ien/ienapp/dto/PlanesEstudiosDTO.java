package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PlanesEstudiosDTO {
    private Integer id;

    @NotBlank(message = "El plan es obligatorio")
    @Size(min = 5, max = 100, message = "El plan debe tener un maximo de 100 caracteres")
    private String dePlan;

    @Size(min = 1, max = 500, message = "Debe tener un maximo de 500 caracteres")
    private String deObservacion;

    @NotNull(message = "El año del plan es obligatorio")
    private Integer nuAnioPlan;

    @NotNull(message = "")
    private Date feRegistro;
}
