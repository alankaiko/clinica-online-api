package br.com.clinica.infrastructure.database.entity;

import br.com.clinica.infrastructure.database.base.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "especialidade")
public class EspecialidadeEntity extends AbstractEntity {

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

}
