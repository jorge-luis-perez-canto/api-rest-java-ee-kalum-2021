package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.entities.Seminario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISeminarioServices {
    List<Seminario> findAll();

    Page<Seminario> findAll(Pageable paramPageable);

    Seminario save(Seminario paramSeminario);

    Seminario findById(String paramString);

    void delete(Seminario paramSeminario);

    void deleteById(String paramString);
}
