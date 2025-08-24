package br.com.ifdiagnosticos.model;

import br.com.ifdiagnosticos.patterns.bridge.GeradorDeFormato;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Laudo {
    private int id;
    private Medico medicoResponsavel;
    private Exame exame;
    private String corpoLaudo;
    private Date dataEmissao;

    public Laudo(Exame exame, Medico medicoResponsavel, String corpoLaudo) {
        this.id = exame.getId();
        this.exame = exame;
        this.medicoResponsavel = medicoResponsavel;
        this.corpoLaudo = corpoLaudo;
        this.dataEmissao = new Date();
    }

    // Padrão Bridge
    public String gerar(GeradorDeFormato gerador) {
        return gerador.formatar(this);
    }

    // Getters...
    public int getId() { return id; }
    public Exame getExame() { return exame; }
    public Medico getMedicoResponsavel() { return medicoResponsavel; }
    public String getCorpoLaudo() { return corpoLaudo; }
    public Date getDataEmissao() { return dataEmissao; }

    public String getCabecalho() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return "IF Diagnósticos - Laudo de Exame\n" +
               "ID do Exame: " + this.id + "\n" +
               "Nome do Paciente: " + this.exame.getPaciente().getNome() + "\n" +
               "Convênio: " + this.exame.getPaciente().getConvenio() + "\n" +
               "Médico Solicitante: " + this.exame.getMedicoSolicitante().getNome() + " (" + this.exame.getMedicoSolicitante().getCrm() + ")\n" +
               "Data da Implantação no Sistema: " + sdf.format(this.dataEmissao) + "\n";
    }

    public String getRodape() {
        return "\n----------------------------------------\n" +
               "Médico Responsável: " + this.medicoResponsavel.getNome() + " (" + this.medicoResponsavel.getCrm() + ")\n";
    }
}

