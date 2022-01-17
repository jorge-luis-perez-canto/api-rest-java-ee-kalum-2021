package edu.kalum.notas.core.models.entities;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.kalum.notas.core.models.entities.AsignacionAlumno;
import edu.kalum.notas.core.models.entities.CarreraTecnica;
import edu.kalum.notas.core.models.entities.Horario;
import edu.kalum.notas.core.models.entities.Instructor;
import edu.kalum.notas.core.models.entities.Salon;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor


@Table(name = "clase")
@Entity
public class Clase implements Serializable {
    @Id
    @Column(name = "clase_id")
    private String claseId;

    @Column(name = "ciclo")
    private int ciclo;

    @Column(name = "cupo_maximo")
    private int cupoMaximo;

    @Column(name = "cupo_minimo")
    private int cupoMinimo;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "clase", fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<AsignacionAlumno> asignaciones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_carrera", referencedColumnName = "codigo_carrera")
    private CarreraTecnica carreraTecnica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "horario_id", referencedColumnName = "horario_id")
    private Horario horario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", referencedColumnName = "instructor_id")
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salon_id", referencedColumnName = "salon_id")
    private Salon salon;

    public void setClaseId(String claseId) {
        this.claseId = claseId;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public void setCupoMinimo(int cupoMinimo) {
        this.cupoMinimo = cupoMinimo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setAsignaciones(List<AsignacionAlumno> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public void setCarreraTecnica(CarreraTecnica carreraTecnica) {
        this.carreraTecnica = carreraTecnica;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    protected boolean canEqual(Object other) {
        return other instanceof edu.kalum.notas.core.models.entities.Clase;
    }

    public String toString() {
        return "Clase(claseId=" + getClaseId() + ", ciclo=" + getCiclo() + ", cupoMaximo=" + getCupoMaximo() + ", cupoMinimo=" + getCupoMinimo() + ", descripcion=" + getDescripcion() + ", asignaciones=" + getAsignaciones() + ", carreraTecnica=" + getCarreraTecnica() + ", horario=" + getHorario() + ", instructor=" + getInstructor() + ", salon=" + getSalon() + ")";
    }

    public Clase(String claseId, int ciclo, int cupoMaximo, int cupoMinimo, String descripcion, List<AsignacionAlumno> asignaciones, CarreraTecnica carreraTecnica, Horario horario, Instructor instructor, Salon salon) {
        this.claseId = claseId;
        this.ciclo = ciclo;
        this.cupoMaximo = cupoMaximo;
        this.cupoMinimo = cupoMinimo;
        this.descripcion = descripcion;
        this.asignaciones = asignaciones;
        this.carreraTecnica = carreraTecnica;
        this.horario = horario;
        this.instructor = instructor;
        this.salon = salon;
    }

    public Clase() {
    }

    public String getClaseId() {
        return this.claseId;
    }

    public int getCiclo() {
        return this.ciclo;
    }

    public int getCupoMaximo() {
        return this.cupoMaximo;
    }

    public int getCupoMinimo() {
        return this.cupoMinimo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public List<AsignacionAlumno> getAsignaciones() {
        return this.asignaciones;
    }

    public CarreraTecnica getCarreraTecnica() {
        return this.carreraTecnica;
    }

    public Horario getHorario() {
        return this.horario;
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public Salon getSalon() {
        return this.salon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clase clase = (Clase) o;
        return ciclo == clase.ciclo && cupoMaximo == clase.cupoMaximo && cupoMinimo == clase.cupoMinimo && Objects.equals(claseId, clase.claseId) && Objects.equals(descripcion, clase.descripcion) && Objects.equals(asignaciones, clase.asignaciones) && Objects.equals(carreraTecnica, clase.carreraTecnica) && Objects.equals(horario, clase.horario) && Objects.equals(instructor, clase.instructor) && Objects.equals(salon, clase.salon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claseId, ciclo, cupoMaximo, cupoMinimo, descripcion, asignaciones, carreraTecnica, horario, instructor, salon);
    }
}