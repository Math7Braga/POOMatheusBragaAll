# Cadastro de Produtos — MVC + JPA + Hibernate + PostgreSQL

Aplicação Java de console para cadastrar, listar, consultar por ID, alterar e excluir produtos
(id, nome, quantidade e valor unitário). Arquitetura MVC; o DAO integra a camada de persistência.

**Stack:** Java 21 · Maven · Hibernate 7.4 (Jakarta Persistence 3.2) · PostgreSQL

## Estrutura

```
src/main
├── java/br/edu/exemplo
│   ├── app/Main.java
│   ├── controller/ProdutoController.java
│   ├── dao/ProdutoDAO.java
│   ├── model/Produto.java
│   ├── util/JPAUtil.java
│   └── view/ProdutoView.java
└── resources/META-INF/persistence.xml
```

## Regras de negócio

- ID gerado automaticamente; nome obrigatório (até 120 caracteres)
- Quantidade inteira ≥ 0; valor unitário positivo com até 2 casas decimais (`BigDecimal`)
- Não altera nem exclui produtos inexistentes; exclusão pede confirmação

## Como executar

1. Tenha **JDK 21** e **Maven** instalados.
2. Crie o banco `db_produtos` no PostgreSQL (`CREATE DATABASE db_produtos;`)
   ou use `docker compose up -d` (usuário e senha: `postgres`).
3. Em `src/main/resources/META-INF/persistence.xml`, troque `SUA_SENHA` pela senha do seu
   PostgreSQL local. **Não publique sua senha no repositório.**
4. Rode:

```bash
mvn compile exec:java
```

No Eclipse: `File > Import > Maven > Existing Maven Projects`, depois `Main.java > Run As > Java Application`.

A tabela `produtos` é criada pelo Hibernate na primeira execução (`hbm2ddl.auto=update`,
adequado apenas para este exercício).

## Testes manuais

| Teste | Procedimento | Esperado |
|-------|--------------|----------|
| Inclusão | Cadastrar Notebook, 10, 3500.00 | Produto gravado e ID gerado |
| Listagem | Cadastrar Mouse, 50, 75.00 e listar | Ambos exibidos |
| Consulta | Consultar ID existente e inexistente | Registro ou aviso |
| Alteração | Alterar Mouse para Mouse sem fio, 40, 95.00 | Dados atualizados |
| Exclusão | Excluir após confirmar S | Registro removido |
| Validação | Nome vazio; quantidade -1; valor 0 | Operação rejeitada |
| Entrada inválida | Letras no campo quantidade | Pede novo número |

Consultas de conferência: `consultas.sql`.
