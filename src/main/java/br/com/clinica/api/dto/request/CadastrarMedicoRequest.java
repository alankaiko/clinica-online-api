package br.com.clinica.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarMedicoRequest {

    private String nome;
    private String crm;
    private String email;

}
