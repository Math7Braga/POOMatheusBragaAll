package br.com.ecommerce.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * FASE 4 - Superclasse abstrata do transporte (estratégia JOINED):
 * uma tabela FRETE com os atributos comuns + uma tabela para cada subclasse,
 * unidas por @PrimaryKeyJoinColumn. Consultas à superclasse são polimórficas,
 * e o Hibernate/banco monta o JOIN conforme o tipo concreto.
 */
@Entity
@Table(name = "FRETE")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Frete implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODIGO_RASTREIO", length = 20)
    private String codigoRastreio;

    @Column(name = "VALOR_BASE", precision = 10, scale = 2)
    private BigDecimal valorBase;

    public Frete() {
    }

    public Frete(String codigoRastreio, BigDecimal valorBase) {
        this.codigoRastreio = codigoRastreio;
        this.valorBase = valorBase;
    }

    public abstract BigDecimal calcularCustoTotal();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    public BigDecimal getValorBase() {
        return valorBase;
    }

    public void setValorBase(BigDecimal valorBase) {
        this.valorBase = valorBase;
    }
}