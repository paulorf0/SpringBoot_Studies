package br.com.paulo_ferlin.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Algumas informações batem dados já cadastrado.");
    }
}
