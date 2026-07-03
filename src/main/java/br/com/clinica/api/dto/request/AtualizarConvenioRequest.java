package br.com.clinica.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarConvenioRequest {

    private String nomeComercial;
    private String registroAns;
    private String email;

}
