package com.taskflow;

import com.taskflow.model.Tarefa;
import com.taskflow.model.Tarefa.Prioridade;
import com.taskflow.model.Tarefa.StatusTarefa;
import com.taskflow.service.ServicoTarefa;
import com.taskflow.util.ValidadorEntrada;

import java.util.List;
import java.util.Scanner;

/**
 * Sistema de gerenciamento de tarefas baseado em metodologias ageis
 * Projeto academico - Engenharia de Software
 */
public class Main {
    private static ServicoTarefa servicoTarefa = new ServicoTarefa();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("    TaskFlow - Gestao de Tarefas");
        System.out.println("    Sistema Agil de Gerenciamento");
        System.out.println("=====================================\n");

        carregarDadosExemplo();

        boolean executando = true;

        while (executando) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    criarNovaTarefa();
                    break;
                case 2:
                    listarTodasTarefas();
                    break;
                case 3:
                    listarTarefasPorStatus();
                    break;
                case 4:
                    listarTarefasPorCategoria();
                    break;
                case 5:
                    atualizarStatusTarefa();
                    break;
                case 6:
                    atualizarDetalhesTarefa();
                    break;
                case 7:
                    deletarTarefa();
                    break;
                case 8:
                    servicoTarefa.exibirQuadroKanban();
                    break;
                case 9:
                    System.out.println(servicoTarefa.obterEstatisticas());
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1. Criar nova tarefa");
        System.out.println("2. Listar todas as tarefas");
        System.out.println("3. Listar tarefas por status");
        System.out.println("4. Listar tarefas por categoria");
        System.out.println("5. Atualizar status da tarefa");
        System.out.println("6. Atualizar detalhes da tarefa");
        System.out.println("7. Deletar tarefa");
        System.out.println("8. Visualizar quadro Kanban");
        System.out.println("9. Ver estatisticas");
        System.out.println("0. Sair");
        System.out.print("\nSelecione uma opcao: ");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void criarNovaTarefa() {
        System.out.println("\n--- Criar Nova Tarefa ---");

        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();

        System.out.print("Descricao: ");
        String descricao = scanner.nextLine();

        System.out.println("Prioridade (1-Alta, 2-Media, 3-Baixa): ");
        String prioridadeEntrada = scanner.nextLine();
        Prioridade prioridade = ValidadorEntrada.converterPrioridade(prioridadeEntrada);

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        try {
            Tarefa tarefa = servicoTarefa.criarTarefa(titulo, descricao, prioridade, categoria);
            System.out.println("\nTarefa criada com sucesso!");
            System.out.println(tarefa);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarTodasTarefas() {
        List<Tarefa> tarefas = servicoTarefa.listarTodasTarefas();

        if (tarefas.isEmpty()) {
            System.out.println("\nNenhuma tarefa encontrada.");
            return;
        }

        System.out.println("\n=== Todas as Tarefas ===");
        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa);
        }
    }

    private static void listarTarefasPorStatus() {
        System.out.println("\nSelecione o status:");
        System.out.println("1. A Fazer");
        System.out.println("2. Em Progresso");
        System.out.println("3. Concluido");
        System.out.print("Opcao: ");

        String entrada = scanner.nextLine();
        StatusTarefa status = ValidadorEntrada.converterStatus(entrada);

        List<Tarefa> tarefas = servicoTarefa.listarPorStatus(status);

        if (tarefas.isEmpty()) {
            System.out.println("\nNenhuma tarefa com status: " + status);
            return;
        }

        System.out.println("\n=== Tarefas: " + status + " ===");
        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa);
        }
    }

    private static void listarTarefasPorCategoria() {
        System.out.print("\nDigite a categoria: ");
        String categoria = scanner.nextLine();

        List<Tarefa> tarefas = servicoTarefa.listarPorCategoria(categoria);

        if (tarefas.isEmpty()) {
            System.out.println("\nNenhuma tarefa encontrada na categoria: " + categoria);
            return;
        }

        System.out.println("\n=== Tarefas na categoria: " + categoria + " ===");
        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa);
        }
    }

    private static void atualizarStatusTarefa() {
        System.out.print("\nDigite o ID da tarefa: ");
        String idEntrada = scanner.nextLine();

        if (!ValidadorEntrada.isNumeroValido(idEntrada)) {
            System.out.println("ID invalido.");
            return;
        }

        int id = Integer.parseInt(idEntrada);
        Tarefa tarefa = servicoTarefa.buscarPorId(id);

        if (tarefa == null) {
            System.out.println("Tarefa nao encontrada.");
            return;
        }

        System.out.println("\nTarefa atual:");
        System.out.println(tarefa);

        System.out.println("\nSelecione o novo status:");
        System.out.println("1. A Fazer");
        System.out.println("2. Em Progresso");
        System.out.println("3. Concluido");
        System.out.print("Opcao: ");

        String statusEntrada = scanner.nextLine();
        StatusTarefa novoStatus = ValidadorEntrada.converterStatus(statusEntrada);

        if (servicoTarefa.atualizarStatus(id, novoStatus)) {
            System.out.println("Status atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar status.");
        }
    }

    private static void atualizarDetalhesTarefa() {
        System.out.print("\nDigite o ID da tarefa: ");
        String idEntrada = scanner.nextLine();

        if (!ValidadorEntrada.isNumeroValido(idEntrada)) {
            System.out.println("ID invalido.");
            return;
        }

        int id = Integer.parseInt(idEntrada);
        Tarefa tarefa = servicoTarefa.buscarPorId(id);

        if (tarefa == null) {
            System.out.println("Tarefa nao encontrada.");
            return;
        }

        System.out.println("\nTarefa atual:");
        System.out.println(tarefa);

        System.out.println("\nO que deseja atualizar?");
        System.out.println("1. Titulo");
        System.out.println("2. Categoria");
        System.out.print("Opcao: ");

        int opcao = lerOpcao();

        switch (opcao) {
            case 1:
                System.out.print("Novo titulo: ");
                String novoTitulo = scanner.nextLine();
                if (servicoTarefa.atualizarTitulo(id, novoTitulo)) {
                    System.out.println("Titulo atualizado com sucesso!");
                }
                break;
            case 2:
                System.out.print("Nova categoria: ");
                String novaCategoria = scanner.nextLine();
                if (servicoTarefa.atualizarCategoria(id, novaCategoria)) {
                    System.out.println("Categoria atualizada com sucesso!");
                }
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    private static void deletarTarefa() {
        System.out.print("\nDigite o ID da tarefa para deletar: ");
        String idEntrada = scanner.nextLine();

        if (!ValidadorEntrada.isNumeroValido(idEntrada)) {
            System.out.println("ID invalido.");
            return;
        }

        int id = Integer.parseInt(idEntrada);

        System.out.print("Tem certeza? (sim/nao): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("sim") || confirmacao.equalsIgnoreCase("s")) {
            if (servicoTarefa.deletarTarefa(id)) {
                System.out.println("Tarefa deletada com sucesso!");
            } else {
                System.out.println("Tarefa nao encontrada.");
            }
        } else {
            System.out.println("Delecao cancelada.");
        }
    }

    private static void carregarDadosExemplo() {
        servicoTarefa.criarTarefa("Configurar ambiente de desenvolvimento",
                "Instalar Java, IntelliJ e Git",
                Prioridade.ALTA,
                "Configuracao");

        servicoTarefa.criarTarefa("Criar estrutura do projeto",
                "Organizar pacotes e pastas",
                Prioridade.ALTA,
                "Desenvolvimento");

        servicoTarefa.criarTarefa("Implementar operacoes CRUD",
                "Funcionalidades de criar, ler, atualizar e deletar",
                Prioridade.MEDIA,
                "Desenvolvimento");

        Tarefa tarefa = servicoTarefa.buscarPorId(1);
        if (tarefa != null) {
            tarefa.marcarComoConcluida();
        }

        Tarefa tarefa2 = servicoTarefa.buscarPorId(2);
        if (tarefa2 != null) {
            tarefa2.moverParaEmProgresso();
        }
    }
}