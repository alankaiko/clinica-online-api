package br.com.clinica.infrastructure.database.mapper;

import br.com.clinica.domain.model.Especialidade;
import br.com.clinica.infrastructure.database.entity.EspecialidadeEntity;
import org.springframework.stereotype.Component;

@Component
public class EspecialidadePersistenceMapper {

    public Especialidade toDomain(EspecialidadeEntity entity) {
        if (entity == null) return null;
        
        return new Especialidade(
            entity.getId(),
            entity.getDescricao(),
            entity.getAtivo(),
            entity.getDataCadastro(),
            entity.getTenantId()
        );
    }

    public EspecialidadeEntity toEntity(Especialidade domain) {
        if (domain == null) return null;
        
        EspecialidadeEntity entity = new EspecialidadeEntity();
        entity.setId(domain.getId());
        entity.setDescricao(domain.getDescricao());
        entity.setAtivo(domain.getAtivo());
        entity.setDataCadastro(domain.getDataCadastro());
        entity.setTenantId(domain.getTenantId());
        
        return entity;
    }
}
