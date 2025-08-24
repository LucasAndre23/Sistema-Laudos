package br.com.ifdiagnosticos.patterns.strategy;

import br.com.ifdiagnosticos.model.Exame;

public class ValidadorRessonancia implements ValidadorExame {

    
    @Override
    public void validar(Exame exame) {
        System.out.println("Validando exame de Ressonância Magnética...");

        // Requisito: A descrição do laudo não pode ser vazia.
        if (exame.getResultado() == null || exame.getResultado().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O laudo da Ressonância Magnética não pode ser vazio.");
        }

        // Requisito: O paciente não pode ter marcapasso ou implantes metálicos.
       
        if (exame.getPaciente().temMarcapasso() || exame.getPaciente().temImplantesMetalicos()) {
            throw new IllegalArgumentException("Erro: Paciente com marcapasso ou implantes metálicos não pode realizar o exame.");
        }
        
        // Requisito: Deve ter a assinatura do radiologista responsável.
       
        if (exame.getMedicoResponsavel() == null || !exame.getMedicoResponsavel().getAssinatura()) {
            throw new IllegalArgumentException("Erro: O laudo da Ressonância Magnética deve ter a assinatura do radiologista.");
        }
        
        // Requisito: Indicar o protocolo do exame.
      
        if (exame.getProtocolo() == null || exame.getProtocolo().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O protocolo do exame de Ressonância Magnética deve ser indicado.");
        }

        // Requisito: Se utilizou contraste, deve indicar o contraste e a dosagem.
       
        if (exame.utilizouContraste() && (exame.getContraste() == null || exame.getDosagem() == 0)) {
            throw new IllegalArgumentException("Erro: Informações de contraste e dosagem são obrigatórias.");
        }

        System.out.println("Validação da Ressonância Magnética concluída com sucesso.");
    }
}