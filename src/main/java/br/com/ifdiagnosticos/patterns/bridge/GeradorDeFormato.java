package br.com.ifdiagnosticos.patterns.bridge;

import br.com.ifdiagnosticos.model.Laudo;

public interface GeradorDeFormato {
    void formatar(Laudo laudo);
}
