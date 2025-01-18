package br.com.paulo_ferlin.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo_ferlin.gestao_vagas.modules.company.JobEntity;
import br.com.paulo_ferlin.gestao_vagas.modules.company.services.JobService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/register/company/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/")
    public ResponseEntity<Object> registerJob(@Valid @RequestBody JobEntity jobEntity) {
        try {
            return ResponseEntity.ok().body(jobService.saveJob(jobEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
