package com.ien.ienapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
@Entity
@Table(name = "actualizaciones_recientes")
public class ActualizacionReciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;
    @Column(name = "title")
    private String title;
    @Column(name = "subtitle")
    private String subtitle;
    @Column(name = "image")
    private String image;
    @Column(name = "date")
    private Date date;

    public ActualizacionReciente() {}

    // Getters and setters
}
