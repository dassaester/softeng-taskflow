package com.taskflow.util;

import com.taskflow.model.Tarefa.Prioridade;
import com.taskflow.model.Tarefa.StatusTarefa;

public class ValidadorEntrada {

    public static boolean isTextoValido(String entrada) {
        return entrada != null && !entrada.trim().isEmpty();
    }

    public static boolean isNumeroValido(String entrada) {
        try {
            Integer.parseInt(entrada);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Prioridade converterPrioridade(String entrada) {
        if (entrada == null) {
            return Prioridade.MEDIA;
        }

        switch (entrada.toUpperCase().trim()) {
            case "1":
            case "ALTA":
            case "HIGH":
                return Prioridade.ALTA;
            case "2":
            case "MEDIA":
            case "MEDIUM":
                return Prioridade.MEDIA;
            case "3":
            case "BAIXA":
            case "LOW":
                return Prioridade.BAIXA;
            default:
                return Prioridade.MEDIA;
        }
    }

    public static StatusTarefa converterStatus(String entrada) {
        if (entrada == null) {
            return StatusTarefa.A_FAZER;
        }

        switch (entrada.toUpperCase().trim()) {
            case "1":
            case "A_FAZER":
            case "TODO":
                return StatusTarefa.A_FAZER;
            case "2":
            case "EM_PROGRESSO":
            case "IN_PROGRESS":
                return StatusTarefa.EM_PROGRESSO;
            case "3":
            case "CONCLUIDO":
            case "DONE":
                return StatusTarefa.CONCLUIDO;
            default:
                return StatusTarefa.A_FAZER;
        }
    }
}