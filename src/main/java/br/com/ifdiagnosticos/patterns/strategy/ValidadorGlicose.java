package br.com.ifdiagnosticos.patterns.strategy;

import br.com.ifdiagnosticos.model.Exame;

public class ValidadorGlicose implements ValidadorExame {
    @Override
    public void validar(Exame exame) {
        System.out.println("Validando exame de glicose...");
        String resultado = exame.getResultado();
        try {
            double valor = Double.parseDouble(resultado.split(" ")[0]);
            String diagnostico = "Normal";
            if (valor < 60) diagnostico = "Hipoglicemia";
            else if (valor >= 100 && valor <= 125) diagnostico = "Intolerante";
            else if (valor > 125) diagnostico = "Diabetes";
            System.out.println("  -> Diagnóstico automático: " + diagnostico);
        } catch (Exception e) {
            System.err.println("  -> Erro na validação: valor inválido.");
        }
    }
}
