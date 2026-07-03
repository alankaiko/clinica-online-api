package br.com.clinica.domain.model;

import br.com.clinica.domain.base.EntityBase;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Especialidade extends EntityBase {

    private String descricao;
    private Boolean ativo;

    public Especialidade(String descricao, String tenantId) {
        super(tenantId);
        this.validarDescricao(descricao);
        
        this.descricao = descricao;
        this.ativo = true;
    }

    public Especialidade(UUID id, String descricao, Boolean ativo, 
                         LocalDateTime dataCadastro, String tenantId) {
        super(id, dataCadastro, tenantId);
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public void atualizarDescricao(String descricao) {
        this.validarDescricao(descricao);
        this.descricao = descricao;
    }

    public void inativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    private void validarDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição é obrigatória");
        }
    }
}
