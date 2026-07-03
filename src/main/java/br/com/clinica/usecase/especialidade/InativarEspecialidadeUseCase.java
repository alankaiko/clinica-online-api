package br.com.clinica.usecase.especialidade;

import br.com.clinica.domain.model.Especialidade;
import br.com.clinica.domain.repository.EspecialidadeRepository;

import java.util.Objects;
import java.util.UUID;

public class InativarEspecialidadeUseCase {

    private final EspecialidadeRepository especialidadeRepository;

    public InativarEspecialidadeUseCase(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = Objects.requireNonNull(especialidadeRepository, "EspecialidadeRepository é obrigatório");
    }

    public void executar(UUID id, String tenantId) {
        Objects.requireNonNull(id, "ID é obrigatório");
        Objects.requireNonNull(tenantId, "TenantId é obrigatório");

        Especialidade especialidade = this.especialidadeRepository.buscarPorIdETenant(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Especialidade não encontrada"));

        especialidade.inativar();
        this.especialidadeRepository.salvar(especialidade);
    }
}
