package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.dao.ICarreraTecnicaDao;
import edu.kalum.notas.core.models.entities.CarreraTecnica;
import edu.kalum.notas.core.models.services.ICarreraTecnicaServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarreraTecnicaServicesImpl implements ICarreraTecnicaServices {
    @Autowired
    private ICarreraTecnicaDao carreraTecnicaDao;

    public List<CarreraTecnica> findAll() {
        return this.carreraTecnicaDao.findAll();
    }

    public CarreraTecnica findByCodigoCarrera(String codigoCarrera) {
        return this.carreraTecnicaDao.findByCodigoCarrera(codigoCarrera);
    }

    public CarreraTecnica save(CarreraTecnica carreraTecnica) {
        return (CarreraTecnica) this.carreraTecnicaDao.save(carreraTecnica);
    }

    public void delete(CarreraTecnica carreraTecnica) {
        this.carreraTecnicaDao.delete(carreraTecnica);
    }

    public void deleteByCodigoCarrera(String codigoCarrera) {
        this.carreraTecnicaDao.findByCodigoCarrera(codigoCarrera);
    }
}
