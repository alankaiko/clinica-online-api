package br.com.clinica.usecase.convenio;

import br.com.clinica.domain.model.Convenio;
import br.com.clinica.domain.repository.ConvenioRepository;

import java.util.Objects;
import java.util.UUID;

public class BuscarConvenioPorIdUseCase {

    private final ConvenioRepository convenioRepository;

    public BuscarConvenioPorIdUseCase(ConvenioRepository convenioRepository) {
        this.convenioRepository = Objects.requireNonNull(convenioRepository, "ConvenioRepository é obrigatório");
    }

    public Convenio executar(UUID id, String tenantId) {
        Objects.requireNonNull(id, "ID é obrigatório");
        Objects.requireNonNull(tenantId, "TenantId é obrigatório");

        return this.convenioRepository.buscarPorIdETenant(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Convênio não encontrado"));
    }
}
