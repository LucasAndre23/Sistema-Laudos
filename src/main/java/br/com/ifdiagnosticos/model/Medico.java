package br.com.ifdiagnosticos.model;

public class Medico {
    private String nome;
    private String crm;
    private boolean temAssinatura;

    public Medico(String nome, String crm, boolean temAssinatura) {
        this.nome = nome;
        this.crm = crm;
        this.temAssinatura = temAssinatura;
    }

    public String getNome() { 
        return nome; 
    }
    public String getCrm() { 
        return crm; 
    }
     public boolean getAssinatura() {
        return temAssinatura;
    }
}
