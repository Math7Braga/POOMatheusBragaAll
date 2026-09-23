package br.com.ecommerce.app;

import br.com.ecommerce.modelo.Frete;
import br.com.ecommerce.modelo.FreteAereo;
import br.com.ecommerce.modelo.FreteRodoviario;
import br.com.ecommerce.util.JpaUtil;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

/**
 * FASE 4 - Herança JOINED com consulta polimórfica pela superclasse:
 * "SELECT f FROM Frete f", inspeção com instanceof e cálculo polimórfico
 * do custo total de cada modalidade.
 */
public class AppHerancaJpa {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            limparDados(em);

            // Persistindo duas modalidades concretas
            em.getTransaction().begin();
            FreteAereo aereo = new FreteAereo("FRE-A-001", new BigDecimal("300.00"),
                    "GOL-1034", new BigDecimal("75.50"));
            FreteRodoviario rodoviario = new FreteRodoviario("FRE-R-001", new BigDecimal("180.00"),
                    "ABC-1D23", new BigDecimal("22.90"));
            em.persist(aereo);
            em.persist(rodoviario);
            em.getTransaction().commit();
            System.out.println(">>> FreteAereo id=" + aereo.getId()
                    + " | FreteRodoviario id=" + rodoviario.getId());

            // Consulta POLIMÓRFICA: todas as instâncias representadas pela superclasse
            List<Frete> fretes = em.createQuery("SELECT f FROM Frete f", Frete.class).getResultList();

            System.out.println("\n--- Consulta polimórfica 'SELECT f FROM Frete f' ---");
            for (Frete frete : fretes) {
                if (frete instanceof FreteAereo) {
                    System.out.println(">> [AÉREO] " + frete);
                } else if (frete instanceof FreteRodoviario) {
                    System.out.println(">> [RODOVIÁRIO] " + frete);
                }
                System.out.println("   Cálculo polimórfico calcularCustoTotal() = R$ "
                        + frete.calcularCustoTotal());
            }
        } finally {
            em.close();
            JpaUtil.fechar();
        }
    }

    private static void limparDados(EntityManager em) {
        em.getTransaction().begin();
        em.createQuery("SELECT f FROM Frete f", Frete.class).getResultList().forEach(em::remove);
        em.getTransaction().commit();
    }
}