package com.biblioteca.biblioteca.Repository;

import com.biblioteca.biblioteca.Model.Autor;
import com.biblioteca.biblioteca.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByAutor(Autor autor);
}
