package br.com.clinica.domain.model;

import br.com.clinica.domain.base.EntityBase;
import br.com.clinica.domain.valueobject.Crm;
import br.com.clinica.domain.valueobject.Email;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class Medico extends EntityBase {

    private String nome;
    private Crm crm;
    private Email email;
    private Set<Especialidade> especialidades;
    private Boolean ativo;

    public Medico(String nome, String crm, String email, String tenantId) {
        super(tenantId);
        this.validarNome(nome);
        
        this.nome = nome;
        this.crm = new Crm(crm);
        this.email = new Email(email);
        this.especialidades = new HashSet<>();
        this.ativo = true;
    }

    public Medico(UUID id, String nome, String crm, String email, Boolean ativo, 
                  LocalDateTime dataCadastro, String tenantId) {
        super(id, dataCadastro, tenantId);
        this.nome = nome;
        this.crm = new Crm(crm);
        this.email = new Email(email);
        this.especialidades = new HashSet<>();
        this.ativo = ativo;
    }

    public void atualizarDados(String nome, String email) {
        this.validarNome(nome);
        
        this.nome = nome;
        this.email = new Email(email);
    }

    public void adicionarEspecialidade(Especialidade especialidade) {
        if (especialidade == null) {
            throw new IllegalArgumentException("Especialidade não pode ser nula");
        }
        
        if (!especialidade.getAtivo()) {
            throw new IllegalArgumentException("Não é possível adicionar uma especialidade inativa");
        }
        
        this.especialidades.add(especialidade);
    }

    public void removerEspecialidade(Especialidade especialidade) {
        this.especialidades.remove(especialidade);
    }

    public void inativar() {
        if (!this.ativo) {
            throw new IllegalStateException("Médico já está inativo");
        }
        this.ativo = false;
    }

    public void ativar() {
        if (this.ativo) {
            throw new IllegalStateException("Médico já está ativo");
        }
        this.ativo = true;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (nome.length() < 3) {
            throw new IllegalArgumentException("Nome deve ter no mínimo 3 caracteres");
        }
    }
}
