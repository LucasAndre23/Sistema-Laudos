package br.com.ifdiagnosticos.dao;

import br.com.ifdiagnosticos.model.Medico;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedicoCsvDAO implements MedicoDAO {
    private String csvFile;

    public MedicoCsvDAO(String csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public List<Medico> findAll() {
        List<Medico> medicos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String linha;
            br.readLine(); // Pular cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String nome = dados[0].replace("\"", "");
                String crm = dados[1].replace("\"", "");
                boolean ativo = Boolean.parseBoolean(dados[2]);
                medicos.add(new Medico(nome, crm, ativo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return medicos;
    }
}
