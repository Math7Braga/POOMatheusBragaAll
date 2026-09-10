package modulo10;

import java.util.Objects;

public class CupomDesconto {

    private String codigo;
    private double porcentagem;

    public CupomDesconto(String codigo, double porcentagem) {
        this.codigo = codigo;
        this.porcentagem = porcentagem;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPorcentagem() {
        return porcentagem;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CupomDesconto outro = (CupomDesconto) obj;
        // Igualdade baseada exclusivamente no atributo "codigo", conforme pedido.
        return Objects.equals(codigo, outro.codigo);
    }

    @Override
    public int hashCode() {
        // O hashCode precisa ser consistente com equals() e usar o mesmo
        // atributo (codigo), senao o HashSet nao consegue detectar
        // corretamente as duplicidades.
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return codigo + " (" + porcentagem + "%)";
    }
}
