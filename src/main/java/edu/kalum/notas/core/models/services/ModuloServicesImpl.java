package edu.kalum.notas.core.models.services;


import edu.kalum.notas.core.models.dao.IModuloDao;
import edu.kalum.notas.core.models.entities.Modulo;
import edu.kalum.notas.core.models.services.IModuloServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ModuloServicesImpl implements IModuloServices {
    @Autowired
    private IModuloDao moduloDao;

    public List<Modulo> findAll() {
        return this.moduloDao.findAll();
    }

    public Page<Modulo> findAll(Pageable pageable) {
        return this.moduloDao.findAll(pageable);
    }

    public Modulo save(Modulo modulo) {
        return (Modulo) this.moduloDao.save(modulo);
    }

    public Modulo findById(String id) {
        return this.moduloDao.findById(id).orElse(null);
    }

    public void delete(Modulo modulo) {
        this.moduloDao.delete(modulo);
    }

    public void deleteById(String id) {
        this.moduloDao.deleteById(id);
    }
}
