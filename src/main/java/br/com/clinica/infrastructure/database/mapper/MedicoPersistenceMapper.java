package br.com.clinica.infrastructure.database.mapper;

import br.com.clinica.domain.model.Medico;
import br.com.clinica.infrastructure.database.entity.MedicoEntity;
import org.springframework.stereotype.Component;

@Component
public class MedicoPersistenceMapper {

    public Medico toDomain(MedicoEntity entity) {
        if (entity == null) return null;
        
        return new Medico(
            entity.getId(),
            entity.getNome(),
            entity.getCrm(),
            entity.getEmail(),
            entity.getAtivo(),
            entity.getDataCadastro(),
            entity.getTenantId()
        );
    }

    public MedicoEntity toEntity(Medico domain) {
        if (domain == null) return null;
        
        MedicoEntity entity = new MedicoEntity();
        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        entity.setCrm(domain.getCrm().getValor());
        entity.setEmail(domain.getEmail().getValor());
        entity.setAtivo(domain.getAtivo());
        entity.setDataCadastro(domain.getDataCadastro());
        entity.setTenantId(domain.getTenantId());
        
        return entity;
    }
}
