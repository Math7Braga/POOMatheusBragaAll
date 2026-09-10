package modulo10;

import java.util.Comparator;

public class ComparadorPorPreco implements Comparator<Produto> {

    @Override
    public int compare(Produto p1, Produto p2) {
        // Regra de retorno do metodo compare():
        // - NEGATIVO -> p1 deve vir ANTES de p2 na ordenacao;
        // - ZERO -> p1 e p2 sao considerados equivalentes para fins de ordem;
        // - POSITIVO -> p1 deve vir DEPOIS de p2 na ordenacao.
        return Double.compare(p1.getPreco(), p2.getPreco());
    }
}
