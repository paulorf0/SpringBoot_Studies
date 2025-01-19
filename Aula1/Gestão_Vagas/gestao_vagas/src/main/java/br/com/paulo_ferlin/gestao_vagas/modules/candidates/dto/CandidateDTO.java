package br.com.paulo_ferlin.gestao_vagas.modules.candidates.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateDTO {
    @NotBlank
    private String name;

    private String curriculum;

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
}
