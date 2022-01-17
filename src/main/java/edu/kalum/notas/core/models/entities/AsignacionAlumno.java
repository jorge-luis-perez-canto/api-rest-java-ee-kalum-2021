package edu.kalum.notas.core.models.entities;


import edu.kalum.notas.core.models.entities.Alumno;
import edu.kalum.notas.core.models.entities.Clase;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "asignacion_alumno")
@Entity
public class AsignacionAlumno {
    @Id
    @Column(name = "asignacion_id")
    private String asignacionId;

    @Column(name = "fecha_asignacion")
    private Date fechaAsignacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carne", referencedColumnName = "carne")
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clase_id", referencedColumnName = "clase_id")
    private Clase clase;

    public void setAsignacionId(String asignacionId) {
        this.asignacionId = asignacionId;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    protected boolean canEqual(Object other) {
        return other instanceof edu.kalum.notas.core.models.entities.AsignacionAlumno;
    }

    public String toString() {
        return "AsignacionAlumno(asignacionId=" + getAsignacionId() + ", fechaAsignacion=" + getFechaAsignacion() + ", alumno=" + getAlumno() + ", clase=" + getClase() + ")";
    }

    public AsignacionAlumno(String asignacionId, Date fechaAsignacion, Alumno alumno, Clase clase) {
        this.asignacionId = asignacionId;
        this.fechaAsignacion = fechaAsignacion;
        this.alumno = alumno;
        this.clase = clase;
    }

    public AsignacionAlumno() {
    }

    public String getAsignacionId() {
        return this.asignacionId;
    }

    public Date getFechaAsignacion() {
        return this.fechaAsignacion;
    }

    public Alumno getAlumno() {
        return this.alumno;
    }

    public Clase getClase() {
        return this.clase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsignacionAlumno that = (AsignacionAlumno) o;
        return Objects.equals(asignacionId, that.asignacionId) && Objects.equals(fechaAsignacion, that.fechaAsignacion) && Objects.equals(alumno, that.alumno) && Objects.equals(clase, that.clase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asignacionId, fechaAsignacion, alumno, clase);
    }
}