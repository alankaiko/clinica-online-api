package br.com.clinica.infrastructure.database.repository;

import br.com.clinica.domain.model.Especialidade;
import br.com.clinica.domain.pagination.PageInfo;
import br.com.clinica.domain.pagination.PageRequest;
import br.com.clinica.domain.repository.EspecialidadeRepository;
import br.com.clinica.infrastructure.database.entity.EspecialidadeEntity;
import br.com.clinica.infrastructure.database.mapper.EspecialidadePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostgresEspecialidadeRepository implements EspecialidadeRepository {

    private final JpaEspecialidadeRepository repository;
    private final EspecialidadePersistenceMapper especialidadeMapper;

    @Override
    public Especialidade salvar(Especialidade especialidade) {
        EspecialidadeEntity especialidadeEntity = this.repository.save(this.especialidadeMapper.toEntity(especialidade));
        return this.especialidadeMapper.toDomain(especialidadeEntity);
    }

    @Override
    public Optional<Especialidade> buscarPorIdETenant(UUID id, String tenantId) {
        return this.repository.findById(id)
                .map(this.especialidadeMapper::toDomain);
    }

    @Override
    public PageInfo<Especialidade> listarPorTenant(String tenantId, PageRequest pageRequest) {
        org.springframework.data.domain.PageRequest springPageRequest = 
            org.springframework.data.domain.PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize());
        
        Page<EspecialidadeEntity> page = this.repository.findAll(springPageRequest);
        
        return new PageInfo<>(
            page.getContent().stream()
                .map(this.especialidadeMapper::toDomain)
                .collect(Collectors.toList()),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements()
        );
    }
}
