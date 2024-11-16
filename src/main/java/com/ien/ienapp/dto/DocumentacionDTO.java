package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class DocumentacionDTO {
    private Integer idDocumentacion;
    private Integer idDocumentacionAlumno;

    @NotNull(message = "El ID carrera es obligatorio")
    private Integer idCarrera;
    
    private Integer idAlumno;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 1, max = 100, message = "La descripcion debe tener un maximo de 100 caracteres")
    private String deDescripcion;

    @NotNull(message = "La fecha de registro es obligatoria")
    private Date feRegistro;
    private Date feModificacion;
}
