package br.com.ifdiagnosticos;

import br.com.ifdiagnosticos.model.*;
import br.com.ifdiagnosticos.patterns.bridge.GeradorHTML;
import br.com.ifdiagnosticos.patterns.bridge.GeradorPDF;
import br.com.ifdiagnosticos.patterns.bridge.GeradorTexto;
import br.com.ifdiagnosticos.patterns.chainofresponsibility.Desconto;
import br.com.ifdiagnosticos.patterns.chainofresponsibility.DescontoConvenio;
import br.com.ifdiagnosticos.patterns.chainofresponsibility.DescontoIdoso;
import br.com.ifdiagnosticos.patterns.facade.SistemaLaudoFacade;
import br.com.ifdiagnosticos.patterns.factory.CsvExameScenarioFactory;
import br.com.ifdiagnosticos.patterns.factory.ExameScenarioFactory;
import br.com.ifdiagnosticos.patterns.observer.NotificadorEmail;
import br.com.ifdiagnosticos.patterns.strategy.ValidadorGlicose;
import br.com.ifdiagnosticos.patterns.strategy.ValidadorRaioX;
import br.com.ifdiagnosticos.patterns.strategy.ValidadorRessonancia;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Inicialização do sistema de laboratório
        SistemaLaboratorio sistemaLaboratorio = new SistemaLaboratorio();
        sistemaLaboratorio.executar();
        
    }
}

class SistemaLaboratorio {
    
    private final ExameScenarioFactory scenarioFactory;
    
    public SistemaLaboratorio() {
        this.scenarioFactory = new CsvExameScenarioFactory("data/pacientes.csv", "data/medicos.csv");
    }
    
    public SistemaLaboratorio(ExameScenarioFactory scenarioFactory) {
        this.scenarioFactory = scenarioFactory;
    }

    public void executar() {
        // Carrega dados do cenário
        List<Paciente> pacientes = scenarioFactory.getPacientesParaExames();
        Medico medicoSolicitante = scenarioFactory.getMedicoSolicitante();
        Medico medicoResponsavel = scenarioFactory.getMedicoResponsavel();
      // Configura o sistema usando o Facade
        SistemaLaudoFacade sistema = configurarSistemaFacade();
        
        // Processa exames para todos os pacientes dinamicamente
        processarTodosPacientes(pacientes, medicoSolicitante, medicoResponsavel, sistema);
        
        System.out.println("\n=== PROCESSAMENTO DA FILA DE PRIORIDADE ===");
        sistema.processarFila();
        
        System.out.println("\n=== SISTEMA EXECUTADO COM SUCESSO ===");
    }
    
    //  Configura o SistemaLaudoFacade com a cadeia de descontos e observers
    private SistemaLaudoFacade configurarSistemaFacade() {
        Desconto cadeiaDescontos = criarCadeiaDescontos();
        SistemaLaudoFacade sistema = new SistemaLaudoFacade(cadeiaDescontos);
        sistema.registrarObserver(new NotificadorEmail());
        
        return sistema;
    }
    
    private Desconto criarCadeiaDescontos() {
        Desconto descontoConvenio = new DescontoConvenio();
        Desconto descontoIdoso = new DescontoIdoso();
        
        // Configurando a ordem da cadeia
        descontoConvenio.setProximoDesconto(descontoIdoso);
        descontoIdoso.setProximoDesconto(null);
        
        return descontoConvenio;
    }

    private void processarTodosPacientes(List<Paciente> pacientes, 
                                        Medico medicoSolicitante, 
                                        Medico medicoResponsavel, 
                                        SistemaLaudoFacade sistema) {
        
        System.out.println("=== PROCESSAMENTO DINÂMICO DE PACIENTES ===");
        System.out.println("Total de pacientes a processar: " + pacientes.size() + "\n");

        int proximoId = 1;
        
        for (int i = 0; i < pacientes.size(); i++) {
            Paciente paciente = pacientes.get(i);
            System.out.println("--- Processando Paciente " + (i + 1) + ": " + paciente.getNome() + " ---");
            
            // Exame de Glicose (prioridade URGENTE)
        
            Exame exame1 = new Exame(new Date(), "Glicose",50.00, "130 mg/DL", paciente, medicoSolicitante, Prioridade.URGENTE);
            exame1.setId(proximoId++);
            exame1.setValidador(new ValidadorGlicose());

            // Exame de Raio-X de Tórax (prioridade ROTINA)
            Exame exame2 = new Exame(new Date(), "Raio-X de Tórax",100.00, "Imagem sem alterações significativas.", paciente, medicoSolicitante, Prioridade.ROTINA);
            exame2.setId(proximoId++);
            exame2.setValidador(new ValidadorRaioX());
            exame2.setMedicoResponsavel(medicoResponsavel);

            // Exame de Ressonância Magnética (prioridade ALTA)
            Exame exame3 = new Exame(new Date(), "Ressonância Magnética",200.00, "Laudo da RM...", paciente, medicoSolicitante, Prioridade.URGENTE);
            exame3.setId(proximoId++);
            exame3.setValidador(new ValidadorRessonancia());
            exame3.setMedicoResponsavel(medicoResponsavel);

            System.out.println("\n=== Adicionando Exames à Fila ===");
            // Adicionando exames fora de ordem para testar a fila de prioridade
            sistema.adicionarExameNaFila(exame2); // ROTINA
            sistema.adicionarExameNaFila(exame1); // URGENTE
            sistema.adicionarExameNaFila(exame3); // URGENTE

            System.out.println("\n=== Demonstração do Padrão Bridge para Geração de Laudos ===");
            Laudo laudoGlicose = new LaudoSanguineo(exame1, medicoResponsavel, exame1.getResultado(), "Diagnóstico automático...");
            
            sistema.gerarLaudoEmFormato(laudoGlicose, new GeradorTexto());
            sistema.gerarLaudoEmFormato(laudoGlicose, new GeradorHTML());
            sistema.gerarLaudoEmFormato(laudoGlicose, new GeradorPDF());
            
        }
    }
}

