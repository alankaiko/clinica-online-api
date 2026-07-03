package br.com.clinica.usecase.especialidade;

import br.com.clinica.domain.model.Especialidade;
import br.com.clinica.domain.repository.EspecialidadeRepository;

import java.util.Objects;

public class CadastrarEspecialidadeUseCase {

    private final EspecialidadeRepository especialidadeRepository;

    public CadastrarEspecialidadeUseCase(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = Objects.requireNonNull(especialidadeRepository, "EspecialidadeRepository é obrigatório");
    }

    public Especialidade executar(Input input) {
        Objects.requireNonNull(input, "Input não pode ser nulo");

        Especialidade especialidade = new Especialidade(input.descricao, input.tenantId);

        return this.especialidadeRepository.salvar(especialidade);
    }

    public static class Input {
        public final String tenantId;
        public final String descricao;

        public Input(String tenantId, String descricao) {
            this.tenantId = tenantId;
            this.descricao = descricao;
        }
    }
}
