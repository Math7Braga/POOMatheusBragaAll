package modulo10;

import java.util.LinkedList;
import java.util.List;

public class GerenciadorPedidos {

    // Programamos usando a INTERFACE List como tipo de referencia (boa pratica),
    // e escolhemos LinkedList como implementacao concreta por causa das
    // operacoes frequentes de insercao/remocao nas extremidades da fila.
    private List<String> filaPedidos = new LinkedList<>();

    public void adicionarNoFim(String pedido) {
        filaPedidos.add(pedido); // insere no final da lista
    }

    public void adicionarNoInicio(String pedido) {
        filaPedidos.add(0, pedido); // insere no inicio da lista
    }

    public String removerProximo() {
        if (filaPedidos.isEmpty()) {
            return null;
        }
        return filaPedidos.remove(0); // remove e retorna o primeiro elemento
    }

    public void exibirFila() {
        System.out.println("Fila atual de pedidos: " + filaPedidos);
    }

    /*
     * Por que a LinkedList tem desempenho superior ao ArrayList em insercoes
     * frequentes no INICIO da lista?
     *
     * O ArrayList e baseado internamente em um array. Ao inserir ou remover um
     * elemento na posicao 0, TODOS os elementos seguintes precisam ser
     * deslocados uma posicao (a direita na insercao, a esquerda na remocao),
     * o que gera complexidade O(n) para essa operacao.
     *
     * Ja a LinkedList e baseada em nos encadeados (lista duplamente ligada),
     * em que cada no guarda referencias para o proximo e o anterior. Inserir
     * ou remover no inicio significa apenas ajustar algumas referencias
     * (ponteiros), sem precisar deslocar elemento nenhum. Isso resulta em
     * complexidade O(1) para insercoes/remocoes nas extremidades, tornando a
     * LinkedList mais eficiente que o ArrayList nesse cenario especifico
     * (uma fila de processamento de pedidos, por exemplo).
     */
}
