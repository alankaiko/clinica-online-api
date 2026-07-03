package br.com.clinica.usecase.especialidade;

import br.com.clinica.domain.model.Especialidade;
import br.com.clinica.domain.repository.EspecialidadeRepository;

import java.util.Objects;
import java.util.UUID;

public class AtualizarEspecialidadeUseCase {

    private final EspecialidadeRepository especialidadeRepository;

    public AtualizarEspecialidadeUseCase(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = Objects.requireNonNull(especialidadeRepository, "EspecialidadeRepository é obrigatório");
    }

    public Especialidade executar(Input input) {
        Objects.requireNonNull(input, "Input não pode ser nulo");
        Objects.requireNonNull(input.id, "ID é obrigatório");
        Objects.requireNonNull(input.tenantId, "TenantId é obrigatório");

        Especialidade especialidade = this.especialidadeRepository.buscarPorIdETenant(input.id, input.tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Especialidade não encontrada"));

        especialidade.atualizarDescricao(input.descricao);

        return this.especialidadeRepository.salvar(especialidade);
    }

    public static class Input {
        public final UUID id;
        public final String tenantId;
        public final String descricao;

        public Input(UUID id, String tenantId, String descricao) {
            this.id = id;
            this.tenantId = tenantId;
            this.descricao = descricao;
        }
    }
}
