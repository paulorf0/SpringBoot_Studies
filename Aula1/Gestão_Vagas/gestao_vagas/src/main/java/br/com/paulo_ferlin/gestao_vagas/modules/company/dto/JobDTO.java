package br.com.paulo_ferlin.gestao_vagas.modules.company.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobDTO {
    @NotBlank
    @Length(min = 20, max = 400)
    private String description;

    @NotBlank
    @Length(min = 3, max = 100)
    private String level;

    @NotBlank
    @Length(min = 20, max = 400)
    private String benefits;
}
