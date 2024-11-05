package com.ien.ienapp.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesoresDTO {
    private Integer idProfesor; 
    private Integer idMateria;  
    private String tiCargo;    
    private Date feRegistro;   
    private Date feModificacion;
}
