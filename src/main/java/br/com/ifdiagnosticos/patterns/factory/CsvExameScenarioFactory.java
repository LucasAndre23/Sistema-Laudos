package br.com.ifdiagnosticos.patterns.factory;

import br.com.ifdiagnosticos.dao.MedicoCsvDAO;
import br.com.ifdiagnosticos.dao.MedicoDAO;
import br.com.ifdiagnosticos.dao.PacienteCsvDAO;
import br.com.ifdiagnosticos.dao.PacienteDAO;
import br.com.ifdiagnosticos.model.Medico;
import br.com.ifdiagnosticos.model.Paciente;

import java.util.List;

public class CsvExameScenarioFactory implements ExameScenarioFactory {
    
    
    private final List<Paciente> allPacientes;
    private final List<Medico> allMedicos;
    private final String pacientesCsvPath;
    private final String medicosCsvPath;

    public CsvExameScenarioFactory(String pacientesCsvPath, String medicosCsvPath) {
        this.pacientesCsvPath = pacientesCsvPath;
        this.medicosCsvPath = medicosCsvPath;
        
        validateConstructorParameters(pacientesCsvPath, medicosCsvPath);
        
        PacienteDAO pacienteDAO = createPacienteDAO(pacientesCsvPath);
        MedicoDAO medicoDAO = createMedicoDAO(medicosCsvPath);
        
        this.allPacientes = loadPacientes(pacienteDAO);
        this.allMedicos = loadMedicos(medicoDAO);
        
        validateLoadedData();
    }
    
    protected PacienteDAO createPacienteDAO(String csvPath) {
        return new PacienteCsvDAO(csvPath);
    }
    
    protected MedicoDAO createMedicoDAO(String csvPath) {
        return new MedicoCsvDAO(csvPath);
    }
    
    @Override
    public List<Paciente> getPacientesParaExames() {
        if (allPacientes.isEmpty()) {
            throw new IllegalStateException(
                String.format("Não há pacientes disponíveis no arquivo: %s", pacientesCsvPath)
            );
        }
        
        return List.copyOf(allPacientes);
    }

    @Override
    public Medico getMedicoSolicitante() {
        if (allMedicos.isEmpty()) {
            throw new IllegalStateException(
                String.format("Não há médicos disponíveis no arquivo: %s", medicosCsvPath)
            );
        }
        
        Medico medicoSolicitante = allMedicos.get(0);
        return medicoSolicitante;
    }

    @Override
    public Medico getMedicoResponsavel() {
        if (allMedicos.size() < 2) {
            throw new IllegalStateException(
                String.format("São necessários pelo menos 2 médicos no arquivo: %s (encontrados: %d)", 
                             medicosCsvPath, allMedicos.size())
            );
        }
        
        Medico medicoResponsavel = allMedicos.get(1);
        return medicoResponsavel;
    }
    
    // validação e leitura de dados
    
    public String getDataSummary() {
        return String.format(
            "Dados carregados - Pacientes: %d (arquivo: %s), Médicos: %d (arquivo: %s)",
            allPacientes.size(), pacientesCsvPath, allMedicos.size(), medicosCsvPath
        );
    }
       
    private void validateConstructorParameters(String pacientesCsvPath, String medicosCsvPath) {
        if (pacientesCsvPath == null || pacientesCsvPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Caminho do arquivo de pacientes não pode ser nulo ou vazio");
        }
        
        if (medicosCsvPath == null || medicosCsvPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Caminho do arquivo de médicos não pode ser nulo ou vazio");
        }
    }
    
    private List<Paciente> loadPacientes(PacienteDAO pacienteDAO) {
        try {
            return pacienteDAO.findAll();
        } catch (Exception e) {
            throw new IllegalStateException(
                String.format("Erro ao carregar pacientes do arquivo: %s - %s", 
                             pacientesCsvPath, e.getMessage()), e
            );
        }
    }
    
    private List<Medico> loadMedicos(MedicoDAO medicoDAO) {
        try {
            return medicoDAO.findAll();
        } catch (Exception e) {
            throw new IllegalStateException(
                String.format("Erro ao carregar médicos do arquivo: %s - %s", 
                             medicosCsvPath, e.getMessage()), e
            );
        }
    }
    
    private void validateLoadedData() {
        if (allPacientes == null) {
            throw new IllegalStateException("Lista de pacientes não deve ser nula");
        }
        
        if (allMedicos == null) {
            throw new IllegalStateException("Lista de médicos não deve ser nula");
        }
    }
}