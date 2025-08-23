package br.com.ifdiagnosticos.patterns.bridge;

import br.com.ifdiagnosticos.model.Laudo;

public class GeradorPDF implements GeradorDeFormato {
    @Override
    public String formatar(Laudo laudo) {
        
        // simulação para demonstrar a ideia do padrão Bridge.
        System.out.println("--- Gerando documento PDF (simulado) ---");
        
        StringBuilder pdfContent = new StringBuilder();
        pdfContent.append("PDF Document Start\n");
        pdfContent.append("Header:\n").append(laudo.getCabecalho());
        pdfContent.append("\nBody:\n").append(laudo.getCorpoLaudo());
        pdfContent.append("\nFooter:\n").append(laudo.getRodape());
        pdfContent.append("PDF Document End\n");

        return pdfContent.toString();
        
        // implementação real :
        /*
        try (OutputStream outputStream = new FileOutputStream("laudo.pdf")) {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(new Paragraph(laudo.getCabecalho()));
            document.add(new Paragraph(laudo.getCorpoLaudo()));
            document.add(new Paragraph(laudo.getRodape()));
            document.close();
            System.out.println("Arquivo PDF gerado: laudo.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Arquivo PDF gerado.";
        */
    }
}