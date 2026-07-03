package br.com.clinica.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarConvenioRequest {

    private String nomeComercial;
    private String cnpj;
    private String registroAns;
    private String email;

}
