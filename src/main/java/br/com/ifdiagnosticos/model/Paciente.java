package br.com.ifdiagnosticos.model;

public class Paciente {
    private String nome;
    private String convenio;
    private int idade;
    private String email;
    private boolean temMarcapasso; 
    private boolean temImplantesMetalicos;

    public Paciente(String nome, String convenio, int idade, String email, boolean temMarcapasso, boolean temImplantesMetalicos) {
        this.nome = nome;
        this.convenio = convenio;
        this.idade = idade;
        this.email = email;
        this.temMarcapasso = temMarcapasso;
        this.temImplantesMetalicos = temImplantesMetalicos;
    }

    public String getNome() { 
        return nome; 
    }
    public String getConvenio() { 
        return convenio; 
    }

    public int getIdade() { 
        return idade; 
    }

    public String getEmail() { 
        return email; 
    }

    public boolean temMarcapasso() {
        return temMarcapasso;
    }

   
    public boolean temImplantesMetalicos() {
        return temImplantesMetalicos;
    }
}