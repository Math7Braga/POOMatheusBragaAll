package modulo10.avancado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modulo10.ComparadorPorPreco;
import modulo10.Produto;

public class UtilitariosCollections {

    public void demonstrarUtilitarios(List<Produto> produtosOriginal) {
        // Criamos uma copia para nao embaralhar/inverter a lista original do chamador.
        List<Produto> lista = new ArrayList<>(produtosOriginal);
        System.out.println("Lista original: " + lista);

        // Collections.shuffle() embaralha aleatoriamente a ordem dos elementos.
        // Uso tipico: sortear produtos em destaque na home do e-commerce.
        Collections.shuffle(lista);
        System.out.println("Lista embaralhada (shuffle): " + lista);

        // Collections.reverse() inverte a ordem atual dos elementos da lista.
        Collections.reverse(lista);
        System.out.println("Lista invertida (reverse): " + lista);

        // Collections.min() percorre a colecao e retorna o menor elemento
        // segundo o Comparator informado (aqui, o produto mais barato).
        Produto maisBarato = Collections.min(lista, new ComparadorPorPreco());
        System.out.println("Produto mais barato (Collections.min com Comparator): " + maisBarato);

        // Collections.synchronizedList() encapsula (wrapper) uma lista comum,
        // tornando-a segura para acesso concorrente por multiplas threads,
        // de forma parecida com o que o Vector oferece nativamente.
        List<Produto> listaSincronizada = Collections.synchronizedList(lista);
        System.out.println("Lista encapsulada com synchronizedList, pronta para uso concorrente.");

        // Boa pratica: ao ITERAR manualmente uma synchronizedList, e necessario
        // sincronizar o bloco manualmente, pois o wrapper so sincroniza
        // operacoes individuais (get/add/remove), nao a iteracao completa.
        synchronized (listaSincronizada) {
            for (Produto p : listaSincronizada) {
                System.out.println("  -> " + p);
            }
        }
    }
}
