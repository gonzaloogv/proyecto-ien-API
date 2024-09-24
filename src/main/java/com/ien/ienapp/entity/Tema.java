package com.ien.ienapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@Table(name = "temas")
public class Tema {
    
    @Id
    @Column(name = "Id")
    private Integer pk_tema;
    
    @Column(name = "de_titulo", nullable = false)
    private String deTitulo;
    
        
    @Column(name = "de_descripcion", nullable = false)
    private String deDescripcion;
    
        
    @Column(name = "fe_registro", nullable = false)
    private String feRegistro;
    
        
    @Column(name = "fe_modificacion", nullable = false)
    private String feModificacion;
    
    
}
