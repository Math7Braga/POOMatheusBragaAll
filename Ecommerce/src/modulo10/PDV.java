package modulo10;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PDV {

    // HashMap oferece acesso O(1) em media para get()/put(), pois usa uma
    // funcao de hash sobre a chave para localizar diretamente o "bucket"
    // onde o valor esta armazenado, sem precisar percorrer a colecao inteira.
    private Map<String, Produto> mapaEstoque = new HashMap<>();

    public void cadastrarProduto(Produto produto) {
        mapaEstoque.put(produto.getCodigo(), produto);
    }

    public Produto consultarPorCodigo(String codigo) {
        // Acesso direto via get(chave), sem necessidade de lacos de repeticao.
        return mapaEstoque.get(codigo);
    }

    public void consultarViaTeclado() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o codigo de barras do produto: ");
        String codigo = scanner.nextLine();
        Produto produto = consultarPorCodigo(codigo);
        if (produto != null) {
            System.out.println("Produto encontrado: " + produto);
        } else {
            System.out.println("Produto nao encontrado para o codigo: " + codigo);
        }
    }

    public void exibirChaves() {
        Set<String> chaves = mapaEstoque.keySet();
        System.out.println("Codigos cadastrados: " + chaves);
    }

    public void exibirValores() {
        Collection<Produto> produtos = mapaEstoque.values();
        System.out.println("Produtos cadastrados: " + produtos);
    }
}
