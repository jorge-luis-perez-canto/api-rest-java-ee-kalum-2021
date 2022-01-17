package edu.kalum.notas.core.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.kalum.notas.core.models.entities.Clase;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "instructor")
public class Instructor implements Serializable {
    @Id
    @Column(name = "instructor_id")
    private String instructorId;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "estatus")
    private String estatus;

    @Column(name = "foto")
    private String foto;

    @Column(name = "comentario")
    private String comentario;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Clase> clases;

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    protected boolean canEqual(Object other) {
        return other instanceof edu.kalum.notas.core.models.entities.Instructor;
    }

    public String toString() {
        return "Instructor(instructorId=" + getInstructorId() + ", apellidos=" + getApellidos() + ", nombres=" + getNombres() + ", direccion=" + getDireccion() + ", telefono=" + getTelefono() + ", estatus=" + getEstatus() + ", foto=" + getFoto() + ", comentario=" + getComentario() + ", clases=" + getClases() + ")";
    }

    public Instructor(String instructorId, String apellidos, String nombres, String direccion, String telefono, String estatus, String foto, String comentario, List<Clase> clases) {
        this.instructorId = instructorId;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estatus = estatus;
        this.foto = foto;
        this.comentario = comentario;
        this.clases = clases;
    }

    public Instructor() {
    }

    public String getInstructorId() {
        return this.instructorId;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getNombres() {
        return this.nombres;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public String getFoto() {
        return this.foto;
    }

    public String getComentario() {
        return this.comentario;
    }

    public List<Clase> getClases() {
        return this.clases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(instructorId, that.instructorId) && Objects.equals(apellidos, that.apellidos) && Objects.equals(nombres, that.nombres) && Objects.equals(direccion, that.direccion) && Objects.equals(telefono, that.telefono) && Objects.equals(estatus, that.estatus) && Objects.equals(foto, that.foto) && Objects.equals(comentario, that.comentario) && Objects.equals(clases, that.clases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructorId, apellidos, nombres, direccion, telefono, estatus, foto, comentario, clases);
    }
}
