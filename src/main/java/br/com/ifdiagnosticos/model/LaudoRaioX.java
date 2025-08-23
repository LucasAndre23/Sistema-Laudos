package br.com.ifdiagnosticos.model;

public class LaudoRaioX extends Laudo {
    private String descricaoLaudo;

    public LaudoRaioX(Exame exame, Medico medicoResponsavel, String descricaoLaudo) {
        super(exame, medicoResponsavel, descricaoLaudo);
        this.descricaoLaudo = descricaoLaudo;
    }
}
