package com.ien.ienapp.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesoresDTO {
    private Integer idProfesor; 
    private Integer idMateria; 
    private Integer idTitulo; 
    private String reImagenTitulo;
    private String tiCargo;
    
    @NotNull(message = "La fecha de registro es obligatoria")
    private Date feRegistro;
       
    private Date feModificacion;

    //titulos
    private String deTitulo;
}
