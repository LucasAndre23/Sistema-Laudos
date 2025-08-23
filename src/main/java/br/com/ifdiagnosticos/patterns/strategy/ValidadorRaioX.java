package br.com.ifdiagnosticos.patterns.strategy;

import br.com.ifdiagnosticos.model.Exame;

public class ValidadorRaioX implements ValidadorExame {
    @Override
    public void validar(Exame exame) {
        System.out.println("Validando exame de Raio-X...");
        
        // Regra: o laudo não pode ser vazio
        if (exame.getResultado() == null || exame.getResultado().trim().isEmpty()) {
            System.err.println("  -> Erro na validação: O laudo do Raio-X não pode ser vazio.");
            throw new IllegalArgumentException("Laudo do Raio-X não pode ser vazio.");
        }
        
        // Regra: verificar se a assinatura do radiologista existe
        // Na simulação, vamos verificar se o médico solicitante é um radiologista
        if (!exame.getMedicoSolicitante().getCrm().contains("Radiologista")) {
             System.err.println("  -> Aviso na validação: Assinatura do radiologista não encontrada.");
        }
        
        System.out.println("  -> Validação de Raio-X concluída com sucesso.");
    }
}
