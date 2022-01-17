package edu.kalum.notas.core.models.dao;

import edu.kalum.notas.core.models.entities.CarreraTecnica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarreraTecnicaDao extends JpaRepository<CarreraTecnica, String> {
    CarreraTecnica findByCodigoCarrera(String paramString);

    void deleteByCodigoCarrera(String paramString);
}
