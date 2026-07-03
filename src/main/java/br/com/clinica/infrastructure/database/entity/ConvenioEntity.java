package br.com.clinica.infrastructure.database.entity;

import br.com.clinica.infrastructure.database.base.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "convenio")
public class ConvenioEntity extends AbstractEntity {

    @Column(name = "nome_comercial", nullable = false)
    private String nomeComercial;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "registro_ans")
    private String registroAns;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
}
