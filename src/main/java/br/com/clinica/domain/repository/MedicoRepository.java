package br.com.clinica.domain.repository;

import br.com.clinica.domain.model.Medico;
import br.com.clinica.domain.pagination.PageInfo;
import br.com.clinica.domain.pagination.PageRequest;

import java.util.Optional;
import java.util.UUID;

public interface MedicoRepository {

    Medico salvar(Medico medico);

    Optional<Medico> buscarPorIdTenant(UUID id, String tenantId);

    boolean existePorCrmETenant(String crm, String tenantId);

    PageInfo<Medico> listarPorTenant(String tenantId, PageRequest pageRequest);

}
