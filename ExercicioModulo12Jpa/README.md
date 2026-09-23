# ExercicioModulo12Jpa

Exercício do **Módulo 12**: mapeamento objeto-relacional com **JPA** e **Hibernate**, aplicado ao contexto de um e-commerce.

## Tópicos abordados

- Configuração de `persistence.xml` (unidade `PuEcommerceJpa`)
- Entidades com `@Entity` e mapeamento de atributos
- `@NamedQueries`, JPQL e `TypedQuery`
- Ciclo de vida das entidades (persist, merge, find, remove)
- Relacionamentos: `@ManyToOne`, `@OneToMany`, `@ManyToMany`
- Herança JOINED (`Frete`, `FreteAereo`, `FreteRodoviario`)
- DAO com `EntityManager` e controle de transações (inclusive reajuste em massa por categoria)

## Entidades

`ProdutoJpa`, `Categoria`, `Cliente`, `PerfilFiscal`, `PedidoJpa`, `Frete` (+ subclasses `FreteAereo` e `FreteRodoviario`).

## Estrutura

| Item | Função |
|---|---|
| `pom.xml` | Maven: JPA 2.2 (`javax.persistence` 2.2), Hibernate 5.6.15.Final, driver PostgreSQL 42.7.13 (Java 21) |
| `src/main/resources/META-INF/persistence.xml` | Conexão `jdbc:postgresql://localhost:5432/bdecommerce`, usuário `postgres`, senha `postgres`, `hbm2ddl=update` |
| `br.com.ecommerce.util.JpaUtil` | Fábrica de `EntityManager` |
| `br.com.ecommerce.dao.ProdutoConsultasDAO` | Consultas JPQL e reajuste em massa |
| `br.com.ecommerce.app.*` | 5 aplicações executáveis |

## Aplicações (classe principal de cada uma)

| Aplicação | Demonstra |
|---|---|
| `TesteSetupJpaApp` | Configuração funcionando (primeira execução) |
| `CicloVidaJpaApp` | Ciclo de vida das entidades |
| `AppRelacionamentosJpa` | Relacionamentos entre entidades |
| `AppHerancaJpa` | Herança JOINED |
| `AppConsultasJpql` | Consultas JPQL e reajuste em massa |

## Pré-requisitos

- **PostgreSQL** instalado e em execução (porta `5432`) com o banco **`bdecommerce`** criado (as tabelas são criadas automaticamente via `hibernate.hbm2ddl.auto=update`).
- **Eclipse com m2e (Maven embutido)** — não é necessário Maven instalado no sistema.
- Usuário `postgres` com senha **`postgres`** (no `persistence.xml`). Ajuste caso a sua senha seja diferente.

## Como executar

1. Importe o projeto no Eclipse como *Maven Project*: `File > Import > Maven > Existing Maven Projects`.
2. Aguarde o download das dependências (`mvn` do m2e).
3. Rode a aplicação desejada (`br.com.ecommerce.app.<Nome>`) como *Java Application*.

> **Atenção:** ao abrir como "Existing Projects", o Maven pode não resolver as dependências; prefira a importação via *Maven Projects*.