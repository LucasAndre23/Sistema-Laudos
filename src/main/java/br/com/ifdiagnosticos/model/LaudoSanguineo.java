package br.com.ifdiagnosticos.model;

public class LaudoSanguineo extends Laudo {
    private String diagnosticoAutomatico;

    public LaudoSanguineo(Exame exame, Medico medicoResponsavel, String corpoLaudo, String diagnosticoAutomatico) {
        super(exame, medicoResponsavel, corpoLaudo);
        this.diagnosticoAutomatico = diagnosticoAutomatico;
    }

    public String getDiagnosticoAutomatico() {
        return diagnosticoAutomatico;
    }
}
