package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDate;

@Getter
@Setter
public class TemaDTO {
    private Integer id;

    @NotBlank(message = "El titulo es obligatorio")
    @Size(min = 5, max = 100, message = "El titulo debe tener un maximo de 100 caracteres")
    private String deTitulo;

    @NotBlank(message = "La descripcion es obligatoria")
    private String deDescripcion;

    @NotNull(message = "La fecha de registro es obligatoria")
    private LocalDate feRegistro;

    private Integer idMateria;
    public TemaDTO(Integer id, String deTitulo, String deDescripcion) {
        this.id = id;
        this.deTitulo = deTitulo;
        this.deDescripcion = deDescripcion;
    }
}
