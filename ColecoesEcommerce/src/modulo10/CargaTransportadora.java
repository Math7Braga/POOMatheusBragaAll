package modulo10;

public class CargaTransportadora implements Rastreavel {

    private String codigoCarga;

    public CargaTransportadora(String codigoCarga) {
        this.codigoCarga = codigoCarga;
    }

    @Override
    public String getStatusRastreio() {
        return "Carga da Transportadora [" + codigoCarga + "]: saiu do centro de distribuicao.";
    }
}
