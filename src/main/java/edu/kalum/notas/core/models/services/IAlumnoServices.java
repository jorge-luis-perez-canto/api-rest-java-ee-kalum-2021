package edu.kalum.notas.core.models.services;

import edu.kalum.notas.core.models.entities.Alumno;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
@SpringBootApplication // nuevo

public interface IAlumnoServices {
    List<Alumno> findAll();

    Alumno findByCarne(String paramString);

    Alumno findByEmail(String paramString);

    Alumno save(Alumno paramAlumno);

    void delete(Alumno paramAlumno);

    void deleteByCarne(String paramString);
}
