package br.com.clinica.infrastructure.database.entity;

import br.com.clinica.infrastructure.database.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "medico")
public class MedicoEntity extends AbstractEntity {

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "crm", nullable = false, unique = true)
    private String crm;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "medico_especialidade",
            joinColumns = @JoinColumn(name = "medico_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidade_id")
    )
    private Set<EspecialidadeEntity> especialidades = new HashSet<>();

}
