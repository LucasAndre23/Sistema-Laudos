package br.com.ifdiagnosticos.patterns.bridge;

import java.io.FileWriter;
import java.io.IOException;

import br.com.ifdiagnosticos.model.Laudo;

public class GeradorHTML implements GeradorDeFormato {
    @Override
    public void formatar(Laudo laudo) {
        String conteudoHtml = "<html><head><title>Laudo de Exame</title></head><body>" +
                               "<h1>" + laudo.getCabecalho() + "</h1>" +
                               "<p><b>Corpo do Laudo:</b><br>" + laudo.getCorpoLaudo().replace("\n", "<br>") + "</p>" +
                               "<p>" + laudo.getRodape().replace("\n", "<br>") + "</p>" +
                               "</body></html>";

        String nomeArquivo = "laudo_exame_" + laudo.getExame().getId() + ".html";

        try (FileWriter fileWriter = new FileWriter(nomeArquivo)) {
            fileWriter.write(conteudoHtml);
            System.out.println("Arquivo HTML gerado com sucesso: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao gerar o arquivo HTML: " + e.getMessage());
            e.printStackTrace();;
    }
    }}
