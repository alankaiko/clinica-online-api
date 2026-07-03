package br.com.clinica.domain.model;

import br.com.clinica.domain.base.EntityBase;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Convenio extends EntityBase {

    private String nomeComercial;
    private String cnpj;
    private String registroAns;
    private String email;
    private Boolean ativo;

    public Convenio(String nomeComercial, String cnpj, String registroAns, String email, String tenantId) {
        super(tenantId);
        this.validarNomeComercial(nomeComercial);
        this.validarCnpj(cnpj);
        this.validarEmail(email);
        
        this.nomeComercial = nomeComercial;
        this.cnpj = cnpj;
        this.registroAns = registroAns;
        this.email = email;
        this.ativo = true;
    }

    public Convenio(UUID id, String nomeComercial, String cnpj, String registroAns, 
                    String email, Boolean ativo, LocalDateTime dataCadastro, String tenantId) {
        super(id, dataCadastro, tenantId);
        this.nomeComercial = nomeComercial;
        this.cnpj = cnpj;
        this.registroAns = registroAns;
        this.email = email;
        this.ativo = ativo;
    }

    public void atualizarDados(String nomeComercial, String registroAns, String email) {
        this.validarNomeComercial(nomeComercial);
        this.validarEmail(email);
        
        this.nomeComercial = nomeComercial;
        this.registroAns = registroAns;
        this.email = email;
    }

    public void inativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    private void validarNomeComercial(String nomeComercial) {
        if (nomeComercial == null || nomeComercial.isBlank()) {
            throw new IllegalArgumentException("Nome comercial é obrigatório");
        }
    }

    private void validarCnpj(String cnpj) {
        if (cnpj == null || cnpj.isBlank()) {
            throw new IllegalArgumentException("CNPJ é obrigatório");
        }
    }

    private void validarEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
    }
}
