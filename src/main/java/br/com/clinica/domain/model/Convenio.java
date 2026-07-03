package br.com.clinica.domain.model;

import br.com.clinica.domain.base.EntityBase;
import br.com.clinica.domain.valueobject.Cnpj;
import br.com.clinica.domain.valueobject.Email;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Convenio extends EntityBase {

    private String nomeComercial;
    private Cnpj cnpj;
    private String registroAns;
    private Email email;
    private Boolean ativo;

    public Convenio(String nomeComercial, String cnpj, String registroAns, String email, String tenantId) {
        super(tenantId);
        this.validarNomeComercial(nomeComercial);
        this.validarRegistroAns(registroAns);
        
        this.nomeComercial = nomeComercial;
        this.cnpj = new Cnpj(cnpj);
        this.registroAns = registroAns;
        this.email = new Email(email);
        this.ativo = true;
    }

    public Convenio(UUID id, String nomeComercial, String cnpj, String registroAns, 
                    String email, Boolean ativo, LocalDateTime dataCadastro, String tenantId) {
        super(id, dataCadastro, tenantId);
        this.nomeComercial = nomeComercial;
        this.cnpj = new Cnpj(cnpj);
        this.registroAns = registroAns;
        this.email = new Email(email);
        this.ativo = ativo;
    }

    public void atualizarDados(String nomeComercial, String registroAns, String email) {
        this.validarNomeComercial(nomeComercial);
        this.validarRegistroAns(registroAns);
        
        this.nomeComercial = nomeComercial;
        this.registroAns = registroAns;
        this.email = new Email(email);
    }

    public void inativar() {
        if (!this.ativo) {
            throw new IllegalStateException("Convênio já está inativo");
        }
        this.ativo = false;
    }

    public void ativar() {
        if (this.ativo) {
            throw new IllegalStateException("Convênio já está ativo");
        }
        this.ativo = true;
    }

    private void validarNomeComercial(String nomeComercial) {
        if (nomeComercial == null || nomeComercial.isBlank()) {
            throw new IllegalArgumentException("Nome comercial é obrigatório");
        }
        if (nomeComercial.length() < 3) {
            throw new IllegalArgumentException("Nome comercial deve ter no mínimo 3 caracteres");
        }
    }

    private void validarRegistroAns(String registroAns) {
        if (registroAns != null && !registroAns.isBlank()) {
            String registroLimpo = registroAns.replaceAll("[^0-9]", "");
            if (registroLimpo.length() != 6) {
                throw new IllegalArgumentException("Registro ANS deve ter 6 dígitos");
            }
        }
    }
}
