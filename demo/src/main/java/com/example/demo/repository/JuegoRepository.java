package com.example.demo.repository;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Juego;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface JuegoRepository extends JpaRepository<Juego, Long> {

}

