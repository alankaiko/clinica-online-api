package br.com.clinica.infrastructure.database.repository;

import br.com.clinica.domain.model.Convenio;
import br.com.clinica.domain.pagination.PageInfo;
import br.com.clinica.domain.pagination.PageRequest;
import br.com.clinica.domain.repository.ConvenioRepository;
import br.com.clinica.infrastructure.database.entity.ConvenioEntity;
import br.com.clinica.infrastructure.database.mapper.ConvenioPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostgresConvenioRepository implements ConvenioRepository {

    private final JpaConvenioRepository repository;
    private final ConvenioPersistenceMapper convenioMapper;

    @Override
    public Convenio salvar(Convenio convenio) {
        ConvenioEntity convenioEntity = this.repository.save(this.convenioMapper.toEntity(convenio));
        return this.convenioMapper.toDomain(convenioEntity);
    }

    @Override
    public Optional<Convenio> buscarPorIdETenant(UUID id, String tenantId) {
        return this.repository.findById(id)
                .map(this.convenioMapper::toDomain);
    }

    @Override
    public boolean existePorCnpjETenant(String cnpj, String tenantId) {
        return this.repository.existsByCnpj(cnpj);
    }

    @Override
    public PageInfo<Convenio> listarPorTenant(String tenantId, PageRequest pageRequest) {
        org.springframework.data.domain.PageRequest springPageRequest = 
            org.springframework.data.domain.PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize());
        
        Page<ConvenioEntity> page = this.repository.findAll(springPageRequest);
        
        return new PageInfo<>(
            page.getContent().stream()
                .map(this.convenioMapper::toDomain)
                .collect(Collectors.toList()),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements()
        );
    }
}
