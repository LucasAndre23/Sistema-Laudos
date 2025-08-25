package br.com.ifdiagnosticos.patterns.bridge;

import br.com.ifdiagnosticos.model.Laudo;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class GeradorPDF implements GeradorDeFormato {
    @Override
    public void formatar(Laudo laudo) {
        try (OutputStream outputStream = new FileOutputStream("laudo-" + laudo.getId() + ".pdf")) {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(new Paragraph(laudo.getCabecalho()));
            document.add(new Paragraph(laudo.getCorpoLaudo()));
            document.add(new Paragraph(laudo.getRodape()));
            document.close();
            System.out.println("Arquivo PDF gerado: laudo-" + laudo.getId() + ".pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}