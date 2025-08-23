package br.com.ifdiagnosticos.patterns.bridge;

import br.com.ifdiagnosticos.model.Laudo;

public class GeradorHTML implements GeradorDeFormato {
    @Override
    public String formatar(Laudo laudo) {
        return "<html><head><title>Laudo de Exame</title></head><body>" +
               "<h1>" + laudo.getCabecalho() + "</h1>" +
               "<p><b>Corpo do Laudo:</b><br>" + laudo.getCorpoLaudo().replace("\n", "<br>") + "</p>" +
               "<p>" + laudo.getRodape().replace("\n", "<br>") + "</p>" +
               "</body></html>";
    }
}
