package br.com.ecommerce.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Utilitário central que cria a EntityManagerFactory da unidade de
 * persistência "PuEcommerceJpa" definida em META-INF/persistence.xml.
 */
public final class JpaUtil {

    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("PuEcommerceJpa");

    private JpaUtil() {
    }

    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

    public static void fechar() {
        if (EMF.isOpen()) {
            EMF.close();
        }
    }
}