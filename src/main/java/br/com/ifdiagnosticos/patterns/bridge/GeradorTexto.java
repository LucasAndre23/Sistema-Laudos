package br.com.ifdiagnosticos.patterns.bridge;

import java.io.FileWriter;
import java.io.IOException;

import br.com.ifdiagnosticos.model.Laudo;

public class GeradorTexto implements GeradorDeFormato {
    @Override
    public void formatar(Laudo laudo) {
        String conteudoTexto = laudo.getCabecalho() +
                               "\nCorpo do Laudo:\n" +
                               laudo.getCorpoLaudo() + "\n" +
                               laudo.getRodape();

        String nomeArquivo = "laudo_exame_" + laudo.getExame().getId() + ".txt";

        try (FileWriter fileWriter = new FileWriter(nomeArquivo)) {
            fileWriter.write(conteudoTexto);
            System.out.println("Arquivo de texto gerado com sucesso: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao gerar o arquivo de texto: " + e.getMessage());
            e.printStackTrace();
    }
}
}
