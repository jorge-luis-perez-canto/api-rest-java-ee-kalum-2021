package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.entities.CarreraTecnica;

import java.util.List;

public interface ICarreraTecnicaServices {
    List<CarreraTecnica> findAll();

    CarreraTecnica findByCodigoCarrera(String paramString);

    CarreraTecnica save(CarreraTecnica paramCarreraTecnica);

    void delete(CarreraTecnica paramCarreraTecnica);

    void deleteByCodigoCarrera(String paramString);
}