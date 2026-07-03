package br.com.clinica.usecase.especialidade;

import br.com.clinica.domain.model.Especialidade;
import br.com.clinica.domain.pagination.PageInfo;
import br.com.clinica.domain.pagination.PageRequest;
import br.com.clinica.domain.repository.EspecialidadeRepository;

import java.util.Objects;

public class ListarEspecialidadesUseCase {

    private final EspecialidadeRepository especialidadeRepository;

    public ListarEspecialidadesUseCase(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = Objects.requireNonNull(especialidadeRepository, "EspecialidadeRepository é obrigatório");
    }

    public PageInfo<Especialidade> executar(String tenantId, PageRequest pageRequest) {
        Objects.requireNonNull(tenantId, "TenantId é obrigatório");
        Objects.requireNonNull(pageRequest, "PageRequest é obrigatório");

        return this.especialidadeRepository.listarPorTenant(tenantId, pageRequest);
    }
}
