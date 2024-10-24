package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CarreraDTO {
    private Integer id;
    private String deCarrera;
    private String deSede;
    private Integer nuDuracionHoras;
    private String deResponsable;
    private Integer planesEstudioId; 
    private String tiCarrera;
    private Date feRegistro;
}
