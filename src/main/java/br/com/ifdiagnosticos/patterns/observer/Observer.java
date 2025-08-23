package br.com.ifdiagnosticos.patterns.observer;

import br.com.ifdiagnosticos.model.Laudo;

public interface Observer {
    void notificar(Laudo laudo);
}