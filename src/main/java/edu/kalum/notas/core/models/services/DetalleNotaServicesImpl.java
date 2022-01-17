package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.dao.IDetalleNotaDao;
import edu.kalum.notas.core.models.entities.DetalleNota;
import edu.kalum.notas.core.models.services.IDetalleNotaServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DetalleNotaServicesImpl implements IDetalleNotaServices {
    @Autowired
    private IDetalleNotaDao detalleNotaDao;

    public List<DetalleNota> findAll() {
        return this.detalleNotaDao.findAll();
    }

    public Page<DetalleNota> findAll(Pageable pageable) {
        return this.detalleNotaDao.findAll(pageable);
    }

    public DetalleNota save(DetalleNota detalleNota) {
        return (DetalleNota) this.detalleNotaDao.save(detalleNota);
    }

    public DetalleNota findById(String id) {
        return this.detalleNotaDao.findById(id).orElse(null);
    }

    public void delete(DetalleNota detalleNota) {
        this.detalleNotaDao.delete(detalleNota);
    }

    public void deleteById(String id) {
        this.detalleNotaDao.deleteById(id);
    }
}
