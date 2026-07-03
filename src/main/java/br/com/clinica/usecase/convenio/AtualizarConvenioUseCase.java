package br.com.clinica.usecase.convenio;

import br.com.clinica.domain.model.Convenio;
import br.com.clinica.domain.repository.ConvenioRepository;

import java.util.Objects;
import java.util.UUID;

public class AtualizarConvenioUseCase {

    private final ConvenioRepository convenioRepository;

    public AtualizarConvenioUseCase(ConvenioRepository convenioRepository) {
        this.convenioRepository = Objects.requireNonNull(convenioRepository, "ConvenioRepository é obrigatório");
    }

    public Convenio executar(Input input) {
        Objects.requireNonNull(input, "Input não pode ser nulo");
        Objects.requireNonNull(input.id, "ID é obrigatório");
        Objects.requireNonNull(input.tenantId, "TenantId é obrigatório");

        Convenio convenio = this.convenioRepository.buscarPorIdETenant(input.id, input.tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Convênio não encontrado"));

        convenio.atualizarDados(input.nomeComercial, input.registroAns, input.email);

        return this.convenioRepository.salvar(convenio);
    }

    public static class Input {
        public final UUID id;
        public final String tenantId;
        public final String nomeComercial;
        public final String registroAns;
        public final String email;

        public Input(UUID id, String tenantId, String nomeComercial, String registroAns, String email) {
            this.id = id;
            this.tenantId = tenantId;
            this.nomeComercial = nomeComercial;
            this.registroAns = registroAns;
            this.email = email;
        }
    }
}
