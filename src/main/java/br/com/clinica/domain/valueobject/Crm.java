package br.com.clinica.domain.valueobject;

import java.util.Objects;

public class Crm {

    private final String valor;

    public Crm(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("CRM é obrigatório");
        }
        
        String crmLimpo = valor.replaceAll("[^0-9]", "");
        
        if (crmLimpo.length() < 4 || crmLimpo.length() > 6) {
            throw new IllegalArgumentException("CRM deve ter entre 4 e 6 dígitos");
        }
        
        this.valor = crmLimpo;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crm crm = (Crm) o;
        return Objects.equals(valor, crm.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor;
    }
}
