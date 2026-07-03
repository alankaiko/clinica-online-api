package br.com.clinica.infrastructure.database.repository;

import br.com.clinica.infrastructure.database.entity.EspecialidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaEspecialidadeRepository extends JpaRepository<EspecialidadeEntity, UUID> {


}
