package br.com.clinica.domain.base;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class EntityBase {

    private UUID id;
    private LocalDateTime dataCadastro;
    private String tenantId;

    protected EntityBase() {
        this.id = UUID.randomUUID();
        this.dataCadastro = LocalDateTime.now();
    }

    protected EntityBase(String tenantId) {
        if (tenantId == null || tenantId.isBlank()) {
            throw new IllegalArgumentException("TenantId é obrigatório");
        }
        this.id = UUID.randomUUID();
        this.dataCadastro = LocalDateTime.now();
        this.tenantId = tenantId;
    }

    protected EntityBase(UUID id, LocalDateTime dataCadastro, String tenantId) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (dataCadastro == null) {
            throw new IllegalArgumentException("Data de cadastro não pode ser nula");
        }
        if (tenantId == null || tenantId.isBlank()) {
            throw new IllegalArgumentException("TenantId não pode ser nulo");
        }
        this.id = id;
        this.dataCadastro = dataCadastro;
        this.tenantId = tenantId;
    }

    protected void setId(UUID id) {
        this.id = id;
    }

    protected void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    protected void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
