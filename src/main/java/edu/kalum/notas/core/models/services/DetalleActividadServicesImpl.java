package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.dao.IDetalleActividadDao;
import edu.kalum.notas.core.models.entities.DetalleActividad;
import edu.kalum.notas.core.models.services.IDetalleActividadServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DetalleActividadServicesImpl implements IDetalleActividadServices {
    @Autowired
    private IDetalleActividadDao detalleActividadDao;

    public List<DetalleActividad> findAll() {
        return this.detalleActividadDao.findAll();
    }

    public Page<DetalleActividad> findAll(Pageable pageable) {
        return this.detalleActividadDao.findAll(pageable);
    }

    public DetalleActividad save(DetalleActividad detalleActividad) {
        return (DetalleActividad) this.detalleActividadDao.save(detalleActividad);
    }

    public DetalleActividad findById(String id) {
        return this.detalleActividadDao.findById(id).orElse(null);
    }

    public void delete(DetalleActividad detalleActividad) {
        this.detalleActividadDao.delete(detalleActividad);
    }

    public void deleteById(String id) {
        this.detalleActividadDao.deleteById(id);
    }
}