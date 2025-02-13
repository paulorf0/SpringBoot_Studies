package com.biblioteca.biblioteca.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "autor")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "livros")
@Data
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    private String senha;

    @Column(nullable = false)
    private LocalDate data_nascimento;

    @Column(unique = true, nullable = false)
    private String telefone;


    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<Livro> livros;

    @Column(name = "data_cadastro", columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime dataCadastro;
}
