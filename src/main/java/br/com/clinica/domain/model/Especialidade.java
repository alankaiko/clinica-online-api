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
        if (!this.ativo) {
            throw new IllegalStateException("Especialidade já está inativa");
        }
        this.ativo = false;
    }

    public void ativar() {
        if (this.ativo) {
            throw new IllegalStateException("Especialidade já está ativa");
        }
        this.ativo = true;
    }

    private void validarDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição é obrigatória");
        }
        if (descricao.length() < 3) {
            throw new IllegalArgumentException("Descrição deve ter no mínimo 3 caracteres");
        }
        if (descricao.length() > 100) {
            throw new IllegalArgumentException("Descrição deve ter no máximo 100 caracteres");
        }
    }
}
