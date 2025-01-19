package br.com.paulo_ferlin.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//Um DTO (Data Transfer Object) serve para transferir dados entre diferentes camadas da 
// apalicação. Ele pega os dados necessários e transformar em um objeto, dessa maenira 
// fica compactado o que deve ser transferido ou usado. Neste caso, como é necessário para 
// autentificação apenas o usuário e a senha, é mais interessante criar um DTO com apenas 
// essas duas informações para deixar o código legível.

@Data
// AllArgsConstructor serve para fazer um construtor de maneira automática com
// todos os dados que foram criados dentro da classe.
@AllArgsConstructor
public class AuthCompanyDTO {
    private String email;
    private String password;
}
