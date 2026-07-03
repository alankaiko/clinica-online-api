package br.com.clinica.usecase.convenio;

import br.com.clinica.domain.model.Convenio;
import br.com.clinica.domain.repository.ConvenioRepository;

import java.util.Objects;

public class CadastrarConvenioUseCase {

    private final ConvenioRepository convenioRepository;

    public CadastrarConvenioUseCase(ConvenioRepository convenioRepository) {
        this.convenioRepository = Objects.requireNonNull(convenioRepository, "ConvenioRepository é obrigatório");
    }

    public Convenio executar(Input input) {
        Objects.requireNonNull(input, "Input não pode ser nulo");

        if (convenioRepository.existePorCnpjETenant(input.cnpj, input.tenantId))
            throw new IllegalArgumentException("Já existe um convênio cadastrado com este CNPJ");

        Convenio convenio = new Convenio(input.nomeComercial, input.cnpj, input.registroAns, input.email, input.tenantId);

        return this.convenioRepository.salvar(convenio);
    }

    public static class Input {
        public final String tenantId;
        public final String nomeComercial;
        public final String cnpj;
        public final String registroAns;
        public final String email;

        public Input(String tenantId, String nomeComercial, String cnpj, String registroAns, String email) {
            this.tenantId = tenantId;
            this.nomeComercial = nomeComercial;
            this.cnpj = cnpj;
            this.registroAns = registroAns;
            this.email = email;
        }
    }
}
