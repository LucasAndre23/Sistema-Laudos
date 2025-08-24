package br.com.ifdiagnosticos.patterns.facade;

import br.com.ifdiagnosticos.model.*;
import br.com.ifdiagnosticos.patterns.FilaPrioridade;
import br.com.ifdiagnosticos.patterns.bridge.GeradorDeFormato;
import br.com.ifdiagnosticos.patterns.chainofresponsibility.Desconto;
import br.com.ifdiagnosticos.patterns.observer.Observer;
import br.com.ifdiagnosticos.patterns.singleton.IDGenerator;

import java.util.ArrayList;
import java.util.List;

public class SistemaLaudoFacade {
    private final IDGenerator idGenerator = IDGenerator.getInstance();
    private final FilaPrioridade filaPrioridade = new FilaPrioridade();
    private final List<Observer> observers = new ArrayList<>();
    private final Desconto cadeiaDescontos;

    public SistemaLaudoFacade(Desconto cadeiaDescontos) {
        this.cadeiaDescontos = cadeiaDescontos;
    }

    public void registrarObserver(Observer observer) {
        observers.add(observer);
    }

    public void adicionarExameNaFila(Exame exame) {
        filaPrioridade.adicionarExame(exame);
    }

    public void processarFila() {
        while (!filaPrioridade.estaVazia()) {
            Exame exame = filaPrioridade.proximoExame();
            System.out.println("\n--- Processando exame da fila: " + exame.getTipo() + " (" + exame.getPrioridade() + ") ---");
            
            int novoId = idGenerator.getNextId();
            exame.setId(novoId);
            System.out.println("ID sequencial gerado: " + novoId);

            System.out.printf("Valor original: R$%.2f\n", exame.getValor());
            double valorFinal = cadeiaDescontos.aplicarDesconto(exame);
            System.out.printf("Valor final com descontos: R$%.2f\n", valorFinal);

            exame.validar();

            // Gerar Laudo
            Medico medicoResponsavel = new Medico("Dr. João Silva", "CRN 12345", true);
            String corpoLaudo = "Resultado do exame: " + exame.getResultado();
            Laudo laudo = new Laudo(exame, medicoResponsavel, corpoLaudo);
            
            // Notificar Observers
            observers.forEach(o -> o.notificar(laudo));
            
            System.out.println("Laudo gerado com sucesso.");
        }
    }

    public void gerarLaudoEmFormato(Laudo laudo, GeradorDeFormato gerador) {
        System.out.println("\n--- Gerando laudo no formato " + gerador.getClass().getSimpleName() + " ---");
        System.out.println(laudo.gerar(gerador));
    }
}
