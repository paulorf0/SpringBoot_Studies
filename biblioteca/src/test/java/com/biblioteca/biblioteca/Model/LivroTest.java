package com.biblioteca.biblioteca.Model;

import com.biblioteca.biblioteca.Repository.AutorRepository;
import com.biblioteca.biblioteca.Repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.beans.Transient;
import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class LivroTest {

    @Autowired
    LivroRepository livroRepository;
    @Autowired
    AutorRepository autorRepository;


    @Test
    void createLivro() {
        Livro livro = new Livro();
        livro.setTitulo("Vida secas");
        livro.setPreco(BigDecimal.valueOf(99.90));
        livro.setDataPublicacao(LocalDate.of(1999, 12, 2));

        livroRepository.save(livro);
    }

    @Test
    void adicionarAutorLivro() {
        var autor = autorRepository.findById((long) 3).orElse(null);
        var livro = livroRepository.findById((long) 1).orElse(null);

        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    @Transactional
    void encontrarAutorLivro() {
        var livro = livroRepository.findById((long) 5).orElse(null);
        Hibernate.initialize(livro.getAutor());
        System.out.println(livro.getAutor());


    }

}
