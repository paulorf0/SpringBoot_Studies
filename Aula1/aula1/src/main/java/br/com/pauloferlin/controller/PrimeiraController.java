package br.com.pauloferlin.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Quando você usa a anotação @RestController, os métodos da classe retornam 
// diretamente os dados (geralmente em formato JSON ou XML) em vez de retornar 
// uma "visão". Isso é útil para construir APIs RESTful, onde o foco está em fornecer 
// dados para serem consumidos por clientes (como aplicativos web ou móveis) em vez 
// de renderizar páginas HTML.

//Diz que essa classe é um controller.
@RestController
// Diz qual é o endpoint que essa classe vai responder.
@RequestMapping("/estoque")
public class PrimeiraController {

    // Paramêtros de path são aqueles que vem junto da requisição, são obrigatórios.
    // Ex: www.site.com.br/helloworld/1. "/1", 1 seria o paramêtro de path.

    // Caso queira receber um parametro de "path" na URL, o paramêtro é pegado
    // utilizando /{param}
    // Exemplo: @GetMapping("/helloworld/{nome}")
    @GetMapping("/camisa/{id}")
    public String camisa(@PathVariable String id) {
        return "Modelo de camisa" + id;
    }

    // RequestParam diz que o paramêtro é um query param, ou seja, ele é passado na
    // URL.
    // Ex: www.site.com.br/helloworld?nome=Paulo. "?nome=Paulo", nome seria o query
    // param.
    @GetMapping("/calca")
    // Mapeia os parametros. A chave é o nome do parametro.
    public String calcaQueryParam(@RequestParam Map<String, String> params) {
        // Anteriormente utilizava .entrySet(), não é mais funcional.
        return params.toString();
    }

    @PostMapping("/comprar")
    public String comprar(@RequestBody Params params) {
        // Processa os dados recebidos no formato JSON
        return "Username: " + params.username() + " Password: " + params.password();
    }

    @PostMapping("/comprar2")
    // Caso queira receber uma lista, basta fazer um Map.
    // @RequestHeader Map<String, String> params
    public String comprar2(@RequestHeader("nome") String nomeString) {
        return "Nome: " + nomeString;
    }

    // Nota: Usando uma lista de parametros, todos os parametros padrão do header
    // vem junto no params. Os parametros sempre vem minuscúlos.
    @PostMapping("/comprar_listaParams")
    public String comprar2(@RequestHeader Map<String, String> params) {
        return "Todos parametros: " + params.toString() + " params: " + params.get("nome") + " Senha: "
                + params.get("senha");
    }

    record Params(String username, String password) {
    }

}
