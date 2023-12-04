package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Categoria {
    @Id
    @Column(name = "id_categoria")
    private Long id;

    private String titulo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public Categoria(Long id, String titulo) {
        super();
        this.id = id;
        this.titulo = titulo;
    }

    public Categoria() {
        super();
    }

    public Categoria(String titulo) {
        super();
        this.titulo = titulo;
    }

    public Categoria(Long id) {
        super();
        this.id = id;
    }
}