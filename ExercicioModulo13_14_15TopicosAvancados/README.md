# ExercicioModulo13_14_15TopicosAvancados

Exercícios dos **Módulos 13, 14 e 15**: tópicos avançados de Java aplicados à **logística** de um e-commerce.

## Tópicos abordados

- **Bloco 1 (exceções em domínio):** exceções customizadas `LogisticaException` e `EstoqueInvalidoException`
- **Bloco 1 (acionamento):** `ItemLogistico` / `PacoteExpresso` com `StatusRastreio`
- **Bloco 2 (genéricos):** classe genérica `FilaArmazenamento<T>`, cálculo de frete com `CalculadoraLogistica`
- **Bloco 3 (Streams/Lambdas):** `ProcessadorStreamsApp` (filter, map, reduce, collect, Optional)
- **Bloco 4 (anotações/reflection):** anotação `@AuditoriaLogistica` + `AuditorMetadadosReflection` inspecionando metadados em tempo de execução

## Estrutura

| Pacote | Conteúdo |
|---|---|
| `br.com.ecommerce.excecoes` | `LogisticaException`, `EstoqueInvalidoException` |
| `br.com.ecommerce.logistica` | `ItemLogistico`, `PacoteExpresso`, `StatusRastreio` |
| `br.com.ecommerce.util` | `FilaArmazenamento<T>`, `CalculadoraLogistica`, `AuditorMetadadosReflection` |
| `br.com.ecommerce.anotacoes` | `@AuditoriaLogistica` |
| `br.com.ecommerce.app` | 4 aplicações executáveis |

## Aplicações (classe principal de cada uma)

| Aplicação | Demonstra |
|---|---|
| `DemonstradorBloco1App` | Exceções customizadas e rastreio de pacotes |
| `DemonstradorBloco2App` | Genéricos (`FilaArmazenamento<T>`) e cálculo de frete |
| `ProcessadorStreamsApp` | Streams e expressões lambda |
| `DemonstradorBloco4App` | Anotações e reflection |

## Como executar

1. Importe o projeto no Eclipse: `File > Import > Existing Projects into Workspace`.
2. Rode a aplicação desejada (`br.com.ecommerce.app.<Nome>`) como *Java Application*.
3. Nenhum pré-requisito externo (sem banco de dados).