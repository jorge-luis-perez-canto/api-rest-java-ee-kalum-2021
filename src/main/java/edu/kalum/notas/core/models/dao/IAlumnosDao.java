package edu.kalum.notas.core.models.dao;

import edu.kalum.notas.core.models.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlumnosDao extends JpaRepository<Alumno, String> {
    Alumno findByCarne(String paramString);

    Alumno findByEmail(String paramString);

    void deleteByCarne(String paramString);
}
