package com.biblioteca.biblioteca.Controller.RequestDTO;

import com.biblioteca.biblioteca.Model.Usuario;

import java.util.List;

public record UsuarioRequestDTO(String nome, String email, String senha, List<String> roles) {

    public Usuario paraUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setRoles(roles);
        return usuario;
    }

}
