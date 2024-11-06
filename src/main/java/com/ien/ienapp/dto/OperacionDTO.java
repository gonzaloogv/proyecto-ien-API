package com.ien.ienapp.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperacionDTO {

    private Integer id;
    private String deOperacion;
    private Integer idModulo; // Usamos el ID del módulo en vez del objeto completo
    private Date feRegistro;
    private Date feModificacion;

    public OperacionDTO() {}
}
