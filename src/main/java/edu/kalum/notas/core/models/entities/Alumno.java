package edu.kalum.notas.core.models.entities;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.kalum.notas.core.models.entities.AsignacionAlumno;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

//@NoArgsConstructor
//@AllArgsConstructor
//@Data

@Table(name = "alumno")
@Entity
public class Alumno implements Serializable {
    @Id
    @Column(name = "carne")
    @NotEmpty(message = "Es necesario asignar un numero de carné")
    private String carne;

    @Column(name = "no_expediente")
    @NotEmpty(message = "Es necesario asignar un numero de expediente.")
    private String noExpendiente;

    @Column(name = "apellidos")
    @NotEmpty(message = "El campo de apellidos debe ser llenado.")
    private String apellidos;

    @Column(name = "nombres")
    @NotEmpty(message = "El campo de nombres debe ser llenado.")
    private String nombres;

    @Column(name = "email")
    @Email(message = "Debe de ingresar un correo valido.")
    private String email;

    @OneToMany(mappedBy = "alumno", fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<AsignacionAlumno> asignacionAlumnos;

    public Alumno() {
    }

    public Alumno(String carne, String noExpendiente, String apellidos, String nombres, String email, List<AsignacionAlumno> asignacionAlumnos) {
        this.carne = carne;
        this.noExpendiente = noExpendiente;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.email = email;
        this.asignacionAlumnos = asignacionAlumnos;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public void setNoExpendiente(String noExpendiente) {
        this.noExpendiente = noExpendiente;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setAsignacionAlumnos(List<AsignacionAlumno> asignacionAlumnos) {
        this.asignacionAlumnos = asignacionAlumnos;
    }

    protected boolean canEqual(Object other) {
        return other instanceof edu.kalum.notas.core.models.entities.Alumno;
    }

    public String toString() {
        return "Alumno(carne=" + getCarne() + ", noExpendiente=" + getNoExpendiente() + ", apellidos=" + getApellidos() + ", nombres=" + getNombres() + ", email=" + getEmail() + ", asignacionAlumnos=" + getAsignacionAlumnos() + ")";
    }

    public String getCarne() {
        return this.carne;
    }

    public String getNoExpendiente() {
        return this.noExpendiente;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getNombres() {
        return this.nombres;
    }

    public String getEmail() {
        return this.email;
    }

    public List<AsignacionAlumno> getAsignacionAlumnos() {
        return this.asignacionAlumnos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(carne, alumno.carne) && Objects.equals(noExpendiente, alumno.noExpendiente) && Objects.equals(apellidos, alumno.apellidos) && Objects.equals(nombres, alumno.nombres) && Objects.equals(email, alumno.email) && Objects.equals(asignacionAlumnos, alumno.asignacionAlumnos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carne, noExpendiente, apellidos, nombres, email, asignacionAlumnos);
    }
}
