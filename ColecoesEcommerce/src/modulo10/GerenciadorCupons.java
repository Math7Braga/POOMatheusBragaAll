package modulo10;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GerenciadorCupons {

    private Set<CupomDesconto> conjuntoCupons = new HashSet<>();

    public void adicionarCupom(CupomDesconto cupom) {
        // add() retorna false quando ja existe um elemento "igual" (equals())
        // no conjunto, o que aqui significa mesmo codigo de cupom.
        boolean adicionado = conjuntoCupons.add(cupom);
        if (adicionado) {
            System.out.println("Cupom " + cupom.getCodigo() + " adicionado com sucesso.");
        } else {
            System.out.println("Cupom " + cupom.getCodigo() + " ja existe (duplicado) - nao adicionado.");
        }
    }

    public void limparCuponsZerados() {
        // Usamos o Iterator explicitamente porque remover elementos de uma
        // colecao durante um for-each tradicional lancaria
        // ConcurrentModificationException. O Iterator permite remocao segura
        // durante a propria iteracao, atraves do metodo it.remove().
        Iterator<CupomDesconto> it = conjuntoCupons.iterator();
        while (it.hasNext()) {
            CupomDesconto cupom = it.next();
            if (cupom.getPorcentagem() == 0.0) {
                it.remove();
            }
        }
    }

    public void exibirCupons() {
        System.out.println("Cupons ativos: " + conjuntoCupons);
    }
}
