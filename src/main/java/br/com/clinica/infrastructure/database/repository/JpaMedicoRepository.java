package br.com.clinica.infrastructure.database.repository;

import br.com.clinica.infrastructure.database.entity.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMedicoRepository extends JpaRepository<MedicoEntity, UUID> {

    boolean existsByCrm(String crm);

}
