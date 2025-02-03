package com.biblioteca.biblioteca.Model;

import com.biblioteca.biblioteca.Repository.AutorRepository;
import com.biblioteca.biblioteca.Repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class LivroTest {

    @Autowired
    LivroRepository livroRepository;
@Autowired
    AutorRepository autorRepository;


    @Test
    void createLivro(){
        Livro livro = new Livro();
        livro.setTitulo("Vida secas");
        livro.setPreco(BigDecimal.valueOf(99.90));
        livro.setDataPublicacao(LocalDate.of(1999, 12, 2));

        livroRepository.save(livro);
    }

    @Test
    void adicionarAutorLivro(){
        var autor = autorRepository.findById((long)3).orElse(null);
        var livro = livroRepository.findById((long)1).orElse(null);

        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    void salvarLivroComAutor(){
        var autor = autorRepository.findById((long)4).orElse(null);
        var livro = new Livro();

        livro.setTitulo("Capitães da Areia");
        livro.setPreco(BigDecimal.valueOf(120.5));
        livro.setDataPublicacao(LocalDate.of(2000, 5, 10));
        livro.setAutor(autor);

        livroRepository.save(livro);
    }

}
