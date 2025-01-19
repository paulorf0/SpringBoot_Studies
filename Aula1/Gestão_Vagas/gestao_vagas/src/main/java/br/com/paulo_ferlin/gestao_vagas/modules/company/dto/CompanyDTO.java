package br.com.paulo_ferlin.gestao_vagas.modules.company.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
public class CompanyDTO {
    private String site;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "(\\d{10,11}|\\d{10})", message = "O formato deve ser XXXXXXXXXX. Não esqueça de inserir o DDD.")
    private String phone;

    @NotBlank
    private String password;

    @NotBlank
    @CNPJ
    private String cnpj;

    @NotBlank
    @Email
    private String email;

    @Length(min = 20, max = 400)
    private String description;

    @Length(min = 10, max = 100, message = "O endereço deve ter no máximo 100 caracteres")
    private String address;
}
