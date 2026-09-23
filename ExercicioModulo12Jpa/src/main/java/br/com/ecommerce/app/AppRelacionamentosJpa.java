package br.com.ecommerce.app;

import br.com.ecommerce.modelo.Categoria;
import br.com.ecommerce.modelo.Cliente;
import br.com.ecommerce.modelo.PedidoJpa;
import br.com.ecommerce.modelo.PerfilFiscal;
import br.com.ecommerce.modelo.ProdutoJpa;
import br.com.ecommerce.util.JpaUtil;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * FASE 3 - Mapeamento de relacionamentos e cascatas:
 * 1:1 Cliente-PerfilFiscal (CascadeType.ALL), 1:N Categoria-ProdutoJpa
 * (@OrderBy e @MapKey), N:N PedidoJpa-ProdutoJpa (@JoinTable).
 */
public class AppRelacionamentosJpa {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            limparDados(em);
            Long idCategoria;
            Long idCliente;
            Long idPedido;

            // ---------- 1:N Categoria -> Produtos (cascade ALL + @OrderBy + @MapKey) ----------
            em.getTransaction().begin();
            Categoria categoria = new Categoria("ELETRONICOS", "ELET");
            categoria.adicionarProduto(new ProdutoJpa("ELET-02", "Console Gamer",
                    new BigDecimal("3999.00"), 5));
            categoria.adicionarProduto(new ProdutoJpa("ELET-01", "Monitor 27pol",
                    new BigDecimal("1299.90"), 8));
            em.persist(categoria); // cascade propaga o persist para os produtos
            em.getTransaction().commit();
            idCategoria = categoria.getId();
            System.out.println(">>> Categoria persistida via CASCADE ALL (id=" + idCategoria + ")");

            // ---------- 1:1 Cliente -> PerfilFiscal (CascadeType.ALL) ----------
            em.getTransaction().begin();
            Cliente cliente = new Cliente("Maria Silva", "maria@email.com");
            PerfilFiscal perfil = new PerfilFiscal("12.345.678/0001-90", "123456789");
            cliente.setPerfilFiscal(perfil); // setter mantém o vínculo bidirecional
            em.persist(cliente); // cascade persiste o PerfilFiscal
            em.getTransaction().commit();
            idCliente = cliente.getId();
            System.out.println(">>> Cliente + PerfilFiscal persistidos em cascata (cliente id=" + idCliente + ")");

            // ---------- N:N PedidoJpa -> ProdutoJpa (@JoinTable) ----------
            ProdutoJpa monitor = em.createQuery(
                    "SELECT p FROM ProdutoJpa p WHERE p.codigo = :c", ProdutoJpa.class)
                    .setParameter("c", "ELET-01").getSingleResult();
            ProdutoJpa console = em.createQuery(
                    "SELECT p FROM ProdutoJpa p WHERE p.codigo = :c", ProdutoJpa.class)
                    .setParameter("c", "ELET-02").getSingleResult();

            em.getTransaction().begin();
            PedidoJpa pedido = new PedidoJpa(LocalDateTime.now());
            pedido.adicionarProduto(monitor);
            pedido.adicionarProduto(console);
            em.persist(pedido);
            em.getTransaction().commit();
            idPedido = pedido.getId();
            System.out.println(">>> Pedido com 2 itens persistido (id=" + idPedido + ")");

            // ---------- Comprovações ----------
            em.clear();

            // 1:1 bidirecional
            Cliente carregado = em.find(Cliente.class, idCliente);
            System.out.println("\n--- Comprovação 1:1 ---");
            System.out.println(carregado);
            System.out.println("Back-reference PerfilFiscal.cliente = "
                    + carregado.getPerfilFiscal().getCliente().getNome());

            // 1:N ordenado e indexado
            Categoria cat = em.find(Categoria.class, idCategoria);
            System.out.println("\n--- Comprovação 1:N / @OrderBy (nome ASC) ---");
            cat.getProdutos().forEach(System.out::println);
            System.out.println("\n--- @MapKey (recuperação direta pela chave 'ELET-02') ---");
            System.out.println(cat.getProdutosPorCodigo().get("ELET-02"));

            // N:N via tabela associativa
            PedidoJpa pj = em.find(PedidoJpa.class, idPedido);
            System.out.println("\n--- Comprovação N:N (tabela ITEM_PEDIDO_ASSOCIACAO) ---");
            System.out.println(pj);
            pj.getProdutos().forEach(System.out::println);
        } finally {
            em.close();
            JpaUtil.fechar();
        }
    }

    private static void limparDados(EntityManager em) {
        em.getTransaction().begin();
        em.createQuery("SELECT p FROM PedidoJpa p", PedidoJpa.class).getResultList().forEach(em::remove);
        em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList().forEach(em::remove);
        em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList().forEach(em::remove);
        em.getTransaction().commit();
    }
}