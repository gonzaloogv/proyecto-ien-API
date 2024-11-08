package com.ien.ienapp.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolesDTO {
    private Integer idRol;
    private String deRol;
    private Date feRegistro;
    private Date feModificacion;
}
