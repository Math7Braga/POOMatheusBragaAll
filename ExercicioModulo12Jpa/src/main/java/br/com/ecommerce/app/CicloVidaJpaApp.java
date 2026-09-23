package br.com.ecommerce.app;

import br.com.ecommerce.modelo.ProdutoJpa;
import br.com.ecommerce.util.JpaUtil;

import javax.persistence.EntityManager;

import java.math.BigDecimal;

/**
 * FASE 2 - Ciclo de vida de entidades gerenciadas pelo EntityManager:
 * New/Transient -> Managed (persist + flush) -> Dirty Checking (commit)
 * -> Detached (detach) -> merge -> refresh -> Removed (remove).
 */
public class CicloVidaJpaApp {

    private static final String CODIGO_REFERENCIA = "CICLO-1";

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            limparRegistrosAnteriores(em);

            // 1) ESTADO NEW / TRANSIENT - objeto recém-instanciado, sem id
            ProdutoJpa produto = new ProdutoJpa(CODIGO_REFERENCIA, "Headset Gamer",
                    new BigDecimal("149.90"), 20);
            System.out.println(">>> Novo/Transient: id = " + produto.getId()
                    + " (ainda não existe no banco)");

            // 2) TRANSICAO PARA MANAGED: persist + flush gera a chave primária
            em.getTransaction().begin();
            em.persist(produto);
            em.flush();
            System.out.println(">>> Managed: id atribuído pelo banco = " + produto.getId()
                    + " (INSERT executado via flush)");

            // 3) DIRTY CHECKING: alteração em memória é sincronizada no commit
            produto.setPreco(new BigDecimal("157.90"));
            em.getTransaction().commit();
            System.out.println(">>> Dirty checking: preço alterado para R$ "
                    + produto.getPreco() + " e persistido no commit");

            // 4) ESTADO DETACHED: fora do contexto, mudanças não vão ao banco
            em.detach(produto);
            produto.setQuantidadeEstoque(99999);
            System.out.println(">>> Detached: estoque alterado em memória p/ "
                    + produto.getQuantidadeEstoque() + " (NÃO será gravado ainda)");

            // 5) RELIGANDO COM MERGE: o estado atual (99999) é sincronizado
            ProdutoJpa mesclado = em.merge(produto);
            System.out.println(">>> merge: religou o objeto. Estoque efetivado = "
                    + mesclado.getQuantidadeEstoque());

            // 6) REFRESH: sobrepõe memória com o estado vigente do banco
            ProdutoJpa espelho = em.find(ProdutoJpa.class, produto.getId());
            espelho.setNome("NOME VOLÁTIL QUE NÃO DEVERIA GRAVAR");
            em.refresh(espelho);
            System.out.println(">>> refresh: nome real no banco = " + espelho.getNome());

            // 7) ESTADO REMOVED: find + remove dentro de transação
            em.getTransaction().begin();
            ProdutoJpa paraRemover = em.find(ProdutoJpa.class, produto.getId());
            em.remove(paraRemover);
            em.getTransaction().commit();
            System.out.println(">>> Removed: registro excluído. Existe agora? "
                    + (em.find(ProdutoJpa.class, produto.getId()) != null));
        } finally {
            em.close();
            JpaUtil.fechar();
        }
    }

    private static void limparRegistrosAnteriores(EntityManager em) {
        // Idempotência: garante que executar o app mais de uma vez não quebre
        // no constraint único do codigo.
        em.getTransaction().begin();
        em.createQuery("DELETE FROM ProdutoJpa p WHERE p.codigo = :c")
                .setParameter("c", CODIGO_REFERENCIA).executeUpdate();
        em.getTransaction().commit();
    }
}