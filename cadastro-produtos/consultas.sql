-- Conferência no pgAdmin (Passo 11 do roteiro)
SELECT id, nome, quantidade, valor_unitario
FROM produtos ORDER BY id;

SELECT SUM(quantidade * valor_unitario) AS total_estoque
FROM produtos;
