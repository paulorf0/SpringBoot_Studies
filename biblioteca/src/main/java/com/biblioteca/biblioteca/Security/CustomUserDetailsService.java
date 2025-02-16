package com.biblioteca.biblioteca.Security;

import com.biblioteca.biblioteca.Services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// Responsável por fazer a conexão da segurança da aplicação com o banco de dados. Dessa maneira, os dados de ROLES, autorização por senha e login é puxado diretamente do banco de dados em vez de ficar em memória
// A utilização do "banco de dados" em memória é implementada nas configurações de segurança, no método "userDetailsService".
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername( String login) throws UsernameNotFoundException {
        // Lógica de autenticação do usuário. "login" seria o método de identificação do sistema
        var usuario = usuarioService.buscar(login);

        if(usuario == null)
            throw new UsernameNotFoundException("Usuário não encontrado");

        return User.builder()
                .username(usuario.getNome())
                .password(usuario.getSenha())
                .roles(usuario.getRoles().toArray(new String[usuario.getRoles().size()]))
                .build();
    }
}
