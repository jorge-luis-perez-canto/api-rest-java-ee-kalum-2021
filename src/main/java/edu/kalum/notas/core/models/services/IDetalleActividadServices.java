package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.entities.DetalleActividad;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDetalleActividadServices {
    List<DetalleActividad> findAll();

    Page<DetalleActividad> findAll(Pageable paramPageable);

    DetalleActividad save(DetalleActividad paramDetalleActividad);

    DetalleActividad findById(String paramString);

    void delete(DetalleActividad paramDetalleActividad);

    void deleteById(String paramString);
}
