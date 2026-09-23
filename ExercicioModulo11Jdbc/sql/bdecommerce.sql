-- ================================================================
-- BASE DE DADOS DO SISTEMA DE E-COMMERCE (MÓDULO 11 - JDBC)
-- Execute no PostgreSQL (pgAdmin ou psql):
--   1) Crie o banco 2) Conecte-se a ele 3) Execute este script
-- ================================================================

CREATE DATABASE bdecommerce;

-- Conecte-se ao banco bdecommerce antes de executar as tabelas:

CREATE TABLE produto (
    codigo VARCHAR(10) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco NUMERIC(10, 2) NOT NULL,
    quantidade_estoque INTEGER DEFAULT 0
);

CREATE TABLE pedido (
    id_pedido VARCHAR(20) PRIMARY KEY,
    codigo_produto VARCHAR(10) NOT NULL,
    quantidade_comprada INTEGER NOT NULL,
    data_pedido TIMESTAMP NOT NULL,
    CONSTRAINT fk_pedido_produto FOREIGN KEY (codigo_produto) REFERENCES produto (codigo)
);

CREATE OR REPLACE PROCEDURE sp_calcular_saldo_estoque(
    IN p_codigo VARCHAR,
    OUT p_quantidade INT,
    OUT p_total_reais NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
    SELECT quantidade_estoque, (quantidade_estoque * preco)
    INTO p_quantidade, p_total_reais
    FROM produto
    WHERE codigo = p_codigo;
END;
$$;