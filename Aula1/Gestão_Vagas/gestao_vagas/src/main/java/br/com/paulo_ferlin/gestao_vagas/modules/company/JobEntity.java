package br.com.paulo_ferlin.gestao_vagas.modules.company;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//Campos:
// {"id": "", "description": "", "level": "", "benefits": "", "companyID"}

@Data
@Entity(name = "job")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Length(min = 20, max = 400)
    private String description;

    @NotBlank
    @Length(min = 3, max = 100)
    private String level;

    @NotBlank
    @Length(min = 20, max = 400)
    private String benefits;

    // Essa key é para poder ter um relacionamento com uma empresa, se o trabalho
    // existe, deve ter o ID da empresa no qual esse trabalho está relacionado.
    // Portanto, isso é uma key estrangeira, pois ela é obtida em outra tabela.
    // Como um trabalho está atrelado a apenas uma empresa, então basta instanciar a
    // entidade empresa para poder obter o ID da empresa.

    // Deve se atentar que é necessário colocar o tipo de relacionado DESSA tabela
    // para a OUTRA tabela. É um relacionado que parte da tabela que está mexendo
    // nesse momento para a outra tabela.

    // A minha tabela job para a tabela company. Pode haver muitos job (Many) para
    // uma empresa (To one). Não existe muitas empresas para um job, caso exista,
    // deveria ser @OneToMany. Se fosse de muitos para muitos, @ManyToMany.
    @ManyToOne()
    // O JoinColumn faz um relacionamento entre a chave primaria que vem de
    // CompanyEntity com a chave estrangeira da coluna company_id. Caso tente
    // inserir uma chave inexistente no CompanyEntity, deve dar um erro.

    // Adicionado "insertable = false, updatable = false", pois se não eu não
    // consigo criar uma coluna manualmente chamada company_id. O springboot acha
    // que estaria criando duas tabelas iguais, pois joincolumn já cria uma tabela
    // automaticamente.
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity company;
    // Em cima está sendo criado essa associação da coluna criada abaixo com a chave
    // primária da tabela de empresas. Resumo: definiu o tipo de relacionamento
    // (@ManyToOne) e depois utilizou o JoinColumn para dizer que a coluna
    // "company_id" dessa tabela atual vai se relacionar com a chave primária. Se
    // relaciona com a chave primária pois não está sendo dito que deve se
    // relacionar com outra chave. Caso queira se relacionar com outra chave, deve
    // dizer no segundo argumento o nome da coluna que se quer.

    // Aqui está sendo criada uma coluna que será usada para referenciar uma chave
    // primaria em outra tabela.
    @Column(name = "company_id")
    private UUID companyID;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
