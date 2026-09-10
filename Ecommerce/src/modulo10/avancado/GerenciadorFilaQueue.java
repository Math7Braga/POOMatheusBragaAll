package modulo10.avancado;

import java.util.LinkedList;
import java.util.Queue;

public class GerenciadorFilaQueue {

    // A interface Queue formaliza o contrato FIFO (First-In-First-Out).
    // Usamos LinkedList como implementacao, mas referenciada pelo tipo Queue,
    // reforcando o principio de "programar voltado a interface".
    private Queue<String> fila = new LinkedList<>();

    public void adicionarPedido(String pedido) {
        // offer() insere o elemento no final da fila. E a forma "segura" de
        // insercao do contrato Queue: retorna false em vez de lancar excecao
        // caso a insercao nao seja possivel (por exemplo, em filas de
        // capacidade limitada).
        boolean sucesso = fila.offer(pedido);
        System.out.println("Pedido '" + pedido + "' adicionado a fila? " + sucesso);
    }

    public void consultarProximo() {
        // peek() apenas consulta (espia) o elemento na cabeca da fila,
        // SEM remove-lo. Retorna null se a fila estiver vazia.
        String proximo = fila.peek();
        System.out.println("Proximo pedido a ser processado (sem remover): " + proximo);
    }

    public void processarProximo() {
        // poll() remove e retorna o elemento na cabeca da fila.
        // Retorna null (em vez de lancar excecao) se a fila estiver vazia,
        // o que e mais seguro que o metodo remove() em cenarios incertos.
        String processado = fila.poll();
        if (processado != null) {
            System.out.println("Pedido processado e removido da fila: " + processado);
        } else {
            System.out.println("Fila vazia - nada para processar.");
        }
    }

    public void exibirFila() {
        System.out.println("Estado atual da fila: " + fila);
    }
}
