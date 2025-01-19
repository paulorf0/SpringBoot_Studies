package br.com.paulo_ferlin.gestao_vagas.modules.candidates;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;

//Criação de uma tabela.

//Serve para criar os Getters e Setters de maneira automatica, sem poluir o código.
// {"id": , "name": , "curriculum": , "email": , "phone": , "password": , "description": , "cpf": , "address": }
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Pattern(regexp = "\\d{10,11}", message = "Formato de número inválido. Esperado 10 ou 11 números")
    private String phone;

    @NotBlank
    private String password;

    @Length(min = 30, max = 100, message = "A descrição deve ter entre 30 e 100 caracteres")
    private String description;

    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "Formato de CPF inválido. Esperado: 11 números")
    private String cpf;

    @Length(min = 0, max = 100, message = "O endereço deve ter entre 10 e 100 caracteres")
    private String address;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
