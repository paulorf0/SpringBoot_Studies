package br.com.paulo_ferlin.gestao_vagas.modules.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paulo_ferlin.gestao_vagas.modules.company.JobEntity;
import br.com.paulo_ferlin.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity saveJob(JobEntity jobEntity) {
        return jobRepository.save(jobEntity);
    }
}
