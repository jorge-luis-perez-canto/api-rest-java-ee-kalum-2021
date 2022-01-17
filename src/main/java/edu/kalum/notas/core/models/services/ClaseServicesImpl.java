package edu.kalum.notas.core.models.services;


import edu.kalum.notas.core.models.dao.IClaseDao;
import edu.kalum.notas.core.models.entities.Clase;
import edu.kalum.notas.core.models.services.IClaseServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClaseServicesImpl implements IClaseServices {
    @Autowired
    private IClaseDao claseDao;

    public List<Clase> findAll() {
        return this.claseDao.findAll();
    }

    public Page<Clase> findAll(Pageable pageable) {
        return this.claseDao.findAll(pageable);
    }

    public Clase save(Clase clase) {
        return (Clase) this.claseDao.save(clase);
    }

    public Clase findById(String id) {
        return this.claseDao.findById(id).orElse(null);
    }

    public void delete(Clase clase) {
        this.claseDao.delete(clase);
    }

    public void deleteById(String id) {
        this.claseDao.deleteById(id);
    }
}