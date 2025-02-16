package com.biblioteca.biblioteca.Controller.RequestDTO;

import com.biblioteca.biblioteca.Model.Autor;
import com.biblioteca.biblioteca.Model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LivroRequestDTO(String titulo, BigDecimal preco, LocalDate data_publicacao, Long id_autor) {


    public Livro paraLivro(){
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setPreco(preco);
        livro.setData_publicacao(data_publicacao);

        return livro;
    }
}
