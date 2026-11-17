# prjExemploJPA

Exemplo de persistência relacional com **JPA 2.2 + Hibernate 5.6 + Maven + PostgreSQL**.
Entidade `Empregado` e uma classe `TesteJPA` que insere, busca por ID e consulta com JPQL (conforme o roteiro).

## Estrutura

```
prjExemploJPA
├── pom.xml
├── docker-compose.yml
└── src/main
    ├── java
    │   ├── app/TesteJPA.java
    │   └── model/Empregado.java
    └── resources/META-INF/persistence.xml
```

## Pré-requisitos

- Java 11+
- Maven 3.6+
- PostgreSQL na porta 5432 com o banco `exemplo_jpa` (usuário/senha: `postgres`/`postgres`)

### Subindo o banco com Docker (opcional)

```bash
docker compose up -d
```

Sem Docker, crie o banco manualmente: `CREATE DATABASE exemplo_jpa;`

## Executando

```bash
mvn compile exec:java
```

As credenciais ficam em `src/main/resources/META-INF/persistence.xml`.
A tabela `EMPREGADO` é criada automaticamente (`hibernate.hbm2ddl.auto=update`).

## Abrindo no Eclipse

`File > Import > Maven > Existing Maven Projects` e selecione a pasta do projeto.

## Problemas comuns

| Erro | Solução |
|------|---------|
| `No Persistence provider for EntityManager named PuMvcJpa` | Confirme que o arquivo está em `src/main/resources/META-INF/persistence.xml` e que o nome da unidade é `PuMvcJpa`. |
| `Connection refused` / `password authentication failed` | Verifique se o PostgreSQL está ativo, o banco existe e usuário/senha estão corretos. |
| `ClassNotFoundException` | Rode `mvn clean compile -U` (no Eclipse: Maven > Update Project). |
