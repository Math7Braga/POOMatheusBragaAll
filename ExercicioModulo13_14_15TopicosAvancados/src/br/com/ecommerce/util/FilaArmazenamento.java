package br.com.ecommerce.util;

import br.com.ecommerce.anotacoes.AuditoriaLogistica;

/**
 * Estrutura genérica (FIFO) de armazenamento em galpão.
 * Manuseia qualquer tipo T sem castings manuais na recuperação.
 */
@AuditoriaLogistica
public class FilaArmazenamento<T> {

    private static class No<E> {
        private E dado;
        private No<E> proximo;

        private No(E dado) {
            this.dado = dado;
        }
    }

    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    public void enfileirar(T elemento) {
        No<T> novo = new No<>(elemento);
        if (fim != null) {
            fim.proximo = novo;
        } else {
            inicio = novo;
        }
        fim = novo;
        tamanho++;
    }

    public T desenfileirar() {
        if (estaVazia()) {
            throw new IllegalStateException("Fila vazia: nada a desenfileirar.");
        }
        T removido = inicio.dado;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return removido;
    }

    public int getTamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return inicio == null;
    }
}