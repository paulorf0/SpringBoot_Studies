package br.com.paulo_ferlin.gestao_vagas.modules.candidates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo_ferlin.gestao_vagas.modules.candidates.CandidateEntity;
import br.com.paulo_ferlin.gestao_vagas.modules.candidates.dto.CandidateDTO;
import br.com.paulo_ferlin.gestao_vagas.modules.candidates.services.CandidateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/register/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateExist;

    @PostMapping("/")
    // Para que o jakarta valide, é necessário adicionar a @Valid antes de receber a
    // informação.
    public ResponseEntity<Object> registerCandidate(@Valid @RequestBody CandidateDTO candidateDTO) {
        try {
            // Chama a função do Service e verifica se as informações enviadas batem com
            // algum candidato já existente.
            var candidateEntity = CandidateEntity.builder()
                    .name(candidateDTO.getName())
                    .curriculum(candidateDTO.getCurriculum())
                    .email(candidateDTO.getEmail())
                    .phone(candidateDTO.getPhone())
                    .password(candidateDTO.getPassword())
                    .description(candidateDTO.getDescription())
                    .cpf(candidateDTO.getCpf())
                    .address(candidateDTO.getAddress())
                    .build();

            var result = this.candidateExist.candidateExist(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
