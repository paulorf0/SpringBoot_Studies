package com.biblioteca.biblioteca.Model;

import com.biblioteca.biblioteca.Repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class AutorTest {
    @Autowired
    AutorRepository autorRepository;

    @Test
    void criarAutor() {
        var autor = new Autor();

        autor.setEmail("autor4@gmail.com");
        autor.setNome("Maria Dias");
        autor.setTelefone("16981045322");
        autor.setDataNascimento(LocalDate.of(1992, 3, 20));

        autorRepository.save(autor);
    }

    @Test
    @Transactional
    void listarAutores() {
        var autores = autorRepository.findAll();
        System.out.println(autores);
    }

    @Test
    @Transactional
    void pegarAutor() {
        Autor autor = autorRepository.findByEmail("autor@gmail.com").orElse(null);
        if (autor != null)
            System.out.println(autor);
        else
            System.out.println("null");

    }

    @Test
    void deletarAutor() {
        autorRepository.deleteById((long)1);
    }
}