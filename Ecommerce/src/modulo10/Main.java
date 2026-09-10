package modulo10;

import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {

        System.out.println("========== MISSAO 1: Contratos de Servico e instanceof ==========");
        Rastreavel pacote = new PacoteCorreios("BR123456789");
        Rastreavel carga = new CargaTransportadora("CT-9988");
        Rastreavel expressa = new EntregaExpressa("EXP-001");
        String naoRastreavel = "Documento sem rastreio";

        CentralRastreamento.inspecionarItem(pacote);
        CentralRastreamento.inspecionarItem(carga);
        CentralRastreamento.inspecionarItem(expressa);
        CentralRastreamento.inspecionarItem(naoRastreavel);

        System.out.println();
        System.out.println("========== MISSAO 2: Filas (List/LinkedList) e Vector ==========");
        GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos();
        gerenciadorPedidos.adicionarNoFim("Pedido #1001");
        gerenciadorPedidos.adicionarNoFim("Pedido #1002");
        gerenciadorPedidos.adicionarNoInicio("Pedido #1000 (prioritario)");
        gerenciadorPedidos.exibirFila();
        System.out.println("Removido da fila: " + gerenciadorPedidos.removerProximo());
        gerenciadorPedidos.exibirFila();

        RegistradorEventos registrador = new RegistradorEventos();
        registrador.registrarEvento("Login do operador realizado");
        registrador.registrarEvento("Pedido #1001 processado");
        registrador.exibirLogs();

        System.out.println();
        System.out.println("========== MISSAO 3: HashSet e Iterator ==========");
        GerenciadorCupons gerenciadorCupons = new GerenciadorCupons();
        gerenciadorCupons.adicionarCupom(new CupomDesconto("PROMO10", 10));
        gerenciadorCupons.adicionarCupom(new CupomDesconto("BLACKFRIDAY", 50));
        gerenciadorCupons.adicionarCupom(new CupomDesconto("PROMO10", 15)); // duplicado (mesmo codigo)
        gerenciadorCupons.adicionarCupom(new CupomDesconto("EXPIRADO", 0));
        gerenciadorCupons.exibirCupons();
        gerenciadorCupons.limparCuponsZerados();
        System.out.println("Apos limpeza de cupons com 0%:");
        gerenciadorCupons.exibirCupons();

        System.out.println();
        System.out.println("========== MISSAO 4: TreeSet, Comparable e Comparator ==========");
        Produto p1 = new Produto("P01", "Mouse Gamer", 150.0);
        Produto p2 = new Produto("P02", "Amplificador de Audio", 800.0);
        Produto p3 = new Produto("P03", "Zebra de Pelucia", 45.0);
        Produto p4 = new Produto("P04", "Cadeira Ergonomica", 1200.0);

        TreeSet<Produto> catalogoNatural = new TreeSet<>();
        catalogoNatural.add(p1);
        catalogoNatural.add(p2);
        catalogoNatural.add(p3);
        catalogoNatural.add(p4);
        System.out.println("Catalogo ordenado naturalmente (por nome - Comparable):");
        System.out.println(catalogoNatural);

        TreeSet<Produto> catalogoPreco = new TreeSet<>(new ComparadorPorPreco());
        catalogoPreco.add(p1);
        catalogoPreco.add(p2);
        catalogoPreco.add(p3);
        catalogoPreco.add(p4);
        System.out.println("Catalogo ordenado por preco (Comparator):");
        System.out.println(catalogoPreco);

        System.out.println();
        System.out.println("========== MISSAO 5: HashMap e acesso O(1) ==========");
        PDV pdv = new PDV();
        pdv.cadastrarProduto(p1);
        pdv.cadastrarProduto(p2);
        pdv.cadastrarProduto(p3);
        pdv.cadastrarProduto(p4);

        Produto encontrado = pdv.consultarPorCodigo("P02");
        System.out.println("Consulta direta pelo codigo P02: " + encontrado);

        pdv.exibirChaves();
        pdv.exibirValores();

        // Para testar a leitura via teclado (Scanner), descomente a linha abaixo:
        // pdv.consultarViaTeclado();
    }
}
