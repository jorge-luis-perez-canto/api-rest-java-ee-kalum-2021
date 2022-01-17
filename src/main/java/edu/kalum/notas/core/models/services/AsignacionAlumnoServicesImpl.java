package edu.kalum.notas.core.models.services;


import edu.kalum.notas.core.models.dao.IAsignacionAlumnoDao;
import edu.kalum.notas.core.models.entities.AsignacionAlumno;
import edu.kalum.notas.core.models.services.IAsignacionAlumnoServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AsignacionAlumnoServicesImpl implements IAsignacionAlumnoServices {
    @Autowired
    private IAsignacionAlumnoDao asignacionAlumnoDao;

    public List<AsignacionAlumno> findAllByCarne(String carne) {
        return this.asignacionAlumnoDao.findAllByCarne(carne);
    }

    public List<AsignacionAlumno> findAll() {
        return this.asignacionAlumnoDao.findAll();
    }

    public Page<AsignacionAlumno> findAll(Pageable pageable) {
        return this.asignacionAlumnoDao.findAll(pageable);
    }

    public AsignacionAlumno save(AsignacionAlumno asignacionAlumno) {
        return (AsignacionAlumno) this.asignacionAlumnoDao.save(asignacionAlumno);
    }

    public AsignacionAlumno findById(String id) {
        return this.asignacionAlumnoDao.findById(id).orElse(null);
    }

    public void delete(AsignacionAlumno asignacionAlumno) {
        this.asignacionAlumnoDao.delete(asignacionAlumno);
    }

    public void deleteById(String id) {
        this.asignacionAlumnoDao.deleteById(id);
    }
}