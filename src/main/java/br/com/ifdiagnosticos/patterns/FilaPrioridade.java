package br.com.ifdiagnosticos.patterns;

import br.com.ifdiagnosticos.model.Exame;
import br.com.ifdiagnosticos.model.Prioridade;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class FilaPrioridade {
    private Queue<Exame> fila;

    public FilaPrioridade() {
        this.fila = new PriorityQueue<>(Comparator.comparingInt(this::getPrioridadeNumerica));
    }

    private int getPrioridadeNumerica(Exame exame) {
        if (exame.getPrioridade() == Prioridade.URGENTE) return 1;
        if (exame.getPrioridade() == Prioridade.POUCO_URGENTE) return 2;
        return 3;
    }

    public void adicionarExame(Exame exame) {
        this.fila.add(exame);
        System.out.println("Exame de " + exame.getTipo() + " (" + exame.getPrioridade() + ") adicionado à fila.");
    }

    public Exame proximoExame() {
        return fila.poll();
    }

    public boolean estaVazia() {
        return fila.isEmpty();
    }
}