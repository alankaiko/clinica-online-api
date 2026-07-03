package br.com.clinica.api.dto.response;

import br.com.clinica.domain.model.Especialidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class EspecialidadeResponse {

    private UUID id;
    private String descricao;
    private Boolean ativo;
    private LocalDateTime dataCadastro;

    public static EspecialidadeResponse fromDomain(Especialidade especialidade) {
        return new EspecialidadeResponse(
            especialidade.getId(),
            especialidade.getDescricao(),
            especialidade.getAtivo(),
            especialidade.getDataCadastro()
        );
    }
}
