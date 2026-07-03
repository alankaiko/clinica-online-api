package br.com.clinica.usecase.medico;

import br.com.clinica.domain.model.Medico;
import br.com.clinica.domain.repository.MedicoRepository;

import java.util.Objects;
import java.util.UUID;

public class BuscarMedicoPorIdUseCase {

    private final MedicoRepository medicoRepository;

    public BuscarMedicoPorIdUseCase(MedicoRepository medicoRepository) {
        this.medicoRepository = Objects.requireNonNull(medicoRepository, "MedicoRepository é obrigatório");
    }

    public Medico executar(UUID id, String tenantId) {
        Objects.requireNonNull(id, "ID é obrigatório");
        Objects.requireNonNull(tenantId, "TenantId é obrigatório");

        return this.medicoRepository.buscarPorIdTenant(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));
    }
}
