package br.com.clinica.domain.model;

import br.com.clinica.domain.base.EntityBase;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class Medico extends EntityBase {

    private String nome;
    private String crm;
    private String email;
    private Set<Especialidade> especialidades;
    private Boolean ativo;

    public Medico(String nome, String crm, String email, String tenantId) {
        super(tenantId);
        this.validarNome(nome);
        this.validarCrm(crm);
        this.validarEmail(email);
        
        this.nome = nome;
        this.crm = crm;
        this.email = email;
        this.especialidades = new HashSet<>();
        this.ativo = true;
    }

    public Medico(UUID id, String nome, String crm, String email, Boolean ativo, 
                  LocalDateTime dataCadastro, String tenantId) {
        super(id, dataCadastro, tenantId);
        this.nome = nome;
        this.crm = crm;
        this.email = email;
        this.especialidades = new HashSet<>();
        this.ativo = ativo;
    }

    public void atualizarDados(String nome, String email) {
        this.validarNome(nome);
        this.validarEmail(email);
        
        this.nome = nome;
        this.email = email;
    }

    public void adicionarEspecialidade(Especialidade especialidade) {
        if (especialidade == null) {
            throw new IllegalArgumentException("Especialidade não pode ser nula");
        }
        this.especialidades.add(especialidade);
    }

    public void removerEspecialidade(Especialidade especialidade) {
        this.especialidades.remove(especialidade);
    }

    public void inativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
    }

    private void validarCrm(String crm) {
        if (crm == null || crm.isBlank()) {
            throw new IllegalArgumentException("CRM é obrigatório");
        }
    }

    private void validarEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
    }
}
