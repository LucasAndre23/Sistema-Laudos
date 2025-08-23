package br.com.ifdiagnosticos.patterns.observer;

import br.com.ifdiagnosticos.model.Laudo;

public class NotificadorEmail implements Observer {
    @Override
    public void notificar(Laudo laudo) {
        System.out.println("Notificação por E-mail: Enviando laudo para " + laudo.getExame().getPaciente().getEmail());
        System.out.println("Assunto: Laudo do Exame Disponível");
        System.out.println("Corpo: Prezado(a) " + laudo.getExame().getPaciente().getNome() + ", seu laudo do exame " + laudo.getExame().getTipo() + " já está disponível para acesso.");
    }
}