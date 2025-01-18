package br.com.paulo_ferlin.gestao_vagas.modules.candidates.controllers;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.paulo_ferlin.gestao_vagas.modules.candidates.CandidateEntity;

//Repository é uma notação para aquelas classes/interfaces que fazem a comunicação entre a aplicação e o banco de dados.

// O JPARepository é uma interface que possui métodos pré-definidos para lidar com ações no banco de dados.
//Neste ponto do código, não é necessário adicionar @Repository, pois o JPARepository já é uma interface que possui essa anotação.
@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

    Optional<CandidateEntity> findByEmailOrPhoneOrCpf(String email, String phone, String cpf);

}
