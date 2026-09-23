package br.com.ecommerce.util;

import br.com.ecommerce.anotacoes.AuditoriaLogistica;
import br.com.ecommerce.logistica.StatusRastreio;

/**
 * Classe utilitária de cálculo e formatação de operações logísticas.
 */
public class CalculadoraLogistica {

    @AuditoriaLogistica
    public static double calcularCustoFinal(double valorBase, double... taxasAdicionais) {
        double total = valorBase;
        for (double taxa : taxasAdicionais) {
            total += taxa;
        }
        return total;
    }

    public static String formatarResumoOperacao(String codigo, StatusRastreio status, double valorFinal) {
        return String.format("ResumoOperacao{codigo='%s', status=%s, valorFinal=R$ %.2f}",
                codigo, status, valorFinal);
    }
}