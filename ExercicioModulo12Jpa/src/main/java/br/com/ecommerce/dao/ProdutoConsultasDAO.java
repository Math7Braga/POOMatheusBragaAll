package br.com.ecommerce.dao;

import br.com.ecommerce.modelo.ProdutoJpa;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.List;

/**
 * FASE 5 - Consultas orientadas a objetos (JPQL): NamedQueries, TypedQuery,
 * parâmetros nomeados, navegação de relacionamentos e mutação em massa.
 */
public class ProdutoConsultasDAO {

    private final EntityManager em;

    public ProdutoConsultasDAO(EntityManager em) {
        this.em = em;
    }

    /** @NamedQuery "ProdutoJpa.listarTodosOrdenados" */
    public List<ProdutoJpa> listarTodosOrdenados() {
        return em.createNamedQuery("ProdutoJpa.listarTodosOrdenados", ProdutoJpa.class)
                .getResultList();
    }

    /** @NamedQuery "ProdutoJpa.buscarPorFaixaPreco" com BETWEEN */
    public List<ProdutoJpa> buscarPorFaixaPreco(BigDecimal precoMinimo, BigDecimal precoMaximo) {
        return em.createNamedQuery("ProdutoJpa.buscarPorFaixaPreco", ProdutoJpa.class)
                .setParameter("precoMinimo", precoMinimo)
                .setParameter("precoMaximo", precoMaximo)
                .getResultList();
    }

    /**
     * JPQL DINÂMICA com TypedQuery<ProdutoJpa> e parâmetro nomeado :teto.
     */
    public List<ProdutoJpa> listarAteValor(BigDecimal teto) {
        TypedQuery<ProdutoJpa> query = em.createQuery(
                "SELECT p FROM ProdutoJpa p WHERE p.preco <= :teto", ProdutoJpa.class);
        return query.setParameter("teto", teto).getResultList();
    }

    /**
     * Consulta por NAVEGAÇÃO de relacionamentos: p.categoria.nome.
     * Uso esperado: "'%perif%'".
     */
    public List<ProdutoJpa> buscarPorNomeCategoria(String trechoNomeCategoria) {
        return em.createQuery(
                "SELECT p FROM ProdutoJpa p WHERE LOWER(p.categoria.nome) LIKE :nomeCat",
                ProdutoJpa.class)
                .setParameter("nomeCat", trechoNomeCategoria)
                .getResultList();
    }

    /**
     * @NamedQuery "ProdutoJpa.buscarPorCodigoUnico" com getSingleResult,
     * tratando NoResultException e NonUniqueResultException.
     */
    public ProdutoJpa buscarPorCodigoUnico(String codigo) {
        try {
            return em.createNamedQuery("ProdutoJpa.buscarPorCodigoUnico", ProdutoJpa.class)
                    .setParameter("codigo", codigo)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println(">> NoResultException: nenhum produto com código " + codigo);
            return null;
        } catch (NonUniqueResultException e) {
            System.out.println(">> NonUniqueResultException: código " + codigo + " duplicado no banco");
            return null;
        }
    }

    /**
     * Mutação em MASSA: UPDATE em JPQL por categoria, retornando a quantidade
     * de registros alterados (query.executeUpdate()).
     *
     * Usa uma SUBCONSULTA no WHERE (em vez de join implícito p.categoria.sigla)
     * porque Hibernate 5.6 converte o join implícito em "UPDATE ... CROSS JOIN
     * ...", sintaxe inválida em PostgreSQL/H2 modernos. A subconsulta gera
     * "WHERE fk IN (SELECT id ...)" e funciona em qualquer dialeto.
     */
    public int aplicarReajustePorCategoria(String siglaCategoria, BigDecimal multiplicador) {
        return em.createQuery(
                "UPDATE ProdutoJpa p SET p.preco = p.preco * :multiplicador "
                        + "WHERE p.categoria IN "
                        + "(SELECT c FROM Categoria c WHERE c.sigla = :sigla)")
                .setParameter("multiplicador", multiplicador)
                .setParameter("sigla", siglaCategoria)
                .executeUpdate();
    }
}