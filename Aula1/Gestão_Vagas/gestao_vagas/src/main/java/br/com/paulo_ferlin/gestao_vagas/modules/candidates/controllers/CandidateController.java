package br.com.paulo_ferlin.gestao_vagas.modules.candidates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo_ferlin.gestao_vagas.exceptions.UserFoundException;
import br.com.paulo_ferlin.gestao_vagas.modules.candidates.CandidateEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/SendCandidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/")
    // Para que o jakarta valide, é necessário adicionar a @Valid antes de receber a
    // informação.
    public CandidateEntity sendCandidate(@Valid @RequestBody CandidateEntity candidateEntity) {
        System.out.println("Nome: " + candidateEntity.getName() + " Cpf: " + candidateEntity.getCpf() + " Email: "
                + candidateEntity.getEmail());

        candidateRepository.findByEmailOrPhoneOrCpf(candidateEntity.getEmail(), candidateEntity.getPhone(),
                candidateEntity.getCpf()).ifPresent(candidate -> {
                    throw new UserFoundException();
                });

        // Dentro do JPARepository, existem métodos pré-definidos para lidar com o ações
        // no banco de dados.
        return candidateRepository.save(candidateEntity);
    }

}
