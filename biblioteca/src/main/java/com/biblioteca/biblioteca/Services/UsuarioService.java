package com.biblioteca.biblioteca.Services;

import com.biblioteca.biblioteca.Model.Usuario;
import com.biblioteca.biblioteca.Repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario buscar(String email){
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    public Usuario cadastrar(Usuario usuario){
        var encode = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encode);

        return usuarioRepository.save(usuario);
    }
}
