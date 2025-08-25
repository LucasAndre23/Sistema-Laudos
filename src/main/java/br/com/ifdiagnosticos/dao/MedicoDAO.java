package br.com.ifdiagnosticos.dao;

import br.com.ifdiagnosticos.model.Medico;
import java.util.List;

public interface MedicoDAO {
    List<Medico> findAll();
}
