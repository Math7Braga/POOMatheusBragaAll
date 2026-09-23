package br.com.ecommerce.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * FASE 2 - Entidade JavaBeans de produto (POJO, construtor padrão vazio,
 * getters/setters e Serializable). Ordem natural do catálogo via nome.
 *
 * FASE 5 - Declara as @NamedQueries pré-compiladas do contexto.
 */
@Entity
@Table(name = "PRODUTO_JPA")
@NamedQueries({
        @NamedQuery(name = "ProdutoJpa.listarTodosOrdenados",
                query = "SELECT p FROM ProdutoJpa p ORDER BY p.nome ASC"),
        @NamedQuery(name = "ProdutoJpa.buscarPorFaixaPreco",
                query = "SELECT p FROM ProdutoJpa p WHERE p.preco BETWEEN :precoMinimo AND :precoMaximo"),
        @NamedQuery(name = "ProdutoJpa.buscarPorCodigoUnico",
                query = "SELECT p FROM ProdutoJpa p WHERE p.codigo = :codigo")
})
public class ProdutoJpa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODIGO", unique = true, length = 10)
    private String codigo;

    @Column(name = "NOME", length = 100)
    private String nome;

    @Column(name = "PRECO", precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "QUANTIDADE_ESTOQUE")
    private Integer quantidadeEstoque;

    // FASE 3 - contraparte unária: muitos ProdutoJpa pertencem a uma Categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORIA")
    private Categoria categoria;

    public ProdutoJpa() {
    }

    public ProdutoJpa(String codigo, String nome, BigDecimal preco, Integer quantidadeEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("ProdutoJpa{id=%d, codigo='%s', nome='%s', preco=R$ %.2f, estoque=%d}",
                id, codigo, nome, preco, quantidadeEstoque);
    }
}