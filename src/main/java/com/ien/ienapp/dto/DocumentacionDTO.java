package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DocumentacionDTO {
    private Integer idDocumentacion;
    private Integer idDocumentacionAlumno;
    private Integer idCarrera;
    private Integer idAlumno;
    private String deDescripcion;
    private Date feRegistro;
    private Date feModificacion;
}
