package modulo10;

public class Produto implements Comparable<Produto> {

    private String codigo;
    private String nome;
    private double preco;

    public Produto(String codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public int compareTo(Produto outro) {
        // Ordenacao natural: alfabetica por nome.
        return this.nome.compareTo(outro.nome);
    }

    @Override
    public String toString() {
        return nome + " [" + codigo + "] - R$" + preco;
    }
}
