package com.ien.ienapp.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CuotasDTO {
    private Integer id;

    private Double prCuota;
    
    private Integer idAlumno;

    private Date feRegistro;

    private Date feModificacion;
}
