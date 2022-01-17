package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.dao.ISeminarioDao;
import edu.kalum.notas.core.models.entities.Seminario;
import edu.kalum.notas.core.models.services.ISeminarioServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SeminarioServicesImpl implements ISeminarioServices {
    @Autowired
    private ISeminarioDao seminarioDao;

    public List<Seminario> findAll() {
        return this.seminarioDao.findAll();
    }

    public Page<Seminario> findAll(Pageable pageable) {
        return this.seminarioDao.findAll(pageable);
    }

    public Seminario save(Seminario seminario) {
        return (Seminario) this.seminarioDao.save(seminario);
    }

    public Seminario findById(String id) {
        return this.seminarioDao.findById(id).orElse(null);
    }

    public void delete(Seminario seminario) {
        this.seminarioDao.delete(seminario);
    }

    public void deleteById(String id) {
        this.seminarioDao.deleteById(id);
    }
}