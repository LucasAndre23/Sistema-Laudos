package br.com.ifdiagnosticos.dao;

import br.com.ifdiagnosticos.model.Paciente;
import java.util.List;

public interface PacienteDAO {
    List<Paciente> findAll();
}
