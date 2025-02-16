package com.biblioteca.biblioteca.Controller.RequestDTO;

import com.biblioteca.biblioteca.Model.Autor;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public record AutorRequestDTO(String nome, String senha, @Email String email, LocalDate data_nascimento, String telefone){

    public Autor paraAutor(){
        var autor = new Autor();
        autor.setNome(nome);
        autor.setEmail(email);
        autor.setSenha(senha);
        autor.setData_nascimento(data_nascimento);
        autor.setTelefone(telefone);
        return autor;
    }
}

