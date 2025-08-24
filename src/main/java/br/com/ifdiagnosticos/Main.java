package br.com.ifdiagnosticos;



import br.com.ifdiagnosticos.model.*;
import br.com.ifdiagnosticos.patterns.bridge.GeradorHTML;
import br.com.ifdiagnosticos.patterns.bridge.GeradorPDF;
import br.com.ifdiagnosticos.patterns.bridge.GeradorTexto;
import br.com.ifdiagnosticos.patterns.chainofresponsibility.Desconto;
import br.com.ifdiagnosticos.patterns.chainofresponsibility.DescontoConvenio;
import br.com.ifdiagnosticos.patterns.chainofresponsibility.DescontoIdoso;
import br.com.ifdiagnosticos.patterns.facade.SistemaLaudoFacade;
import br.com.ifdiagnosticos.patterns.observer.NotificadorEmail;
import br.com.ifdiagnosticos.patterns.strategy.ValidadorGlicose;
import br.com.ifdiagnosticos.patterns.strategy.ValidadorRaioX;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //  Padrão Chain of Responsibility
        Desconto descontoConvenio = new DescontoConvenio();
        Desconto descontoIdoso = new DescontoIdoso();
        descontoConvenio.setProximoDesconto(descontoIdoso);
        // Exemplo de um desconto que não se aplica, então passa para o próximo
        descontoIdoso.setProximoDesconto(null); 

        // Padrão Facade
        SistemaLaudoFacade sistema = new SistemaLaudoFacade(descontoConvenio);

        // Padrão Observer
        sistema.registrarObserver(new NotificadorEmail());
        
        // criação de exames com diferentes prioridades e tipos
        Paciente paciente1 = new Paciente("Maria da Silva", "Plano ABC", 65, "maria@email.com");
        Paciente paciente2 = new Paciente("João Souza", "", 30, "joao@email.com");
        Paciente paciente3 = new Paciente("Bob Esponja", "Plano XYZ", 72, "bob@email.com");
        
        Medico medico1 = new Medico("Dr. Roberto", "CRM-SP 123456");
        
        // Padrão Strategy: Atribui o validador específico para cada exame
        Exame exame1 = new Exame(paciente1, medico1, "Glicose", 50.00, Prioridade.URGENTE);
        exame1.setValidador(new ValidadorGlicose());
        exame1.setResultado("130 mg/DL");

        Exame exame2 = new Exame(paciente2, medico1, "Raio-X de Tórax", 120.00, Prioridade.ROTINA);
        exame2.setValidador(new ValidadorRaioX());
        exame2.setResultado("Imagem sem alterações significativas.");

        Exame exame3 = new Exame(paciente3, medico1, "Glicose", 50.00, Prioridade.POUCO_URGENTE);
        exame3.setValidador(new ValidadorGlicose());
        exame3.setResultado("83 mg/DL");

        // Adicionando exames à fila de prioridade
        sistema.adicionarExameNaFila(exame2);
        sistema.adicionarExameNaFila(exame1);
        sistema.adicionarExameNaFila(exame3);

        // Processa a fila de exames
        sistema.processarFila();
        
        // Simulação de geração de laudos em diferentes formatos
        Medico medicoResponsavel = new Medico("Dra. Roberta", "CRN 654321");
        Laudo laudoGlicose = new LaudoSanguineo(exame1, medicoResponsavel, exame1.getResultado(), "Diagnóstico automático...");

        System.out.println("\n=== Demonstração do Padrão Bridge para Geração de Laudos ===");
        sistema.gerarLaudoEmFormato(laudoGlicose, new GeradorTexto());
        sistema.gerarLaudoEmFormato(laudoGlicose, new GeradorHTML());
        sistema.gerarLaudoEmFormato(laudoGlicose, new GeradorPDF());

        // Requisito 10: Padrão Decorator 
        // Laudo laudoDecorado = new LaudoComAssinaturaDecorator(laudoGlicose, "Assinatura digital válida.");
        // sistema.gerarLaudoEmFormato(laudoDecorado, new GeradorTexto());
    }
}
