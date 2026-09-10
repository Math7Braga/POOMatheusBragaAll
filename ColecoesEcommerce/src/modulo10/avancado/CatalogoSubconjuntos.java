package modulo10.avancado;

import java.util.SortedSet;
import java.util.TreeSet;

import modulo10.ComparadorPorPreco;
import modulo10.Produto;

public class CatalogoSubconjuntos {

    // TreeSet ordenado por preco (Comparator), o que permite consultas de
    // faixa em O(log n), pois a estrutura interna e uma arvore binaria
    // balanceada (Red-Black Tree).
    private TreeSet<Produto> catalogoPorPreco = new TreeSet<>(new ComparadorPorPreco());

    public void adicionarProduto(Produto produto) {
        catalogoPorPreco.add(produto);
    }

    public void exibirCatalogoCompleto() {
        System.out.println("Catalogo completo (ordenado por preco): " + catalogoPorPreco);
    }

    public void consultarPorFaixaDePreco(Produto piso, Produto teto) {
        // subSet(from, to) retorna uma VIEW (visao "viva") do conjunto original,
        // contendo apenas os elementos entre "piso" (inclusive) e "teto"
        // (exclusive), respeitando a ordenacao definida pelo Comparator.
        SortedSet<Produto> faixa = catalogoPorPreco.subSet(piso, teto);
        System.out.println("Produtos na faixa de preco [" + piso.getPreco() + " ate " + teto.getPreco()
                + "): " + faixa);
    }

    public void consultarTeto(Produto tetoExclusivo) {
        // headSet(toElement) retorna todos os elementos ESTRITAMENTE MENORES
        // que o elemento informado. Util para perguntas do tipo "tudo abaixo de X".
        SortedSet<Produto> abaixoDoTeto = catalogoPorPreco.headSet(tetoExclusivo);
        System.out.println("Produtos abaixo de " + tetoExclusivo.getPreco() + " (headSet): " + abaixoDoTeto);
    }

    public void consultarPiso(Produto pisoInclusivo) {
        // tailSet(fromElement) retorna todos os elementos MAIORES OU IGUAIS
        // ao elemento informado. Util para perguntas do tipo "tudo a partir de X".
        SortedSet<Produto> apartirDoPiso = catalogoPorPreco.tailSet(pisoInclusivo);
        System.out.println("Produtos a partir de " + pisoInclusivo.getPreco() + " (tailSet): " + apartirDoPiso);
    }
}
