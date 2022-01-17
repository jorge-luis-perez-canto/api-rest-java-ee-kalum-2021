package edu.kalum.notas.core.models.dao;

import edu.kalum.notas.core.models.entities.AsignacionAlumno;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan

public interface IAsignacionAlumnoDao extends JpaRepository<AsignacionAlumno, String> {
    @Query("select a from AsignacionAlumno a where a.alumno.carne = ?1")
    List<AsignacionAlumno> findAllByCarne(String paramString);
}
