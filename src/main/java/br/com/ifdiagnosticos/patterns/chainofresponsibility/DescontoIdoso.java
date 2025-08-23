package br.com.ifdiagnosticos.patterns.chainofresponsibility;

import br.com.ifdiagnosticos.model.Exame;

public class DescontoIdoso extends Desconto {
    private static final double PERCENTUAL = 0.08; // 8%

    @Override
    public double aplicarDesconto(Exame exame) {
        if (exame.getPaciente().getIdade() > 60) {
            double desconto = exame.getValor() * PERCENTUAL;
            System.out.printf("  -> Desconto aplicado: R$%.2f (Idoso)\n", desconto);
            return exame.getValor() - desconto;
        } else if (proximoDesconto != null) {
            return proximoDesconto.aplicarDesconto(exame);
        }
        return exame.getValor();
    }
}