package br.com.ifdiagnosticos.patterns.chainofresponsibility;

import br.com.ifdiagnosticos.model.Exame;

public abstract class Desconto {
    protected Desconto proximoDesconto;

    public void setProximoDesconto(Desconto proximo) {
        this.proximoDesconto = proximo;
    }

    public abstract double aplicarDesconto(Exame exame);
}