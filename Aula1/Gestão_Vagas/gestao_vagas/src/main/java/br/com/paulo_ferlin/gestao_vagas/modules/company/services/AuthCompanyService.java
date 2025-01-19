package br.com.paulo_ferlin.gestao_vagas.modules.company.services;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.paulo_ferlin.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.paulo_ferlin.gestao_vagas.modules.company.providers.JWTProvider;
import br.com.paulo_ferlin.gestao_vagas.modules.company.repositories.CompanyRepository;

// Observe que até neste ponto, está sendo criado diversos tipos de arquivo para que possa separar a funcionalidade do sistema. Cada pasta lida com um tipo de funcionalidade no sistema.
// Esse arquivo ainda é um service, pois lida com a funcionalidade critica do sistema.
// Neste arquivo será feito a lógica de autentificação através dos tokens JWT. Portanto, aqui ficará a lógica de login de usuários.

@Service
public class AuthCompanyService {

    @Value("${security.secret.key}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;

    // Recebe as informações da empresa.
    public String AuthenticationLogic(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {

        // findByEmail retorna um Optional<>, caso tenha encontrado, será recebido um
        // CompanyEntity.
        // orElse(T other): Retorna o valor contido se presente, caso contrário, retorna
        // o valor fornecido.
        var company = companyRepository.findByEmail(authCompanyDTO.getEmail()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Usuario/Senha inválida.");
        });

        // .matches serve para verificar se uma senha em texto bruto é igual a uma senha
        // que já foi criptografada anteriormente.
        var verifiedPass = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
        // Observe que se as senhas não forem iguais é retornado um Exception.
        if (!verifiedPass)
            throw new AuthenticationException("Senha incorreta.");

        // Se a senha tiver dado certo, deve ser retorna um Token de autentificação para
        // o usuário.

        var token = jwtProvider.generateToken(company.getId().toString());
        return token;

        // Está sendo passado apenas o ID da empresa, pois nesse projeto de aplicação a
        // ideia é utilizar o ID da empresa como company_id na tabela de job. Portanto,
        // é útil passar para o token a id da empresa, pois dessa maneira, pode-se
        // utilizar o dado que está dentro do token para fazer o registro de uma vaga.

        // O registro de uma vaga só pode ser feito se a pessoa estiver logado no site.
    }
}
