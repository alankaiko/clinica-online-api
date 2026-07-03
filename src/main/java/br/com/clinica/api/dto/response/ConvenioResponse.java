package br.com.clinica.api.dto.response;

import br.com.clinica.domain.model.Convenio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ConvenioResponse {

    private UUID id;
    private String nomeComercial;
    private String cnpj;
    private String registroAns;
    private String email;
    private Boolean ativo;
    private LocalDateTime dataCadastro;

    public static ConvenioResponse fromDomain(Convenio convenio) {
        return new ConvenioResponse(
            convenio.getId(),
            convenio.getNomeComercial(),
            convenio.getCnpj().getValor(),
            convenio.getRegistroAns(),
            convenio.getEmail().getValor(),
            convenio.getAtivo(),
            convenio.getDataCadastro()
        );
    }
}
