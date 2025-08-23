package br.com.ifdiagnosticos.model;

public class Paciente {
    private String nome;
    private String convenio;
    private int idade;
    private String email;

    public Paciente(String nome, String convenio, int idade, String email) {
        this.nome = nome;
        this.convenio = convenio;
        this.idade = idade;
        this.email = email;
    }

    public String getNome() { return nome; }
    public String getConvenio() { return convenio; }
    public int getIdade() { return idade; }
    public String getEmail() { return email; }
}