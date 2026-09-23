package br.com.ecommerce.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * FASE 3 - Pedido com relacionamento N:N com ProdutoJpa via tabela associativa
 * ITEM_PEDIDO_ASSOCIACAO (joins: ID_PEDIDO e inverseJoinColumns: ID_PRODUTO).
 */
@Entity
@Table(name = "PEDIDO_JPA")
public class PedidoJpa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATA_PEDIDO")
    private LocalDateTime dataPedido;

    @ManyToMany
    @JoinTable(name = "ITEM_PEDIDO_ASSOCIACAO",
            joinColumns = @JoinColumn(name = "ID_PEDIDO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUTO"))
    private List<ProdutoJpa> produtos = new ArrayList<>();

    public PedidoJpa() {
    }

    public PedidoJpa(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public void adicionarProduto(ProdutoJpa produto) {
        this.produtos.add(produto);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<ProdutoJpa> getProdutos() {
        return produtos;
    }

    @Override
    public String toString() {
        return String.format("PedidoJpa{id=%d, data=%s, itens=%d}",
                id, dataPedido, (produtos == null ? 0 : produtos.size()));
    }
}