package com.ien.ienapp.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuloDTO {

    private Integer id;
    private String deModulo;
    private Date feRegistro;
    private Date feModificacion;

    public ModuloDTO() {}
}