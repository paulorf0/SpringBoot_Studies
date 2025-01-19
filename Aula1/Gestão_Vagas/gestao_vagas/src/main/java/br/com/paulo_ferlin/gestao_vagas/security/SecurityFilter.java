package br.com.paulo_ferlin.gestao_vagas.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.paulo_ferlin.gestao_vagas.modules.company.providers.JWTProvider;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    // Cada um dos argumentos da função abaixo é recebido na requisição de endpoints
    // que estão bloqueados para usuários acessar. Aqueles que estão aberto para
    // usuários acessar, não é passado para o filtro. Esse filtro serve para ser um
    // intermediário entre usuário-endpoint, portanto, sendo algo como
    // usário-filter-endpoint. O filter decide se o usuário vai ter acesso ao
    // endpoint ou não.

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        // No argumento "request", é recebido as informações de request enviado pelo
        // browser. Ex: O header, capturando com request.getHeader("<Campo do header que
        // se deseja. Ex: Authorization>").
        // Response representa a resposta HTTP que será enviada de volta ao cliente.
        // filterChain representa a cadeia de filtros que a requisição deve passar.

        // Apenas por segurança, é setado o contexto de segurança do cliente como nulo,
        // para depois ser válidado.
        SecurityContextHolder.getContext().setAuthentication(null);

        var authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            var sub = jwtProvider.ValidateToken(authHeader);
            if (!sub.isPresent()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // Se fosse uma aplicação maior, poderia ser necessário separar em cargos dentro
            // do site. Apenas companias podem publicar os trabalhos. Os candidatos podem
            // acessar os trabalhos... separação por cargo.

            // O atributo armazenado usando setAttribute é mantido no objeto
            // HttpServletRequest no servidor. Ele não é enviado ao cliente e não é
            // armazenado na máquina do cliente.

            // Pode-se recuperar o atributo posteriormente com .getAttribute().
            System.out.println(sub.get());
            request.setAttribute("company_id", sub.get());

            // Uma classe simples para gerenciar o login de um usuário. Essa classe armazena
            // informações do cliente autenticado, assim como se lee está ou não
            // autenticado. Uma classe simples para gerenciar a autentificação dos usuários.
            // Para uma classe do mesmo tipo, mais robusta e segura, pode-se considerar a
            // "JwtAuthenticationFilter"
            // O construtor de UsernamePasswordAuthenticationToken, recebe alguns
            // argumentos:
            // principal: O objeto que representa o usuário autenticado.
            // credentials: As credenciais do usuário (geralmente null após a autenticação).
            // authorities: As permissões ou autoridades concedidas ao usuário.
            var auth = new UsernamePasswordAuthenticationToken(sub, null, Collections.emptyList());

            // SecurityContextHolder armazena informações de autenticação e autorização para
            // a requisição atual. Por padrão, o SecurityContextHolder usa uma estratégia de
            // armazenamento baseada em thread-local, o que significa que o contexto de
            // segurança é específico para a thread que está processando a requisição.

            // "O SecurityContextHolder armazena informações de autenticação no contexto da
            // thread por padrão. Isso significa que ele não preserva automaticamente o
            // contexto de autenticação entre requisições HTTP/HTTPs". Pode ser, portanto,
            // que entre duas requisições, perca as informações.

            // Neste caso, como não foi feita nenhuma configuração para preservar a sessão
            // entre requisições, sempre que o usuário acessar áreas restritas, ele deve
            // enviar o TOKEN Jwt para ser validado e posterior, se for o caso, permitido o
            // acesso a área.
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

}
