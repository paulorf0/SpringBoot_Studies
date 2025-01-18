package br.com.pauloferlin.ioc_di;

import java.util.Map;

import org.springframework.stereotype.Component;

//Componentes são classes que você quer que o SpringBoot gerencie.
//Gerenciar, no caso, instanciar, apagar, etc...
@Component
public class LoginComponent {

    public String login(Map<String, String> param) {
        if (param.get("username").equals("Paulo Ferlin") && param.get("password").equals("josefino"))
            return "Login efetuado com sucesso";
        else
            return "Usuario ou senha invalidos";
    }
}
