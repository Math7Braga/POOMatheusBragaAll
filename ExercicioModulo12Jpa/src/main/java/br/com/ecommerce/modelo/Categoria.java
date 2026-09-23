package br.com.ecommerce.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FASE 3 - Categoria com dois pontos de vista sobre o MESMO relacionamento
 * 1:N com ProdutoJpa (ambos mappedBy = "categoria"):
 *  - List<ProdutoJpa> auto-ordenada por nome (@OrderBy);
 *  - Map<String, ProdutoJpa> indexado pelo codigo do produto (@MapKey).
 */
@Entity
@Table(name = "CATEGORIA")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", length = 100, unique = true)
    private String nome;

    @Column(name = "SIGLA", length = 10, unique = true)
    private String sigla;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @OrderBy("nome ASC")
    private List<ProdutoJpa> produtos = new ArrayList<>();

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @MapKey(name = "codigo")
    private Map<String, ProdutoJpa> produtosPorCodigo = new HashMap<>();

    public Categoria() {
    }

    public Categoria(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public void adicionarProduto(ProdutoJpa produto) {
        produto.setCategoria(this);
        this.produtos.add(produto);
        this.produtosPorCodigo.put(produto.getCodigo(), produto);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<ProdutoJpa> getProdutos() {
        return produtos;
    }

    public Map<String, ProdutoJpa> getProdutosPorCodigo() {
        return produtosPorCodigo;
    }

    @Override
    public String toString() {
        return String.format("Categoria{id=%d, nome='%s', sigla='%s', produtos=%d}",
                id, nome, sigla, (produtos == null ? 0 : produtos.size()));
    }
}