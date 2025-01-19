package br.com.paulo_ferlin.gestao_vagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.paulo_ferlin.gestao_vagas.modules.company.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    // Estava ocorrendo um erro de findBy estar encontrando mais de um usuario com
    // os mesmos dados, e a função que esperava ter um retorno unico, retornava uma
    // lista. Mudei para findFirstBy, para poder ter o retorno do primeiro usuario
    // que tenha as informações que batem com as enviadas.
    Optional<CompanyEntity> findFirstByCnpjOrEmailOrPhone(String cnpj, String email, String phone);

    Optional<CompanyEntity> findByEmail(String email);

}
