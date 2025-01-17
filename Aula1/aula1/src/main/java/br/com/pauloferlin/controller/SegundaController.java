package br.com.pauloferlin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SegundaController {

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Usuario user) {
        if (user.username().equals("Paulo Ferlin") && user.password().equals("josefino"))
            return ResponseEntity.accepted().body("Login efetuado com sucesso");
        else
            return ResponseEntity.badRequest().body("Usuario ou senha invalidos");
    }

    record Usuario(String username, String password) {
    };
}
