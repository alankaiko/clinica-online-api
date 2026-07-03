package br.com.clinica.domain.repository;

import br.com.clinica.domain.model.Especialidade;
import br.com.clinica.domain.pagination.PageInfo;
import br.com.clinica.domain.pagination.PageRequest;

import java.util.Optional;
import java.util.UUID;

public interface EspecialidadeRepository {

    Especialidade salvar(Especialidade especialidade);

    Optional<Especialidade> buscarPorIdETenant(UUID id, String tenantId);

    PageInfo<Especialidade> listarPorTenant(String tenantId, PageRequest pageRequest);

}
