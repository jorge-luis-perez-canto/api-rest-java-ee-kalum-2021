package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.entities.DetalleNota;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDetalleNotaServices {
    List<DetalleNota> findAll();

    Page<DetalleNota> findAll(Pageable paramPageable);

    DetalleNota save(DetalleNota paramDetalleNota);

    DetalleNota findById(String paramString);

    void delete(DetalleNota paramDetalleNota);

    void deleteById(String paramString);
}
