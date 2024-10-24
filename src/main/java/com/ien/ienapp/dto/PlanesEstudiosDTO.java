package com.ien.ienapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PlanesEstudiosDTO {
    private Integer id;
    private String dePlan;
    private String deObservacion;
    private Integer nuAnioPlan;
    private Date feRegistro;
}
