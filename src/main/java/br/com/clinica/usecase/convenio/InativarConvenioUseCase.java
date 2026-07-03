package br.com.clinica.usecase.convenio;

import br.com.clinica.domain.model.Convenio;
import br.com.clinica.domain.repository.ConvenioRepository;

import java.util.Objects;
import java.util.UUID;

public class InativarConvenioUseCase {

    private final ConvenioRepository convenioRepository;

    public InativarConvenioUseCase(ConvenioRepository convenioRepository) {
        this.convenioRepository = Objects.requireNonNull(convenioRepository, "ConvenioRepository é obrigatório");
    }

    public void executar(UUID id, String tenantId) {
        Objects.requireNonNull(id, "ID é obrigatório");
        Objects.requireNonNull(tenantId, "TenantId é obrigatório");

        Convenio convenio = this.convenioRepository.buscarPorIdETenant(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Convênio não encontrado"));

        convenio.inativar();
        this.convenioRepository.salvar(convenio);
    }
}
