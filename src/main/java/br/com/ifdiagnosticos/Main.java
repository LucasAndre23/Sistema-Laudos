package br.com.ifdiagnosticos;

import br.com.ifdiagnosticos.model.Prioridade;
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
import br.com.ifdiagnosticos.patterns.strategy.ValidadorRessonancia;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        
        // Simulação do carregamento de dados de arquivos CSV
        Paciente paciente1 = new Paciente("Maria da Silva", "Plano ABC", 65, "maria@email.com", false, false);
        Paciente paciente2 = new Paciente("João Souza", "Particular", 30, "joao@email.com", false, false);
        Paciente paciente3 = new Paciente("Bob Silva", "Plano XYZ", 72, "bob@email.com", false, true); // paciente com implante para falhar na validação
        
        Medico medicoSolicitante = new Medico("Dr. Roberto", "CRM-SP 123456", true);
        Medico medicoResponsavel = new Medico("Dra. Roberta", "CRM-SP 654321", true);

        // --- R7: Padrão Chain of Responsibility (Cadeia de Descontos) ---
        Desconto descontoConvenio = new DescontoConvenio();
        Desconto descontoIdoso = new DescontoIdoso();
        descontoConvenio.setProximoDesconto(descontoIdoso);
        descontoIdoso.setProximoDesconto(null);
        
        // --- R9: Padrão Facade ---
        SistemaLaudoFacade sistema = new SistemaLaudoFacade(descontoConvenio);

        // --- R6: Padrão Observer (Notificação) ---
        sistema.registrarObserver(new NotificadorEmail());
        

        // --- R2: Geração de ID Sequencial ---
        int proximoId = 1;

        // --- R3 e R5: Padrão Strategy (Tipos de Exame e Validações) ---
        System.out.println("\n=== Instanciação e Validação dos Exames ===");

        // Exame de Glicose (prioridade URGENTE)
        
        Exame exame1 = new Exame(new Date(), "Glicose",50.00, "130 mg/DL", paciente1, medicoSolicitante, Prioridade.URGENTE);
        exame1.setId(proximoId++);
        exame1.setValidador(new ValidadorGlicose());

        // Exame de Raio-X de Tórax (prioridade ROTINA)
        Exame exame2 = new Exame(new Date(), "Raio-X de Tórax",100.00, "Imagem sem alterações significativas.", paciente2, medicoSolicitante, Prioridade.ROTINA);
        exame2.setId(proximoId++);
        exame2.setValidador(new ValidadorRaioX());
        exame2.setMedicoResponsavel(medicoResponsavel);

        // Exame de Ressonância Magnética (prioridade ALTA)
        Exame exame3 = new Exame(new Date(), "Ressonância Magnética",200.00, "Laudo da RM...", paciente3, medicoSolicitante, Prioridade.URGENTE);
        exame3.setId(proximoId++);
        exame3.setValidador(new ValidadorRessonancia());
        exame3.setMedicoResponsavel(medicoResponsavel);

        // --- R8: Fila de Prioridade ---
        System.out.println("\n=== Adicionando Exames à Fila ===");
        // Adicionando exames fora de ordem para testar a fila de prioridade
        sistema.adicionarExameNaFila(exame2); // ROTINA
        sistema.adicionarExameNaFila(exame1); // URGENTE
        sistema.adicionarExameNaFila(exame3); // URGENTE

        // Processa a fila de exames
        System.out.println("\n=== Processando a Fila de Prioridade ===");
        sistema.processarFila();

        // --- R4: Padrão Bridge (Geração de Laudos em Diferentes Formatos) ---
        System.out.println("\n=== Demonstração do Padrão Bridge para Geração de Laudos ===");
        Laudo laudoGlicose = new LaudoSanguineo(exame1, medicoResponsavel, exame1.getResultado(), "Diagnóstico automático...");

        
        sistema.gerarLaudoEmFormato(laudoGlicose, new GeradorTexto());
        sistema.gerarLaudoEmFormato(laudoGlicose, new GeradorHTML());
        sistema.gerarLaudoEmFormato(laudoGlicose, new GeradorPDF());
    }
}
