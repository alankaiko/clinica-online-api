package br.com.clinica.usecase.medico;

import br.com.clinica.domain.model.Medico;
import br.com.clinica.domain.pagination.PageInfo;
import br.com.clinica.domain.pagination.PageRequest;
import br.com.clinica.domain.repository.MedicoRepository;

import java.util.Objects;

public class ListarMedicosUseCase {

    private final MedicoRepository medicoRepository;

    public ListarMedicosUseCase(MedicoRepository medicoRepository) {
        this.medicoRepository = Objects.requireNonNull(medicoRepository, "MedicoRepository é obrigatório");
    }

    public PageInfo<Medico> executar(String tenantId, PageRequest pageRequest) {
        Objects.requireNonNull(tenantId, "TenantId é obrigatório");
        Objects.requireNonNull(pageRequest, "PageRequest é obrigatório");

        return this.medicoRepository.listarPorTenant(tenantId, pageRequest);
    }
}
