package br.com.paulo_ferlin.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo_ferlin.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.paulo_ferlin.gestao_vagas.modules.company.services.AuthCompanyService;

@RestController
@RequestMapping("/auth")
public class AuthenticatedController {

    @Autowired
    private AuthCompanyService authCompanyService;

    @PostMapping("/company/")
    // AuthCompanyDTO = {"email": "", "password": ""}
    public ResponseEntity<Object> AuthCompany(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            var result = authCompanyService.AuthenticationLogic(authCompanyDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
