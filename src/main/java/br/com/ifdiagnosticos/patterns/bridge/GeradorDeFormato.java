package br.com.ifdiagnosticos.patterns.bridge;

import br.com.ifdiagnosticos.model.Laudo;

public interface GeradorDeFormato {
    String formatar(Laudo laudo);
}
