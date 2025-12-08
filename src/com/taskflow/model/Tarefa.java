package com.taskflow.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa uma tarefa no sistema TaskFlow.
 * Cada tarefa possui identificador unico, titulo, descricao,
 * prioridade, categoria e status que reflete sua posicao no Kanban.
 *
 * @author Hadassa Ester
 * @version 1.0
 * @since 2025-12-08
 */
public class Tarefa {
    private static int contadorId = 1;

    private int id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private String categoria;
    private StatusTarefa status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;

    public enum Prioridade {
        ALTA, MEDIA, BAIXA
    }

    public enum StatusTarefa {
        A_FAZER, EM_PROGRESSO, CONCLUIDO
    }

    public Tarefa(String titulo, String descricao, Prioridade prioridade, String categoria) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = StatusTarefa.A_FAZER;
        this.dataCriacao = LocalDateTime.now();
    }

    public void marcarComoConcluida() {
        this.status = StatusTarefa.CONCLUIDO;
        this.dataConclusao = LocalDateTime.now();
    }

    public void moverParaEmProgresso() {
        this.status = StatusTarefa.EM_PROGRESSO;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
        if (status == StatusTarefa.CONCLUIDO) {
            this.dataConclusao = LocalDateTime.now();
        }
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();

        sb.append("\nID da Tarefa: ").append(id).append("\n");
        sb.append("Titulo: ").append(titulo).append("\n");
        sb.append("Descricao: ").append(descricao).append("\n");
        sb.append("Prioridade: ").append(prioridade).append("\n");
        sb.append("Categoria: ").append(categoria).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Criada em: ").append(dataCriacao.format(formatador)).append("\n");

        if (dataConclusao != null) {
            sb.append("Concluida em: ").append(dataConclusao.format(formatador)).append("\n");
        }

        return sb.toString();
    }
}