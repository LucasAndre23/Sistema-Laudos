package br.com.ifdiagnosticos.patterns.factory;

import br.com.ifdiagnosticos.model.Medico;
import br.com.ifdiagnosticos.model.Paciente;

import java.util.List;

public interface ExameScenarioFactory {
    List<Paciente> getPacientesParaExames();
    Medico getMedicoSolicitante();
    Medico getMedicoResponsavel();
}
