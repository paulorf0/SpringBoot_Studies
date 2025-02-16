package com.biblioteca.biblioteca.Services;

import com.biblioteca.biblioteca.Model.Autor;
import com.biblioteca.biblioteca.Repository.AutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final PasswordEncoder passwordEncoder;


    public Autor save(Autor autor) {
        var senha_criptografia = passwordEncoder.encode(autor.getSenha());
        autor.setSenha(senha_criptografia);
        return autorRepository.save(autor);
    }

    public Autor obterAutor(long id) {
        return autorRepository.findById(id).orElse(null);
    }
}
