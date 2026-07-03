package br.com.clinica.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarMedicoRequest {

    private String nome;
    private String email;

}
