package br.com.pauloferlin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pauloferlin.ioc_di.LoginComponent;

@RestController
@RequestMapping("/")
public class SegundaController {

    // Serve para dizer para o springboot que ele deve gerenciar essa classe
    // loginComponent, que é um componente.
    // Portanto, não precisamos nos preocupar quando ela é instanciada ou não.
    @Autowired
    LoginComponent loginComponent;

    @PostMapping("/loginSemComponente")
    // Foi retornado um "Object", que pode ser qualquer tipo de classe java.
    // Utilização do ResponseEntity, que serve para fazer retorno ao cliente.
    // Algo interessante, é que nos métodos de requisição (GET, POST), não existe
    // lógica envolvida. A lógica é feita em um componente de serviço.
    public ResponseEntity<Object> login(@RequestBody Usuario user) {
        if (user.username().equals("Paulo Ferlin") && user.password().equals("josefino"))
            return ResponseEntity.accepted().body("Login efetuado com sucesso");
        else
            return ResponseEntity.badRequest().body("Usuario ou senha invalidos");
    }

    @PostMapping("/loginComComponente")
    public ResponseEntity<Object> loginComComponente(@RequestBody Map<String, String> param) {
        var response = loginComponent.login(param);
        return ResponseEntity.accepted().body(response);
    }

    record Usuario(String username, String password) {
    };
}
