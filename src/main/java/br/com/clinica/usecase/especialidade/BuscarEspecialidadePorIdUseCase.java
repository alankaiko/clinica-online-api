package br.com.clinica.usecase.especialidade;

import br.com.clinica.domain.model.Especialidade;
import br.com.clinica.domain.repository.EspecialidadeRepository;

import java.util.Objects;
import java.util.UUID;

public class BuscarEspecialidadePorIdUseCase {

    private final EspecialidadeRepository especialidadeRepository;

    public BuscarEspecialidadePorIdUseCase(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = Objects.requireNonNull(especialidadeRepository, "EspecialidadeRepository é obrigatório");
    }

    public Especialidade executar(UUID id, String tenantId) {
        Objects.requireNonNull(id, "ID é obrigatório");
        Objects.requireNonNull(tenantId, "TenantId é obrigatório");

        return this.especialidadeRepository.buscarPorIdETenant(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Especialidade não encontrada"));
    }
}
