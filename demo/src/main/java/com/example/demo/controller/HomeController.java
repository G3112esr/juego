package com.example.demo.controller;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Juego;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.JuegoRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private JuegoRepository juegoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository; // Asegúrate de tener el repositorio de categorías

    @GetMapping("")
    public ModelAndView  verPaginaDeInicio() {
        List<Juego> ultimosJuegos = juegoRepository.findAll(PageRequest.of(0, 4, Sort.by("id").descending())).toList();
        return new ModelAndView("index")
                .addObject("ultimosJuegos", ultimosJuegos);
    }

    @GetMapping("/juegos")
    public ModelAndView listarJuegos(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Juego> juegos = juegoRepository.findAll(pageable);
        return new ModelAndView("juegos")
                .addObject("juegos", juegos);
    }

    @GetMapping("/juegos/{id}")
    public ModelAndView mostrarDetallesDeJuego(@PathVariable Long id) {
        Juego juego = juegoRepository.getOne(id);
        return new ModelAndView("juego").addObject("juego", juego);
    }


}