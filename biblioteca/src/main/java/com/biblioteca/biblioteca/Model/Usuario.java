package com.biblioteca.biblioteca.Model;

import com.biblioteca.biblioteca.Model.Conversor.ListaStringParaString;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String email;
    private String senha;

    @Convert(converter = ListaStringParaString.class)
    private List<String> roles;
}
