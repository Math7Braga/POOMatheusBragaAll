package br.com.ecommerce.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * FASE 3 - Contraparte do 1:1. O lado INVERSO usa mappedBy = "perfilFiscal"
 * (nome do atributo dono na classe Cliente). Não possui FK própria.
 */
@Entity
@Table(name = "PERFIL_FISCAL")
public class PerfilFiscal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CNPJ", length = 18)
    private String cnpj;

    @Column(name = "INSCRICAO_ESTADUAL", length = 20)
    private String inscricaoEstadual;

    @OneToOne(mappedBy = "perfilFiscal")
    private Cliente cliente;

    public PerfilFiscal() {
    }

    public PerfilFiscal(String cnpj, String inscricaoEstadual) {
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return String.format("PerfilFiscal{id=%d, cnpj='%s', inscricaoEstadual='%s'}",
                id, cnpj, inscricaoEstadual);
    }
}