package br.com.ecommerce.app;

import br.com.ecommerce.excecoes.EstoqueInvalidoException;
import br.com.ecommerce.excecoes.LogisticaException;
import br.com.ecommerce.logistica.ItemLogistico;
import br.com.ecommerce.logistica.PacoteExpresso;

/**
 * Demonstração do BLOCO 1: construtores encadeados, visibilidade estrita,
 * multi-catch, precedência de finally sobre return e try-finally sem catch.
 */
public class DemonstradorBloco1App {

    public static void main(String[] args) {
        System.out.println("=== BLOCO 1: Engenharia de Classes e Tratamento de Exceções ===\n");

        // 1) Instanciação válida dentro do try + tratamento conjunto (multi-catch)
        try {
            PacoteExpresso pacote = new PacoteExpresso("PAC-0001", 4.5, 0.12, true);
            System.out.println("Pacote válido criado: " + pacote);
            System.out.printf("Frete base: R$ %.2f%n", pacote.calcularFreteBase());
            pacote.setDespachado(true);
            System.out.println("Despachado? " + pacote.isDespachado());
            System.out.println("Total de itens gerados (bloco static): " + ItemLogistico.getTotalItensGeradosNaJvm());
        } catch (LogisticaException | EstoqueInvalidoException e) {
            System.out.println("[ERRO]" + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        // 2) LogisticaException (CHECKED) -> etiqueta curta, exige throws/tratamento
        try {
            new PacoteExpresso("AB", 2.0);
        } catch (LogisticaException | EstoqueInvalidoException e) {
            System.out.println("[LogisticaException - checked] etiqueta curta: " + e.getMessage());
        }

        // 3) EstoqueInvalidoException (UNCHECKED) -> peso <= 0
        try {
            new PacoteExpresso("PAC-9999", -1.0);
        } catch (LogisticaException | EstoqueInvalidoException e) {
            System.out.println("[" + e.getClass().getSimpleName() + " - unchecked] peso inválido: " + e.getMessage());
        }

        System.out.println("\n-- Precedência: finally executa ANTES da devolução do return --");
        String retorno = metodoComFinallyAntesDoReturn();
        System.out.println("Valor efetivamente devolvido ao chamador: " + retorno);

        System.out.println("\n-- try-finally SEM catch (liberação determinística de rotina local) --");
        liberarRecursoComTryFinally();
        System.out.println("[main] instrução posterior ao try-finally também executou.");
    }

    static String metodoComFinallyAntesDoReturn() {
        try {
            System.out.println("  [try] retorno 'FINALIZADO-OK' calculado...");
            return "FINALIZADO-OK";
        } finally {
            System.out.println("  [finally] log de auditoria gravado ANTES do valor chegar ao chamador");
        }
    }

    static void liberarRecursoComTryFinally() {
        try {
            System.out.println("  [try] recurso local em uso (simulação de conexão)...");
            int linhaLida = 42;
            System.out.println("  [try] dado lido do recurso: " + linhaLida);
        } finally {
            System.out.println("  [finally] recurso liberado/close executado deterministicamente");
        }
    }
}