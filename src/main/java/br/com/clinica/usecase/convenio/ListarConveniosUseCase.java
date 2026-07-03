package br.com.clinica.usecase.convenio;

import br.com.clinica.domain.model.Convenio;
import br.com.clinica.domain.pagination.PageInfo;
import br.com.clinica.domain.pagination.PageRequest;
import br.com.clinica.domain.repository.ConvenioRepository;

import java.util.Objects;

public class ListarConveniosUseCase {

    private final ConvenioRepository convenioRepository;

    public ListarConveniosUseCase(ConvenioRepository convenioRepository) {
        this.convenioRepository = Objects.requireNonNull(convenioRepository, "ConvenioRepository é obrigatório");
    }

    public PageInfo<Convenio> executar(String tenantId, PageRequest pageRequest) {
        Objects.requireNonNull(tenantId, "TenantId é obrigatório");
        Objects.requireNonNull(pageRequest, "PageRequest é obrigatório");

        return this.convenioRepository.listarPorTenant(tenantId, pageRequest);
    }
}
