package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.entities.Modulo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IModuloServices {
    List<Modulo> findAll();

    Page<Modulo> findAll(Pageable paramPageable);

    Modulo save(Modulo paramModulo);

    Modulo findById(String paramString);

    void delete(Modulo paramModulo);

    void deleteById(String paramString);
}