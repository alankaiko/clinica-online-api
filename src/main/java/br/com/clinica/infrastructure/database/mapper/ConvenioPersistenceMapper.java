package br.com.clinica.infrastructure.database.mapper;

import br.com.clinica.domain.model.Convenio;
import br.com.clinica.infrastructure.database.entity.ConvenioEntity;
import org.springframework.stereotype.Component;

@Component
public class ConvenioPersistenceMapper {

    public Convenio toDomain(ConvenioEntity entity) {
        if (entity == null) return null;
        
        return new Convenio(
            entity.getId(),
            entity.getNomeComercial(),
            entity.getCnpj(),
            entity.getRegistroAns(),
            entity.getEmail(),
            entity.getAtivo(),
            entity.getDataCadastro(),
            entity.getTenantId()
        );
    }

    public ConvenioEntity toEntity(Convenio domain) {
        if (domain == null) return null;
        
        ConvenioEntity entity = new ConvenioEntity();
        entity.setId(domain.getId());
        entity.setNomeComercial(domain.getNomeComercial());
        entity.setCnpj(domain.getCnpj().getValor());
        entity.setRegistroAns(domain.getRegistroAns());
        entity.setEmail(domain.getEmail().getValor());
        entity.setAtivo(domain.getAtivo());
        entity.setDataCadastro(domain.getDataCadastro());
        entity.setTenantId(domain.getTenantId());
        
        return entity;
    }
}
