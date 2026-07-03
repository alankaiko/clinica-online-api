package br.com.clinica.usecase.medico;

import br.com.clinica.domain.model.Medico;
import br.com.clinica.domain.repository.MedicoRepository;

import java.util.Objects;

public class CadastrarMedicoUseCase {

    private final MedicoRepository medicoRepository;

    public CadastrarMedicoUseCase(MedicoRepository medicoRepository) {
        this.medicoRepository = Objects.requireNonNull(medicoRepository, "MedicoRepository é obrigatório");
    }

    public Medico executar(Input input) {
        Objects.requireNonNull(input, "Input não pode ser nulo");
        
        if (medicoRepository.existePorCrmETenant(input.crm, input.tenantId))
            throw new IllegalArgumentException("Já existe um médico cadastrado com este CRM");

        Medico medico = new Medico(input.nome, input.crm, input.email, input.tenantId);
        
        return this.medicoRepository.salvar(medico);
    }

    public static class Input {
        public final String tenantId;
        public final String nome;
        public final String crm;
        public final String email;

        public Input(String tenantId, String nome, String crm, String email) {
            this.tenantId = tenantId;
            this.nome = nome;
            this.crm = crm;
            this.email = email;
        }
    }
}
