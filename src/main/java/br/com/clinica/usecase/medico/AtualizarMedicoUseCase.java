package br.com.clinica.usecase.medico;

import br.com.clinica.domain.model.Medico;
import br.com.clinica.domain.repository.MedicoRepository;

import java.util.Objects;
import java.util.UUID;

public class AtualizarMedicoUseCase {

    private final MedicoRepository medicoRepository;

    public AtualizarMedicoUseCase(MedicoRepository medicoRepository) {
        this.medicoRepository = Objects.requireNonNull(medicoRepository, "MedicoRepository é obrigatório");
    }

    public Medico executar(Input input) {
        Objects.requireNonNull(input, "Input não pode ser nulo");
        Objects.requireNonNull(input.id, "ID é obrigatório");
        Objects.requireNonNull(input.tenantId, "TenantId é obrigatório");

        Medico medico = this.medicoRepository.buscarPorIdTenant(input.id, input.tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

        medico.atualizarDados(input.nome, input.email);

        return this.medicoRepository.salvar(medico);
    }

    public static class Input {
        public final UUID id;
        public final String tenantId;
        public final String nome;
        public final String email;

        public Input(UUID id, String tenantId, String nome, String email) {
            this.id = id;
            this.tenantId = tenantId;
            this.nome = nome;
            this.email = email;
        }
    }
}
