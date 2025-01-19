package br.com.paulo_ferlin.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo_ferlin.gestao_vagas.modules.company.JobEntity;
import br.com.paulo_ferlin.gestao_vagas.modules.company.dto.JobDTO;
import br.com.paulo_ferlin.gestao_vagas.modules.company.services.JobService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

//Grande lição: Sempre isole as informações do banco de dados do mundo externo. Transforme em DTO's o recebimento dos dados.

@RestController
@RequestMapping("/register/company/job")
public class JobController {

    @Autowired
    private JobService jobService;

    // Uma prática de segurança interessante é que ao invés de receber diretamente
    // um JobEntity, receber um jobDTO. Dessa maneira, mesmo que alguém tente passar
    // atributos manualmente para o servidor, o servidor só vai pegar os atributos
    // que foram definidos no DTO. Assim, não é possível, por exemplo, passar um
    // company_id.

    // Sempre é interessante criar um DTO recebendo apenas as informações
    // necessárias para cadastro em vez de receber um objeto com todos os campos do
    // banco de dados?
    @PostMapping("/")
    public ResponseEntity<Object> registerJob(@Valid @RequestBody JobDTO jobDTO, HttpServletRequest request) {
        try {
            // Sempre que um cliente entra em um endpoint, o cliente envia um request que
            // pode ser obtido no argumento do controller. No request possuí todas as
            // informações, inclusive o atributo salvo. Esse atributo foi salvo no arquivo
            // configurado para filtro de requisição. Sempre que um cliente acessa um
            // endpoint que ele não tem acesso, a requisição passa por um filtro que decide
            // se o usuário pode acessar o endpoint ou não, nesse filtro, é adicionado nos
            // atributos do cliente o token de acesso, que pode ser resgado no request que é
            // passado para o argumento do controller.
            var payload_request = request.getAttribute("company_id");
            var company_id = UUID.fromString(payload_request.toString());

            var jobEntity = JobEntity.builder()
                    .benefits(jobDTO.getBenefits())
                    .level(jobDTO.getLevel())
                    .description(jobDTO.getDescription())
                    .company_id(company_id)
                    .build();
            // O build() no final é para transformar o JobEntity.builder() em uma classe
            // JobEntity. Até o comando JobEntity.builder(), jobEntity é do tipo
            // JobEntityBuilder.

            return ResponseEntity.ok().body(jobService.saveJob(jobEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
