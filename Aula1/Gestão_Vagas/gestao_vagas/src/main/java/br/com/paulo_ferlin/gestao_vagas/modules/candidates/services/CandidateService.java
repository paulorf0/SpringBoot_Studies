package br.com.paulo_ferlin.gestao_vagas.modules.candidates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.paulo_ferlin.gestao_vagas.exceptions.UserFoundException;
import br.com.paulo_ferlin.gestao_vagas.modules.candidates.CandidateEntity;
import br.com.paulo_ferlin.gestao_vagas.modules.candidates.repositories.CandidateRepository;

//Uma classe que serve para executar um comando. Os comandos são distribuidos para os services e não para os controllers.
@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity candidateExist(CandidateEntity candidateEntity) {
        // Lançando uma exceção que será tratada no controller.
        candidateRepository.findByEmailOrPhoneOrCpf(candidateEntity.getEmail(), candidateEntity.getPhone(),
                candidateEntity.getCpf()).ifPresent(candidate -> {
                    throw new UserFoundException();
                });

        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);
        // Dentro do JPARepository, existem métodos pré-definidos para lidar com o ações
        // no banco de dados.
        return candidateRepository.save(candidateEntity);
    }
}
