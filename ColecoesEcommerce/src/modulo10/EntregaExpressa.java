package modulo10;

public class EntregaExpressa implements Rastreavel {

    private String codigoEntrega;

    public EntregaExpressa(String codigoEntrega) {
        this.codigoEntrega = codigoEntrega;
    }

    @Override
    public String getStatusRastreio() {
        return "Entrega Expressa [" + codigoEntrega + "]: a caminho do destinatario, previsao de hoje.";
    }
}
