package br.com.paulo_ferlin.gestao_vagas.modules.company;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//A relação entre empresa e trabalho é:
//Todo trabalho precisa de uma empresa existente por trás.
//Nem toda empresa precisa de um trabalho ofertado.
//O trabalho não pode existir sem uma empresa por trás, mas a empresa pode existir sem ofertar um trabalho.

//Posteriormente, o candidato se cadastra em um trabalho ofertado por uma empresa.
//Não pode existir um candidato se não houver trabalho e não pode havar trabalho se não existir empresa.
//Empresa -> Trabalho -> Candidato

//Criação de um tabela no banco de dados é feito com o @Entity(name = "") ou @Entity e depois @Table(name = "").

//Os dados pedidos são:
// {"id": , "name": , "email": , "phone": , "password": , "description": , "cnpj": , "address", "site": }
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "company")
public class CompanyEntity {

    private String site;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    @CreationTimestamp
    private LocalDateTime createdAt;
}
