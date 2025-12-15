package com.taskflow.service;

import com.taskflow.model.Tarefa;
import com.taskflow.model.Tarefa.Prioridade;
import com.taskflow.model.Tarefa.StatusTarefa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servico responsavel pela gestao de tarefas (CRUD)
 */
public class ServicoTarefa {
    private List<Tarefa> tarefas;

    public ServicoTarefa() {
        this.tarefas = new ArrayList<>();
    }

    // CREATE
    public Tarefa criarTarefa(String titulo, String descricao, Prioridade prioridade, String categoria) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O titulo da tarefa e obrigatorio e nao pode estar vazio.");
        }
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: A descricao da tarefa e obrigatoria.");
        }

        Tarefa tarefa = new Tarefa(titulo, descricao, prioridade, categoria);
        tarefas.add(tarefa);
        return tarefa;
    }

    // READ
    public List<Tarefa> listarTodasTarefas() {
        return new ArrayList<>(tarefas);
    }

    public List<Tarefa> listarPorStatus(StatusTarefa status) {
        return tarefas.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Tarefa> listarPorCategoria(String categoria) {
        return tarefas.stream()
                .filter(t -> t.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }
    public List<Tarefa> listarPorPrioridade(Prioridade prioridade) {
        return tarefas.stream()
                .filter(t -> t.getPrioridade() == prioridade)
                .collect(Collectors.toList());
    }

    public Tarefa buscarPorId(int id) {
        return tarefas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // UPDATE
    public boolean atualizarStatus(int id, StatusTarefa novoStatus) {
        Tarefa tarefa = buscarPorId(id);
        if (tarefa != null) {
            tarefa.setStatus(novoStatus);
            return true;
        }
        return false;
    }

    public boolean atualizarTitulo(int id, String novoTitulo) {
        Tarefa tarefa = buscarPorId(id);
        if (tarefa != null && novoTitulo != null && !novoTitulo.trim().isEmpty()) {
            tarefa.setTitulo(novoTitulo);
            return true;
        }
        return false;
    }

    public boolean atualizarCategoria(int id, String novaCategoria) {
        Tarefa tarefa = buscarPorId(id);
        if (tarefa != null && novaCategoria != null && !novaCategoria.trim().isEmpty()) {
            tarefa.setCategoria(novaCategoria);
            return true;
        }
        return false;
    }

    // DELETE
    public boolean deletarTarefa(int id) {
        Tarefa tarefa = buscarPorId(id);
        if (tarefa != null) {
            tarefas.remove(tarefa);
            return true;
        }
        return false;
    }

    public String obterEstatisticas() {
        int total = tarefas.size();
        int aFazer = listarPorStatus(StatusTarefa.A_FAZER).size();
        int emProgresso = listarPorStatus(StatusTarefa.EM_PROGRESSO).size();
        int concluidas = listarPorStatus(StatusTarefa.CONCLUIDO).size();

        StringBuilder stats = new StringBuilder();
        stats.append("\nEstatisticas do Projeto:\n");
        stats.append("------------------------\n");
        stats.append("Total de tarefas: ").append(total).append("\n");
        stats.append("A Fazer: ").append(aFazer).append("\n");
        stats.append("Em Progresso: ").append(emProgresso).append("\n");
        stats.append("Concluidas: ").append(concluidas).append("\n");

        if (total > 0) {
            int percentualConclusao = (concluidas * 100) / total;
            stats.append("Taxa de conclusao: ").append(percentualConclusao).append("%\n");
        }

        return stats.toString();
    }

    public void exibirQuadroKanban() {
        List<Tarefa> aFazer = listarPorStatus(StatusTarefa.A_FAZER);
        List<Tarefa> emProgresso = listarPorStatus(StatusTarefa.EM_PROGRESSO);
        List<Tarefa> concluidas = listarPorStatus(StatusTarefa.CONCLUIDO);

        System.out.println("\n=== Quadro Kanban ===");
        System.out.println("A FAZER (" + aFazer.size() + ")     |     EM PROGRESSO (" +
                emProgresso.size() + ")     |     CONCLUIDO (" + concluidas.size() + ")");
        System.out.println("-----------------------------------------------------------------------");

        int maxLinhas = Math.max(aFazer.size(), Math.max(emProgresso.size(), concluidas.size()));

        for (int i = 0; i < maxLinhas; i++) {
            String todoText = i < aFazer.size() ? "#" + aFazer.get(i).getId() + " " +
                    truncar(aFazer.get(i).getTitulo(), 15) : "               ";

            String inProgText = i < emProgresso.size() ? "#" + emProgresso.get(i).getId() + " " +
                    truncar(emProgresso.get(i).getTitulo(), 15) : "               ";

            String doneText = i < concluidas.size() ? "#" + concluidas.get(i).getId() + " " +
                    truncar(concluidas.get(i).getTitulo(), 15) : "               ";

            System.out.printf("%-20s | %-20s | %-20s\n", todoText, inProgText, doneText);
        }
        System.out.println("-----------------------------------------------------------------------\n");
    }

    private String truncar(String texto, int tamanho) {
        if (texto.length() <= tamanho) {
            return texto;
        }
        return texto.substring(0, tamanho - 2) + "..";
    }
}