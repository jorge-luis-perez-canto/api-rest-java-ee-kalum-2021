package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.dao.IAlumnosDao;
import edu.kalum.notas.core.models.entities.Alumno;
import edu.kalum.notas.core.models.services.IAlumnoServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServicesImpl implements IAlumnoServices {
    @Autowired
    private IAlumnosDao alumnoDao;

    public List<Alumno> findAll() {
        return this.alumnoDao.findAll();
    }

    public Alumno findByCarne(String carne) {
        return this.alumnoDao.findByCarne(carne);
    }

    public Alumno findByEmail(String email) {
        return this.alumnoDao.findByEmail(email);
    }

    public Alumno save(Alumno alumno) {
        return (Alumno) this.alumnoDao.save(alumno);
    }

    public void delete(Alumno alumno) {
        this.alumnoDao.delete(alumno);
    }

    public void deleteByCarne(String carne) {
        this.alumnoDao.deleteByCarne(carne);
    }
}
