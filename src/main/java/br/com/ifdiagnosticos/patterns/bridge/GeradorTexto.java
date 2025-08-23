package br.com.ifdiagnosticos.patterns.bridge;

import br.com.ifdiagnosticos.model.Laudo;

public class GeradorTexto implements GeradorDeFormato {
    @Override
    public String formatar(Laudo laudo) {
        return laudo.getCabecalho() +
               "Corpo do Laudo:\n" +
               laudo.getCorpoLaudo() + "\n" +
               laudo.getRodape();
    }
}
