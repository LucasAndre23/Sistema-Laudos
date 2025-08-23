package br.com.ifdiagnosticos.model;

import br.com.ifdiagnosticos.patterns.strategy.ValidadorExame;
import br.com.ifdiagnosticos.patterns.bridge.GeradorDeFormato;

public class Exame {
    private int id;
    private Paciente paciente;
    private Medico medicoSolicitante;
    private String tipo;
    private double valor;
    private Prioridade prioridade;
    private ValidadorExame validador;
    private String resultado;

    public Exame(Paciente paciente, Medico medicoSolicitante, String tipo, double valor, Prioridade prioridade) {
        this.paciente = paciente;
        this.medicoSolicitante = medicoSolicitante;
        this.tipo = tipo;
        this.valor = valor;
        this.prioridade = prioridade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Paciente getPaciente() { return paciente; }
    public Medico getMedicoSolicitante() { return medicoSolicitante; }
    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
    public Prioridade getPrioridade() { return prioridade; }
    public ValidadorExame getValidador() { return validador; }
    public void setValidador(ValidadorExame validador) { this.validador = validador; }
    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    public void validar() {
        if (this.validador != null) {
            this.validador.validar(this);
        }
    }
}
