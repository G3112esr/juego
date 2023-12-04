package com.example.demo.entity;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_juego")
    private Long id;
    @NotBlank(message = "este campo es obligatorio")
    private String titulo;

    @NotBlank(message = "este campo es obligatorio")
    private String requisitos;
    @NotBlank(message = "este campo es obligatorio")
    private String descripcion;

    private String imagen; // Cambiar a String para almacenar la ubicación del archivo
    private String instalador; // Cambiar a String para almacenar la ubicación del archivo
    @NotEmpty(message = "Debe seleccionar al menos una categoría")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categoria_juego" ,
            joinColumns = @JoinColumn(name = "id_juego"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> categorias;

    @Transient
    private MultipartFile image;
    @Transient
    private MultipartFile installer;

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

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getInstalador() {
        return instalador;
    }

    public void setInstalador(String instalador) {
        this.instalador = instalador;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public MultipartFile getInstaller() {
        return installer;
    }

    public void setInstaller(MultipartFile installer) {
        this.installer = installer;
    }
    public Juego(){
        super();
    }

    public Juego(Long id, String titulo, String requisitos, String descripcion, String imagen, String instalador, List<Categoria> categorias, MultipartFile image, MultipartFile installer) {
        super();
        this.id = id;
        this.titulo = titulo;
        this.requisitos = requisitos;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.instalador = instalador;
        this.categorias = categorias;
        this.image = image;
        this.installer = installer;
    }

    public Juego( String titulo, String requisitos, String descripcion, String imagen, String instalador, List<Categoria> categorias, MultipartFile image, MultipartFile installer) {
        super();
        this.titulo = titulo;
        this.requisitos = requisitos;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.instalador = instalador;
        this.categorias = categorias;
        this.image = image;
        this.installer = installer;
    }
}