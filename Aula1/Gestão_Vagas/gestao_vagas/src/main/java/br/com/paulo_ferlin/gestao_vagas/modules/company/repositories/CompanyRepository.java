package br.com.paulo_ferlin.gestao_vagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.paulo_ferlin.gestao_vagas.modules.company.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    Optional<CompanyEntity> findByCnpjOrEmailOrPhoneOrSite(String cnpj, String email, String phone, String site);
}
