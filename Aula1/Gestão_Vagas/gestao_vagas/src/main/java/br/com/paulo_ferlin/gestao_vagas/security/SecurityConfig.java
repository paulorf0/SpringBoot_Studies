package br.com.paulo_ferlin.gestao_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    // Distinção importante: Autentificação é a verificação de que um usuário está
    // registrado no banco de dados. O usuário passa o login para poder autentificar
    // no site.

    // Autorização é verificar se um usuário tem permissão de acessar um certo tipo
    // de
    // rota(https://auth0.com/resources/ebooks/jwt-handbook?utm_source=google&utm_campaign=amer_latam-pt_bra_all_ciam-all_dg-ao_auth0_search_google_text_kw_eng-generic-authentication_utm2&utm_medium=cpc&utm_id=aNK4z000000UCvTGAW&gad_source=1&gclid=Cj0KCQiAv628BhC2ARIsAIJIiK8I2P_2l6v9ox0Rjz6arGLlO152EoMua8R178bhGAJsLZgMiOsAu74aAlYEEALw_wcB).

    // A ideia é que quando o usuário se autentificar, é enviado um token JWT (Ou
    // outro), para evitar que o usuário tenha que ficar digitando login toda vez.

    // Serve para dizer que vai sobrescrever um método que já foi definido
    // anteriormente.
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF (Cross-Site Request Forgery). Em API-RestFul, pode desabilitar; pois
        // cliente e servidor ficam separados.
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/register/candidate/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register/company/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/company/").permitAll()
                        .anyRequest().authenticated());
        // Será adicionado um filtro que tem como objetivo lidar com todo request que a
        // página web receba. Dessa maneira, ele decide se algum usuário vai passar por
        // aquele url ou não.
        http.addFilterBefore(securityFilter, BasicAuthenticationFilter.class);
        // No segundo argumento, existe algumas possibilidades do que colocar.

        // UsernamePasswordAuthenticationFilter.class: Adiciona o filtro antes
        // do filtro de autenticação padrão baseado em nome de usuário e senha.
        // BasicAuthenticationFilter.class: Adiciona o filtro antes do filtro de
        // autenticação básica.
        // SecurityContextPersistenceFilter.class: Adiciona o filtro antes do filtro que
        // persiste o contexto de segurança entre requisições.
        // CsrfFilter.class: Adiciona o filtro antes do filtro de proteção contra CSRF.
        // FilterSecurityInterceptor.class: Adiciona o filtro antes do filtro que aplica
        // as regras de segurança definidas.

        // Para o caso desse projeto, o BasicAuthenticationFilter.class, é interessante
        // de se adicionar. Pois, é necessário que seja válido o token antes de iniciar
        // uma validação nas URL que não são permitidas entrar. Observe que nas URL de
        // registro, o BasicAuthenticationFilter não faz efeito, como deve ser.

        // requestMatchers diz para o pacote de segurança que um usuário pode fazer
        // requisição sem pedir permissão. E em anyRequest().authenticated() diz que as
        // outras rotas precisam de autenticação para poder acessar.

        return http.build();
    }

    // Aqui é possível escolher o tipo de codificação de senha que se quer. Até o
    // momento, a senha está sendo gravada no bando de dados em dado bruto. Caso
    // alguém invada o sistema e tenha acesso ao banco de dados, terá acesso ao dado
    // bruto dos usuários.

    // Uma das possiblidades de codificação é BCrypt e Pbkdf2, resistentes a ataque
    // de força bruta.

    // Outro é SCrypt, resistente a ataque de hardware (Um ataque de hardware
    // refere-se a um tipo de ataque em que o invasor utiliza hardware
    // especializado, como GPUs (Unidades de Processamento Gráfico) ou ASICs
    // (Circuitos Integrados de Aplicação Específica), para realizar cálculos
    // intensivos de forma muito mais rápida do que seria possível com CPUs
    // (Unidades de Processamento Central) comuns.)

    // DelegatingPasswordEncoder permite que faça uma migração de um algoritmo para
    // outro de codificação.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
