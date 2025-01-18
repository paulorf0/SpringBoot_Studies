package br.com.paulo_ferlin.gestao_vagas.modules.company.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.paulo_ferlin.gestao_vagas.modules.company.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

}
