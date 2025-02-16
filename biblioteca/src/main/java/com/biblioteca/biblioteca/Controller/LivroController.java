package com.biblioteca.biblioteca.Controller;


import com.biblioteca.biblioteca.Services.AutorService;
import com.biblioteca.biblioteca.Services.LivroService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;
    private final AutorService autorService;

    public LivroController(LivroService livroService, AutorService autorService) {
        this.livroService = livroService;
        this.autorService = autorService;
    }
}
