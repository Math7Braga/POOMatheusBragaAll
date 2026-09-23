package br.com.ecommerce.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * FASE 3 - Relacionamento 1:1 BIDIRECIONAL Cliente <-> PerfilFiscal.
 * Lado dono: Cliente (possui a FK via @JoinColumn com CascadeType.ALL).
 */
@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", length = 100)
    private String nome;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PERFIL_FISCAL")
    private PerfilFiscal perfilFiscal;

    public Cliente() {
    }

    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PerfilFiscal getPerfilFiscal() {
        return perfilFiscal;
    }

    public void setPerfilFiscal(PerfilFiscal perfilFiscal) {
        this.perfilFiscal = perfilFiscal;
        // Mantém a contraparte da associação bidirecional
        if (perfilFiscal != null) {
            perfilFiscal.setCliente(this);
        }
    }

    @Override
    public String toString() {
        return String.format("Cliente{id=%d, nome='%s', email='%s', cnpj=%s}",
                id, nome, email, (perfilFiscal == null ? null : perfilFiscal.getCnpj()));
    }
}