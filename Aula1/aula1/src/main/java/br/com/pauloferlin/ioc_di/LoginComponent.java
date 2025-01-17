package br.com.pauloferlin.ioc_di;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class LoginComponent {

    public String login(Map<String, String> param) {
        if (param.get("username").equals("Paulo Ferlin") && param.get("password").equals("josefino"))
            return "Login efetuado com sucesso";
        else
            return "Usuario ou senha invalidos";
    }
}
