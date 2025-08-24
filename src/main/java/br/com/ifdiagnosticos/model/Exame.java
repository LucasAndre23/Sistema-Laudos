package br.com.ifdiagnosticos.model;

import br.com.ifdiagnosticos.model.Prioridade;
import br.com.ifdiagnosticos.patterns.strategy.ValidadorExame;

import java.util.Date;

public class Exame {
    private int id;
    private Date data;
    private String tipo;
    private double valor;
    private String resultado;
    private Prioridade prioridade;

    private Paciente paciente;
    private Medico medicoSolicitante;
    private Medico medicoResponsavel;

    private ValidadorExame validador;

    // Atributos específicos para exames como Ressonância Magnética
    private String protocolo;
    private boolean utilizouContraste;
    private String contraste;
    private double dosagem;

    
    public Exame(Date data, String tipo,double valor, String resultado, Paciente paciente, Medico medicoSolicitante, Prioridade prioridade) {
        this.data = data;
        this.tipo = tipo;
        this.valor = valor;
        this.resultado = resultado;
        this.paciente = paciente;
        this.medicoSolicitante = medicoSolicitante;
        this.prioridade = prioridade;
    }

    // --- Getters e Setters ---
    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }
    public Date getData() { 
        return data; 
    }
    public String getTipo() { 
        return tipo; 
    }
    public double getValor() { 
        return valor; 
    }
    public String getResultado() { 
        return resultado; 
    }
    public void setResultado(String resultado) { 
        this.resultado = resultado; 
    }
    public Prioridade getPrioridade() { 
        return prioridade; 
    }

    public Paciente getPaciente() { 
        return paciente; 
    }
    public Medico getMedicoSolicitante() { 
        return medicoSolicitante; 
    }
    public Medico getMedicoResponsavel() { 
        return medicoResponsavel; 
    }
    public void setMedicoResponsavel(Medico medicoResponsavel) { 
        this.medicoResponsavel = medicoResponsavel; 
    }

    // --- Métodos para o padrão Strategy ---
    public ValidadorExame getValidador() { 
        return validador; 
    }
    public void setValidador(ValidadorExame validador) { 
        this.validador = validador; 
    }
    
    public void validar() {
        if (this.validador != null) {
            this.validador.validar(this);
        }
    }

    // --- Métodos para os atributos específicos de Ressonância Magnética ---
    public String getProtocolo() { 
        return protocolo; 
    }
    public void setProtocolo(String protocolo) { 
        this.protocolo = protocolo; 
    }
    
    public boolean utilizouContraste() { 
        return utilizouContraste; 
    }
    public void setUtilizouContraste(boolean utilizouContraste) { 
        this.utilizouContraste = utilizouContraste; 
    }
    
    public String getContraste() { 
        return contraste; 
    }
    public void setContraste(String contraste) { 
        this.contraste = contraste; 
    }
    
    public double getDosagem() { 
        return dosagem; 
    }
    public void setDosagem(double dosagem) { 
        this.dosagem = dosagem; 
    }
}