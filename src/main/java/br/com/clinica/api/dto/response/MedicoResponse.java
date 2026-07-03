package br.com.clinica.api.dto.response;

import br.com.clinica.domain.model.Medico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MedicoResponse {

    private UUID id;
    private String nome;
    private String crm;
    private String email;
    private Boolean ativo;
    private LocalDateTime dataCadastro;

    public static MedicoResponse fromDomain(Medico medico) {
        return new MedicoResponse(
            medico.getId(),
            medico.getNome(),
            medico.getCrm().getValor(),
            medico.getEmail().getValor(),
            medico.getAtivo(),
            medico.getDataCadastro()
        );
    }
}
