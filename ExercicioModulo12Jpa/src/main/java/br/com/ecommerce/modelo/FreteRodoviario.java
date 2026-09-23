package br.com.ecommerce.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import java.math.BigDecimal;

/**
 * FASE 4 - Subclasse de Frete (modalidade RODOVIÁRIA).
 */
@Entity
@Table(name = "FRETE_RODOVIARIO")
@PrimaryKeyJoinColumn(name = "ID_FRETE")
public class FreteRodoviario extends Frete {

    private static final long serialVersionUID = 1L;

    @Column(name = "PLACA_VEICULO", length = 10)
    private String placaVeiculo;

    @Column(name = "VALOR_PEDAGIOS", precision = 10, scale = 2)
    private BigDecimal valorPedagios;

    public FreteRodoviario() {
    }

    public FreteRodoviario(String codigoRastreio, BigDecimal valorBase,
                           String placaVeiculo, BigDecimal valorPedagios) {
        super(codigoRastreio, valorBase);
        this.placaVeiculo = placaVeiculo;
        this.valorPedagios = valorPedagios;
    }

    @Override
    public BigDecimal calcularCustoTotal() {
        // Polimorfismo OO: custo específico do transporte rodoviário
        return getValorBase().add(valorPedagios);
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public BigDecimal getValorPedagios() {
        return valorPedagios;
    }

    public void setValorPedagios(BigDecimal valorPedagios) {
        this.valorPedagios = valorPedagios;
    }

    @Override
    public String toString() {
        return String.format("FreteRodoviario{id=%d, rastreio='%s', placa='%s', custoTotal=R$ %.2f}",
                getId(), getCodigoRastreio(), placaVeiculo, calcularCustoTotal());
    }
}