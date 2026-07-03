package br.com.clinica.infrastructure.database.repository;

import br.com.clinica.infrastructure.database.entity.ConvenioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaConvenioRepository extends JpaRepository<ConvenioEntity, UUID> {

    boolean existsByCnpj(String cnpj);

}
