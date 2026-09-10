package modulo10.avancado;

import java.util.Arrays;
import java.util.List;

import modulo10.Produto;

public class MainAvancado {

    public static void main(String[] args) {

        System.out.println("========== REQUISITO 1: SortedSet - subSet, headSet, tailSet ==========");
        Produto p1 = new Produto("P01", "Mouse Gamer", 150.0);
        Produto p2 = new Produto("P02", "Amplificador de Audio", 800.0);
        Produto p3 = new Produto("P03", "Zebra de Pelucia", 45.0);
        Produto p4 = new Produto("P04", "Cadeira Ergonomica", 1200.0);
        Produto p5 = new Produto("P05", "Teclado Mecanico", 350.0);

        CatalogoSubconjuntos catalogo = new CatalogoSubconjuntos();
        catalogo.adicionarProduto(p1);
        catalogo.adicionarProduto(p2);
        catalogo.adicionarProduto(p3);
        catalogo.adicionarProduto(p4);
        catalogo.adicionarProduto(p5);
        catalogo.exibirCatalogoCompleto();

        // "Marcadores" usados apenas como limites de faixa. Como o Comparator
        // do catalogo compara SOMENTE pelo preco, so o preco desses objetos
        // importa para as consultas de subSet/headSet/tailSet.
        Produto pisoFaixa = new Produto("MARCADOR", "-", 100.0);
        Produto tetoFaixa = new Produto("MARCADOR", "-", 800.0);
        catalogo.consultarPorFaixaDePreco(pisoFaixa, tetoFaixa);

        Produto tetoConsulta = new Produto("MARCADOR", "-", 350.0);
        catalogo.consultarTeto(tetoConsulta);

        Produto pisoConsulta = new Produto("MARCADOR", "-", 350.0);
        catalogo.consultarPiso(pisoConsulta);

        System.out.println();
        System.out.println("========== REQUISITO 2: Queue - offer, peek, poll ==========");
        GerenciadorFilaQueue filaQueue = new GerenciadorFilaQueue();
        filaQueue.adicionarPedido("Pedido #2001");
        filaQueue.adicionarPedido("Pedido #2002");
        filaQueue.adicionarPedido("Pedido #2003");
        filaQueue.exibirFila();
        filaQueue.consultarProximo();
        filaQueue.processarProximo();
        filaQueue.exibirFila();

        System.out.println();
        System.out.println("========== REQUISITO 3: Collections - shuffle, reverse, min, synchronizedList ==========");
        List<Produto> produtos = Arrays.asList(p1, p2, p3, p4, p5);
        UtilitariosCollections utilitarios = new UtilitariosCollections();
        utilitarios.demonstrarUtilitarios(produtos);

        System.out.println();
        System.out.println("========== REQUISITO 4: Hashtable e Enumeration ==========");
        TabelaConfiguracoes tabela = new TabelaConfiguracoes();
        tabela.abrirSessao("SESS-001", "carlos.silva");
        tabela.abrirSessao("SESS-002", "ana.souza");
        tabela.abrirSessao("SESS-003", "joao.pereira");
        tabela.listarSessoesViaEnumeration();
        System.out.println("Total de sessoes ativas: " + tabela.totalSessoesAtivas());

        tabela.encerrarSessao("SESS-002");
        System.out.println("Apos encerrar a sessao SESS-002:");
        tabela.listarSessoesViaEnumeration();
    }
}
