package br.com.ecommerce.app;

import br.com.ecommerce.dao.ProdutoConsultasDAO;
import br.com.ecommerce.modelo.Categoria;
import br.com.ecommerce.modelo.ProdutoJpa;
import br.com.ecommerce.util.JpaUtil;

import javax.persistence.EntityManager;

import java.math.BigDecimal;

/**
 * FASE 5 - Teste das consultas JPQL: NamedQueries, JPQL dinâmica,
 * navegação entre entidades e reajuste em massa.
 */
public class AppConsultasJpql {

    private static final String SIGLA_PERIFERICOS = "PERIF";

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            semearCatalogo(em);

            ProdutoConsultasDAO dao = new ProdutoConsultasDAO(em);

            System.out.println("\n>>> NamedQuery ProdutoJpa.listarTodosOrdenados");
            dao.listarTodosOrdenados().forEach(System.out::println);

            System.out.println("\n>>> NamedQuery ProdutoJpa.buscarPorFaixaPreco [R$ 30,00 - R$ 200,00]");
            dao.buscarPorFaixaPreco(new BigDecimal("30.00"), new BigDecimal("200.00"))
                    .forEach(System.out::println);

            System.out.println("\n>>> JPQL dinâmica com :teto (preço <= R$ 200,00)");
            dao.listarAteValor(new BigDecimal("200.00")).forEach(System.out::println);

            System.out.println("\n>>> Navegação p.categoria.nome LIKE '%perif%'");
            dao.buscarPorNomeCategoria("%perif%").forEach(System.out::println);

            System.out.println("\n>>> NamedQuery getSingleResult (código P-CQ-03)");
            System.out.println(dao.buscarPorCodigoUnico("P-CQ-03"));

            System.out.println("\n>>> Casos de exceção do getSingleResult:");
            dao.buscarPorCodigoUnico("P-CQ-99-NAO-EXISTE");

            System.out.println("\n>>> Reajuste em massa (+10% na categoria PERIF)");
            // Mutação em massa exige transação ativa (resource-local)
            em.getTransaction().begin();
            int alterados = dao.aplicarReajustePorCategoria(SIGLA_PERIFERICOS, new BigDecimal("1.10"));
            em.getTransaction().commit();
            System.out.println("Registros alterados: " + alterados);

            // Limpa o contexto p/ que os novos preços sejam reconsultados do banco
            em.clear();
            System.out.println("\n>>> Catálogo depois do reajuste (listarTodosOrdenados)");
            dao.listarTodosOrdenados().forEach(System.out::println);
        } finally {
            em.close();
            JpaUtil.fechar();
        }
    }

    private static void semearCatalogo(EntityManager em) {
        // Idempotência para execuções repetidas
        em.getTransaction().begin();
        em.createQuery("DELETE FROM ProdutoJpa p WHERE p.codigo LIKE 'P-CQ-%'").executeUpdate();
        em.createQuery("DELETE FROM Categoria c WHERE c.sigla = :s").setParameter("s", SIGLA_PERIFERICOS)
                .executeUpdate();
        em.getTransaction().commit();

        em.getTransaction().begin();
        Categoria perifericos = new Categoria("PERIFERICOS", SIGLA_PERIFERICOS);
        perifericos.adicionarProduto(new ProdutoJpa("P-CQ-01", "Teclado Mecânico",
                new BigDecimal("120.00"), 30));
        perifericos.adicionarProduto(new ProdutoJpa("P-CQ-02", "Mouse Sem Fio",
                new BigDecimal("40.00"), 50));
        perifericos.adicionarProduto(new ProdutoJpa("P-CQ-03", "Monitor 24pol",
                new BigDecimal("800.00"), 10));
        perifericos.adicionarProduto(new ProdutoJpa("P-CQ-04", "Webcam HD",
                new BigDecimal("150.00"), 15));
        em.persist(perifericos); // cascade persiste os 4 produtos
        em.getTransaction().commit();
        em.clear();
    }
}