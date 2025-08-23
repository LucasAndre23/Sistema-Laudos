package br.com.ifdiagnosticos.patterns.strategy;

import br.com.ifdiagnosticos.model.Exame;

public interface ValidadorExame {
    void validar(Exame exame);
}
