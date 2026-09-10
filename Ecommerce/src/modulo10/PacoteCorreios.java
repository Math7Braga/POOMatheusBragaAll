package modulo10;

public class PacoteCorreios implements Rastreavel {

    private String codigoRastreio;

    public PacoteCorreios(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    @Override
    public String getStatusRastreio() {
        return "Pacote dos Correios [" + codigoRastreio + "]: em transito na agencia local.";
    }
}
