package com.biblioteca.biblioteca.Repository;

import com.biblioteca.biblioteca.Model.Autor;
import com.biblioteca.biblioteca.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

}
