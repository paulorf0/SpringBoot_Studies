package br.com.paulo_ferlin.gestao_vagas.modules.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paulo_ferlin.gestao_vagas.exceptions.UserFoundException;
import br.com.paulo_ferlin.gestao_vagas.modules.company.CompanyEntity;
import br.com.paulo_ferlin.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity companyExists(CompanyEntity companyEntity) {
        companyRepository
                .findByCnpjOrEmailOrPhoneOrSite(companyEntity.getCnpj(), companyEntity.getEmail(),
                        companyEntity.getPhone(), companyEntity.getSite())
                .ifPresent(company -> {
                    // Observe que estou utilizando a mesma mensagem de erro para usuário e para
                    // empresa. Por ser um projeto que envolve tudo em uma mesma aplicação, ocorre
                    // isso. Normalmente, é separado em micro-serviços; um para empresa, outro para
                    // usuário.
                    throw new UserFoundException();
                });

        return companyRepository.save(companyEntity);
    }
}
