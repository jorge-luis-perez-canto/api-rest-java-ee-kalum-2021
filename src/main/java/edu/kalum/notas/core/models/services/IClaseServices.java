package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.entities.Clase;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClaseServices {
    List<Clase> findAll();

    Page<Clase> findAll(Pageable paramPageable);

    Clase save(Clase paramClase);

    Clase findById(String paramString);

    void delete(Clase paramClase);

    void deleteById(String paramString);
}