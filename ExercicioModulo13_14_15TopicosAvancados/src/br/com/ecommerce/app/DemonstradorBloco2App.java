package br.com.ecommerce.app;

import static br.com.ecommerce.logistica.StatusRastreio.EM_TRANSITO;
import static br.com.ecommerce.logistica.StatusRastreio.ENTREGUE;
import static br.com.ecommerce.logistica.StatusRastreio.EXTRAVIADO;
import static br.com.ecommerce.util.CalculadoraLogistica.calcularCustoFinal;

import br.com.ecommerce.excecoes.LogisticaException;
import br.com.ecommerce.logistica.PacoteExpresso;
import br.com.ecommerce.logistica.StatusRastreio;
import br.com.ecommerce.util.CalculadoraLogistica;
import br.com.ecommerce.util.FilaArmazenamento;

/**
 * Demonstração do BLOCO 2: enums com atributos, Generics (FilaArmazenamento<T>),
 * anotação customizada, varargs, String.format e static import.
 */
public class DemonstradorBloco2App {

    public static void main(String[] args) throws LogisticaException {
        System.out.println("=== BLOCO 2: Enums, Generics, Varargs e Anotações ===\n");

        System.out.println("-- Enum StatusRastreio (atributos + métodos) --");
        for (StatusRastreio status : StatusRastreio.values()) {
            System.out.printf("  %-18s prazo=%d dia(s) | assinatura=%s | finalizado=%s%n",
                    status, status.getPrazoDiasUteis(), status.isRequerAssinatura(), status.isFinalizado());
        }
        // Constantes referenciadas via static import
        System.out.println("  (static import) EM_TRANSITO.isFinalizado()? " + EM_TRANSITO.isFinalizado()
                + " | ENTREGUE.isFinalizado()? " + ENTREGUE.isFinalizado());

        System.out.println("\n-- FilaArmazenamento<PacoteExpresso> (Generics sem cast manual) --");
        FilaArmazenamento<PacoteExpresso> fila = new FilaArmazenamento<>();
        fila.enfileirar(new PacoteExpresso("PAC-A100", 2.5));
        fila.enfileirar(new PacoteExpresso("PAC-A200", 7.1, 0.35, true));
        fila.enfileirar(new PacoteExpresso("PAC-A300", 1.2));
        System.out.println("  Tamanho após enfileirar 3 pacotes: " + fila.getTamanho());
        System.out.println("  Fila vazia? " + fila.estaVazia());
        while (!fila.estaVazia()) {
            PacoteExpresso removido = fila.desenfileirar();
            System.out.println("  Desenfileirado (tipado, sem cast): " + removido.getCodigoEtiqueta());
        }
        System.out.println("  Tamanho final após desenfileirar tudo: " + fila.getTamanho());

        System.out.println("\n-- CalculadoraLogistica com varargs (métodos via static import) --");
        double custoComQuatroTaxas = calcularCustoFinal(100.0, 5.0, 3.0, 12.5, 2.0);
        System.out.printf("  calcularCustoFinal(100, 5, 3, 12.5, 2) = R$ %.2f%n", custoComQuatroTaxas);
        double custoComUmaTaxa = calcularCustoFinal(250.0, 9.99);
        System.out.printf("  calcularCustoFinal(250, 9.99)          = R$ %.2f%n", custoComUmaTaxa);

        System.out.println("\n-- String.format (formatação estilo printf) --");
        System.out.println("  " + CalculadoraLogistica.formatarResumoOperacao("PED-2026-0001", ENTREGUE, custoComQuatroTaxas));
        System.out.println("  " + CalculadoraLogistica.formatarResumoOperacao("PED-2026-0002", EXTRAVIADO, custoComUmaTaxa));
    }
}