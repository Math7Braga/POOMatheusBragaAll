package br.com.ecommerce.logistica;

import java.util.Date;

import br.com.ecommerce.excecoes.EstoqueInvalidoException;
import br.com.ecommerce.excecoes.LogisticaException;

/**
 * Subclasse concreta de ItemLogistico.
 *
 * Colisão de pacotes de import: o atributo criadoEm usa {@link Date}
 * (java.util.Date importado), enquanto criadoEmBancoDeDados usa o nome
 * QUALIFICADO COMPLETO java.sql.Date, contornando o conflito de nomes.
 */
public class PacoteExpresso extends ItemLogistico {

    private boolean entregaNoMesmoDia;

    // java.util.Date via import
    private Date criadoEm;

    // java.sql.Date pelo nome qualificado completo (colisão de imports contornada)
    private java.sql.Date criadoEmBancoDeDados;

    public PacoteExpresso(String codigoEtiqueta, double pesoKg, double cubagemM3, boolean entregaNoMesmoDia)
            throws LogisticaException {
        super(codigoEtiqueta, pesoKg, cubagemM3);
        if (pesoKg <= 0) {
            throw new EstoqueInvalidoException(
                    "Peso inválido para o pacote " + codigoEtiqueta + ": " + pesoKg + " kg (deve ser > 0).");
        }
        this.entregaNoMesmoDia = entregaNoMesmoDia;
        this.criadoEm = new Date();
        this.criadoEmBancoDeDados = new java.sql.Date(System.currentTimeMillis());
    }

    // Construtor sobrecarregado reutilizando o principal via this(...)
    public PacoteExpresso(String codigoEtiqueta, double pesoKg) throws LogisticaException {
        this(codigoEtiqueta, pesoKg, 0.05, false);
    }

    @Override
    public double calcularFreteBase() {
        return pesoKg * 5.0 + cubagemM3 * 20.0;
    }

    public boolean isEntregaNoMesmoDia() {
        return entregaNoMesmoDia;
    }

    @Override
    public String toString() {
        return super.toString() + " -> PacoteExpresso[entregaNoMesmoDia=" + entregaNoMesmoDia + "]";
    }
}