package br.com.ecommerce.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import java.math.BigDecimal;

/**
 * FASE 4 - Subclasse de Frete (modalidade AÉREA).
 * @PrimaryKeyJoinColumn(name = "ID_FRETE") liga a FK da tabela FRETE_AEREO
 * à PK da tabela FRETE.
 */
@Entity
@Table(name = "FRETE_AEREO")
@PrimaryKeyJoinColumn(name = "ID_FRETE")
public class FreteAereo extends Frete {

    private static final long serialVersionUID = 1L;

    @Column(name = "PREFIXO_VOO", length = 10)
    private String prefixoVoo;

    @Column(name = "TAXA_AEROPORTUARIA", precision = 10, scale = 2)
    private BigDecimal taxaAeroportuaria;

    public FreteAereo() {
    }

    public FreteAereo(String codigoRastreio, BigDecimal valorBase,
                      String prefixoVoo, BigDecimal taxaAeroportuaria) {
        super(codigoRastreio, valorBase);
        this.prefixoVoo = prefixoVoo;
        this.taxaAeroportuaria = taxaAeroportuaria;
    }

    @Override
    public BigDecimal calcularCustoTotal() {
        // Polimorfismo OO: custo específico do transporte aéreo
        return getValorBase().add(taxaAeroportuaria);
    }

    public String getPrefixoVoo() {
        return prefixoVoo;
    }

    public void setPrefixoVoo(String prefixoVoo) {
        this.prefixoVoo = prefixoVoo;
    }

    public BigDecimal getTaxaAeroportuaria() {
        return taxaAeroportuaria;
    }

    public void setTaxaAeroportuaria(BigDecimal taxaAeroportuaria) {
        this.taxaAeroportuaria = taxaAeroportuaria;
    }

    @Override
    public String toString() {
        return String.format("FreteAereo{id=%d, rastreio='%s', voo='%s', custoTotal=R$ %.2f}",
                getId(), getCodigoRastreio(), prefixoVoo, calcularCustoTotal());
    }
}