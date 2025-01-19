package br.com.paulo_ferlin.gestao_vagas.modules.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.paulo_ferlin.gestao_vagas.exceptions.UserFoundException;
import br.com.paulo_ferlin.gestao_vagas.modules.company.CompanyEntity;
import br.com.paulo_ferlin.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    // Como PasswordEncoder já foi configurar na pasta de "SecurityConfig.java",
    // quando instancia essa classe de codificação, ela já vem definida com a
    // criptografia escolhida.
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity companyExists(CompanyEntity companyEntity) {
        companyRepository
                .findFirstByCnpjOrEmailOrPhone(companyEntity.getCnpj(), companyEntity.getEmail(),
                        companyEntity.getPhone())
                .ifPresent(company -> {
                    // Observe que estou utilizando a mesma mensagem de erro para usuário e para
                    // empresa. Por ser um projeto que envolve tudo em uma mesma aplicação, ocorre
                    // isso. Normalmente, é separado em micro-serviços; um para empresa, outro para
                    // usuário.
                    throw new UserFoundException();
                });

        // Criptografando a senha antes de enviar para o banco de dados. Utilizando a
        // criptografia configurar na pasta "SecurityConfig.java".
        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return companyRepository.save(companyEntity);
    }
}
