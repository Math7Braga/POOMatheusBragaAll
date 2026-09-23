package br.com.ecommerce.logistica;

import br.com.ecommerce.excecoes.LogisticaException;

/**
 * Superclasse ABSTRATA de itens expedidos pelo armazém central.
 *
 * Visibilidade estrita:
 *  - codigoEtiqueta: private (apenas a própria classe);
 *  - pesoKg: protected (subclasses e classes do mesmo pacote);
 *  - cubagemM3: package-default (sem modificador, apenas o mesmo pacote).
 *
 * A ausência de construtor vazio OBRIGA as subclasses a encadearem
 * chamadas via super(...) no construtor parametrizado.
 */
public abstract class ItemLogistico {

    private static int totalItensGeradosNaJvm;

    // Bloco estático: inicialização única do contador global da JVM
    static {
        totalItensGeradosNaJvm = 0;
    }

    private String codigoEtiqueta;
    protected double pesoKg;
    double cubagemM3;
    private boolean despachado;

    public ItemLogistico(String codigoEtiqueta, double pesoKg, double cubagemM3) throws LogisticaException {
        if (codigoEtiqueta == null || codigoEtiqueta.trim().length() < 5) {
            throw new LogisticaException(
                    "Etiqueta inválida: deve conter ao menos 5 caracteres (recebida: " + codigoEtiqueta + ")");
        }
        this.codigoEtiqueta = codigoEtiqueta;
        this.pesoKg = pesoKg;
        this.cubagemM3 = cubagemM3;
        this.despachado = false;
        totalItensGeradosNaJvm++;
    }

    public abstract double calcularFreteBase();

    // Padrão JavaBeans para a propriedade booleana 'despachado'
    public boolean isDespachado() {
        return despachado;
    }

    public void setDespachado(boolean despachado) {
        this.despachado = despachado;
    }

    public String getCodigoEtiqueta() {
        return codigoEtiqueta;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public double getCubagemM3() {
        return cubagemM3;
    }

    public static int getTotalItensGeradosNaJvm() {
        return totalItensGeradosNaJvm;
    }

    @Override
    public String toString() {
        return String.format("ItemLogistico[etiqueta=%s, pesoKg=%.2f, cubagemM3=%.2f, despachado=%s]",
                codigoEtiqueta, pesoKg, cubagemM3, despachado);
    }
}