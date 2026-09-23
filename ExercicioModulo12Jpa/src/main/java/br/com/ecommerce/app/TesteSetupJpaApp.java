package br.com.ecommerce.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FASE 1 - Teste de inicialização do JPA/Hibernate.
 * Instancia a EntityManagerFactory a partir da unidade "PuEcommerceJpa"
 * comprovando que o provider Hibernate se conecta sem falhas.
 */
public class TesteSetupJpaApp {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("PuEcommerceJpa");
            em = emf.createEntityManager();

            System.out.println(">>> Hibernate inicializado com sucesso!");
            System.out.println(">>> Unidade de persistência: PuEcommerceJpa");
            System.out.println(">>> Dialeto configurado: "
                    + emf.getProperties().get("hibernate.dialect"));
            System.out.println(">>> hbm2ddl.auto: " + emf.getProperties().get("hibernate.hbm2ddl.auto"));
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}