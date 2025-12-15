# TaskFlow - Sistema de Gerenciamento de Tarefas

Sistema de gerenciamento de tarefas desenvolvido com metodologias ágeis para a disciplina de Engenharia de Software.

## Sobre o Projeto

TaskFlow é um sistema console desenvolvido em Java que implementa funcionalidades de gerenciamento de tarefas seguindo os princípios ágeis, utilizando quadro Kanban para visualização do fluxo de trabalho.

## Objetivo

Desenvolver um sistema que permita:
- Criar, visualizar, atualizar e deletar tarefas (CRUD)
- Acompanhar o fluxo de trabalho em tempo real
- Priorizar tarefas críticas
- Monitorar o desempenho através de estatísticas
- Categorizar tarefas (mudança de escopo implementada)

## Metodologia

O projeto utiliza a metodologia **Kanban** com as seguintes colunas:
- A Fazer
- Em Progresso
- Concluído

## Tecnologias

- **Linguagem:** Java 21 (OpenJDK Temurin)
- **IDE:** IntelliJ IDEA 2025.3 Ultimate
- **Controle de Versão:** Git 2.51.2
- **Plataforma:** GitHub
- **CI/CD:** GitHub Actions
- **Testes:** JUnit (em implementação)
- **Build:** IntelliJ Build System


## Estrutura do Projeto
```
taskflow/
├── src/
│   └── com.taskflow/
│       ├── model/
│       │   └── Tarefa.java
│       ├── service/
│       │   └── ServicoTarefa.java
│       ├── util/
│       │   └── ValidadorEntrada.java
│       └── Main.java
├── tests/
├── docs/
└── README.md
```

## Funcionalidades

### CRUD Completo
- **Create**: Criar novas tarefas com título, descrição, prioridade e categoria
- **Read**: Listar todas as tarefas ou filtrar por status/categoria
- **Update**: Atualizar status, título ou categoria de tarefas existentes
- **Delete**: Remover tarefas do sistema

### Recursos Adicionais
- Quadro Kanban visual
- Estatísticas do projeto
- Sistema de prioridades (Alta, Média, Baixa)
- Categorização de tarefas
- Registro de datas de criação e conclusão

## Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/dassaester/softeng-taskflow.git
```

2. Abra o projeto no IntelliJ IDEA

3. Execute a classe `Main.java`

## Mudança de Escopo

Durante o desenvolvimento, foi implementada a funcionalidade de **categorização de tarefas**, permitindo organizar as tarefas por categorias específicas (ex: Desenvolvimento, Configuração, Testes). Esta mudança foi documentada e refletida no quadro Kanban.

## Referência

Desenvolvido como projeto acadêmico para a disciplina de Engenharia de Software - UniFECAF