package br.com.paulo_ferlin.gestao_vagas.modules.candidates;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;

//Criação de uma tabela.

//Serve para criar os Getters e Setters de maneira automatica, sem poluir o código.
// {"id": , "name": , "curriculum": , "email": , "phone": , "password": , "description": , "cpf": , "address": }
@Data
// Criando uma tabela chamada candidates no banco de dados.
@Entity(name = "candidates")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // O jakarta considera que essas variáveis são o nome das colunas no banco de
    // dados. Caso queira que a coluna se chame
    // diferente, basta adicionar a anotação @Column(name = "nome_da_coluna")
    private String curriculum;

    // @Email serve para validação de um email válido.
    // @Length também funciona para limitar o tamanho.
    // @Pattern para regex.

    @NotBlank
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "\\(\\d{2}\\)\\d{8,9}|\\d{10,11}", message = "Formato de número inválido. Esperado: (XX)XXXXXXXXX ou XXXXXXXXXXX")
    private String phone;

    @NotBlank
    @Length(min = 8, max = 30, message = "A senha deve ter entre 8 e 30 caracteres")
    private String password;

    @Length(min = 30, max = 100, message = "A descrição deve ter entre 30 e 100 caracteres")
    private String description;

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11}", message = "Formato de CPF inválido. Esperado: XXX.XXX.XXX-XX ou XXXXXXXXXXX")
    private String cpf;

    @Length(min = 0, max = 100, message = "O endereço deve ter entre 10 e 100 caracteres")
    private String address;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
