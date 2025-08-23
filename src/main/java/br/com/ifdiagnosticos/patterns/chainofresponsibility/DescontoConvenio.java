package br.com.ifdiagnosticos.patterns.chainofresponsibility;

import br.com.ifdiagnosticos.model.Exame;

public class DescontoConvenio extends Desconto {
    private static final double PERCENTUAL = 0.15; // 15%

    @Override
    public double aplicarDesconto(Exame exame) {
        if (exame.getPaciente().getConvenio() != null && !exame.getPaciente().getConvenio().isEmpty()) {
            double desconto = exame.getValor() * PERCENTUAL;
            System.out.printf("  -> Desconto aplicado: R$%.2f (Convênio)\n", desconto);
            return exame.getValor() - desconto;
        } else if (proximoDesconto != null) {
            return proximoDesconto.aplicarDesconto(exame);
        }
        return exame.getValor();
    }
}