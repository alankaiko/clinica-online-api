package br.com.clinica.domain.valueobject;

import java.util.Objects;

public class Cnpj {

    private final String valor;

    public Cnpj(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("CNPJ é obrigatório");
        }
        
        String cnpjLimpo = valor.replaceAll("[^0-9]", "");
        
        if (cnpjLimpo.length() != 14) {
            throw new IllegalArgumentException("CNPJ deve ter 14 dígitos");
        }
        
        if (!validarCnpj(cnpjLimpo)) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
        
        this.valor = cnpjLimpo;
    }

    private boolean validarCnpj(String cnpj) {
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        try {
            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * pesos1[i];
            }
            int digito1 = (soma % 11 < 2) ? 0 : 11 - (soma % 11);

            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * pesos2[i];
            }
            int digito2 = (soma % 11 < 2) ? 0 : 11 - (soma % 11);

            return Character.getNumericValue(cnpj.charAt(12)) == digito1 &&
                   Character.getNumericValue(cnpj.charAt(13)) == digito2;
        } catch (Exception e) {
            return false;
        }
    }

    public String getValor() {
        return valor;
    }

    public String getValorFormatado() {
        return String.format("%s.%s.%s/%s-%s",
            valor.substring(0, 2),
            valor.substring(2, 5),
            valor.substring(5, 8),
            valor.substring(8, 12),
            valor.substring(12, 14)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cnpj cnpj = (Cnpj) o;
        return Objects.equals(valor, cnpj.valor);
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
