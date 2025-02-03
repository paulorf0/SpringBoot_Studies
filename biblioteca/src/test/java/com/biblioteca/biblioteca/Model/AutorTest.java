package com.biblioteca.biblioteca.Model;

import com.biblioteca.biblioteca.Repository.AutorRepository;
import com.biblioteca.biblioteca.Repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
class AutorTest {
    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

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
        autorRepository.deleteById((long) 1);
    }

    @Test
    void salvarAutorComLivro() {
        Autor autor = new Autor();
        Livro livro1 = new Livro();
        Livro livro2 = new Livro();

        autor.setEmail("autor5@gmail.com");
        autor.setNome("Thaís Dias");
        autor.setTelefone("34999457732");
        autor.setDataNascimento(LocalDate.of(2004, 11, 18));

        livro1.setTitulo("Casa Assombrada");
        livro1.setPreco(BigDecimal.valueOf(99.90));
        livro1.setDataPublicacao(LocalDate.of(1999, 12, 2));
        livro1.setAutor(autor);

        livro2.setTitulo("Mansão Abandonada");
        livro2.setPreco(BigDecimal.valueOf(99.90));
        livro2.setDataPublicacao(LocalDate.of(1999, 12, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro1);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);
        livroRepository.saveAll(autor.getLivros());

        // Ou utilizar Cascade para salvar, mas utilizar Cascade não é recomendado pois o gerenciamento é perigoso
    }
}