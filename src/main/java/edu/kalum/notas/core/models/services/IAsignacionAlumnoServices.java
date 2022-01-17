package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.entities.AsignacionAlumno;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAsignacionAlumnoServices {
    List<AsignacionAlumno> findAllByCarne(String paramString);

    List<AsignacionAlumno> findAll();

    Page<AsignacionAlumno> findAll(Pageable paramPageable);

    AsignacionAlumno save(AsignacionAlumno paramAsignacionAlumno);

    AsignacionAlumno findById(String paramString);

    void delete(AsignacionAlumno paramAsignacionAlumno);

    void deleteById(String paramString);
}
