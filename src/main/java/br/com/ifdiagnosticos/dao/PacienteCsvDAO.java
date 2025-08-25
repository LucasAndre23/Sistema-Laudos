package br.com.ifdiagnosticos.dao;

import br.com.ifdiagnosticos.model.Paciente;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PacienteCsvDAO implements PacienteDAO {
    private String csvFile;

    public PacienteCsvDAO(String csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public List<Paciente> findAll() {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String linha;
            br.readLine(); // Pular cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String nome = dados[0].replace("\"", "");
                String plano = dados[1].replace("\"", "");
                int idade = Integer.parseInt(dados[2]);
                String email = dados[3].replace("\"", "");
                boolean hasConvenio = Boolean.parseBoolean(dados[4]);
                boolean hasImplante = Boolean.parseBoolean(dados[5]);
                pacientes.add(new Paciente(nome, plano, idade, email, hasConvenio, hasImplante));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pacientes;
    }
}
