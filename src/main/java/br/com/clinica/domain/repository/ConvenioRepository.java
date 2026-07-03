package br.com.clinica.domain.repository;

import br.com.clinica.domain.model.Convenio;
import br.com.clinica.domain.pagination.PageInfo;
import br.com.clinica.domain.pagination.PageRequest;

import java.util.Optional;
import java.util.UUID;

public interface ConvenioRepository {

    Convenio salvar(Convenio convenio);

    Optional<Convenio> buscarPorIdETenant(UUID id, String tenantId);

    boolean existePorCnpjETenant(String cnpj, String tenantId);

    PageInfo<Convenio> listarPorTenant(String tenantId, PageRequest pageRequest);

}
