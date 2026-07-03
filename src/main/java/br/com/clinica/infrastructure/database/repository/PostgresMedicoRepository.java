package br.com.clinica.infrastructure.database.repository;

import br.com.clinica.domain.model.Medico;
import br.com.clinica.domain.pagination.PageInfo;
import br.com.clinica.domain.pagination.PageRequest;
import br.com.clinica.domain.repository.MedicoRepository;
import br.com.clinica.infrastructure.database.entity.MedicoEntity;
import br.com.clinica.infrastructure.database.mapper.MedicoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostgresMedicoRepository implements MedicoRepository {

    private final JpaMedicoRepository repository;
    private final MedicoPersistenceMapper medicoMapper;

    @Override
    public Medico salvar(Medico medico) {
        MedicoEntity medicoEntity = this.repository.save(this.medicoMapper.toEntity(medico));
        return this.medicoMapper.toDomain(medicoEntity);
    }

    @Override
    public Optional<Medico> buscarPorIdTenant(UUID id, String tenantId) {
        return this.repository.findById(id)
                .map(this.medicoMapper::toDomain);
    }

    @Override
    public boolean existePorCrmETenant(String crm, String tenantId) {
        return this.repository.existsByCrm(crm);
    }

    @Override
    public PageInfo<Medico> listarPorTenant(String tenantId, PageRequest pageRequest) {
        org.springframework.data.domain.PageRequest springPageRequest = 
            org.springframework.data.domain.PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize());
        
        Page<MedicoEntity> page = this.repository.findAll(springPageRequest);
        
        return new PageInfo<>(
            page.getContent().stream()
                .map(this.medicoMapper::toDomain)
                .collect(Collectors.toList()),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements()
        );
    }
}
