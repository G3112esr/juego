package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // Anotación para hacer que el username sea único
    private String username;

    @Column(unique = true) // Anotación para hacer que el email sea único
    private String email;

    private String password;

}
