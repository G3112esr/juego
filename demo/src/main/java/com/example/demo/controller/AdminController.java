package com.example.demo.controller;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Juego;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.JuegoRepository;
import com.example.demo.service.AlmacenServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private JuegoRepository juegoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private AlmacenServicioImpl servicio;



    @GetMapping("")
    public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "titulo", size = 5) Pageable pageable) {
       Page<Juego> juegos = juegoRepository.findAll(pageable);
        return new ModelAndView("admin/index").addObject("juegos", juegos);
    }

    @GetMapping("/juegos/nuevo")
    public ModelAndView mostrarFormularioDeNuevaJuego() {
        List<Categoria> categorias = categoriaRepository.findAll(Sort.by("titulo"));
        return new ModelAndView("/admin/nueva-juego")
                .addObject("juego", new Juego())
                .addObject("categorias",categorias);
    }
    @PostMapping("/juegos/nuevo")
        public ModelAndView registrarJuego(@Validated Juego juego,BindingResult bindingResult ) {
        if (bindingResult.hasErrors() || juego.getImage().isEmpty() || juego.getInstaller().isEmpty())
        {
            if(juego.getImage().isEmpty()) {
                bindingResult.rejectValue("image","MultipartNotEmpty");
            }
            if(juego.getInstaller().isEmpty()) {
                bindingResult.rejectValue("installer","MultipartNotEmpty");
            }

                // Manejo de errores, en este caso, volvemos a cargar la vista del formulario
            // para mostrar los errores al usuario.
            List<Categoria> categorias = categoriaRepository.findAll(Sort.by("titulo"));
            return new ModelAndView("admin/nueva-juego")
                    .addObject("juego",juego)
                    .addObject("categorias",categorias);
        }
        String imagen = servicio.almacenarArchivo(juego.getImage());
        juego.setImagen(imagen);
        String instalador = servicio.almacenarArchivo(juego.getInstaller());
        juego.setInstalador(instalador);

        // Guardar el juego en la base de datos
        juegoRepository.save(juego);

        return new ModelAndView("redirect:/admin");
    }
    @GetMapping("/juegos/{id}/editar")
    public ModelAndView mostrarFormularioDeEditarJuego(@PathVariable Long id) {
        Juego juego = juegoRepository.getOne(id);
        List<Categoria> categorias = categoriaRepository.findAll(Sort.by("titulo"));

        return new ModelAndView("admin/editar-juego")
                .addObject("juego",juego)
                .addObject("categorias",categorias);
    }
    @PostMapping("/juegos/{id}/editar")
    public ModelAndView actualizarJuego(@PathVariable long id,@Validated Juego juego,BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<Categoria> categorias = categoriaRepository.findAll(Sort.by("titulo"));
            return new ModelAndView("admin/editar-juego")
                    .addObject("juego",juego)
                    .addObject("categorias",categorias);
        }

        Juego juegoDB = juegoRepository.getOne(id);
        juegoDB.setTitulo(juego.getTitulo());
        juegoDB.setDescripcion(juego.getDescripcion());
        juegoDB.setRequisitos(juego.getRequisitos());
        juegoDB.setCategorias(juego.getCategorias());

        if(!juego.getImage().isEmpty()) {
            servicio.eliminarArchivo(juegoDB.getImagen());
            String imagen = servicio.almacenarArchivo(juego.getImage());
            juegoDB.setImagen(imagen);
        }
        if(!juego.getInstaller().isEmpty()) {
            servicio.eliminarArchivo(juegoDB.getInstalador());
            String instalador = servicio.almacenarArchivo(juego.getInstaller());
            juegoDB.setInstalador(instalador);
        }

       juegoRepository.save(juegoDB);
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping("/juegos/{id}/eliminar")
    public String eliminarJuego(@PathVariable Long id) {
        Juego juego = juegoRepository.getOne(id);
        juegoRepository.delete(juego);
        servicio.eliminarArchivo(juego.getImagen());

        return "redirect:/admin";
    }
}
