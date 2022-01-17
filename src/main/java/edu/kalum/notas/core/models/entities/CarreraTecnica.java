package edu.kalum.notas.core.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.kalum.notas.core.models.entities.Clase;
import edu.kalum.notas.core.models.entities.Modulo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "carrera_tecnica")
@Entity
public class CarreraTecnica implements Serializable {
    @Id
    @Column(name = "codigo_carrera")
    private String codigoCarrera;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "carreraTecnica", fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Clase> clase;

    @OneToMany(mappedBy = "carreraTecnica", fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Modulo> modulo;

    public CarreraTecnica() {
    }

    public CarreraTecnica(String codigoCarrera, String nombre, List<Clase> clase, List<Modulo> modulo) {
        this.codigoCarrera = codigoCarrera;
        this.nombre = nombre;
        this.clase = clase;
        this.modulo = modulo;
    }

    public void setCodigoCarrera(String codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setClase(List<Clase> clase) {
        this.clase = clase;
    }

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setModulo(List<Modulo> modulo) {
        this.modulo = modulo;
    }

    protected boolean canEqual(Object other) {
        return other instanceof edu.kalum.notas.core.models.entities.CarreraTecnica;
    }

    public String toString() {
        return "CarreraTecnica(codigoCarrera=" + getCodigoCarrera() + ", nombre=" + getNombre() + ", clase=" + getClase() + ", modulo=" + getModulo() + ")";
    }

    public String getCodigoCarrera() {
        return this.codigoCarrera;
    }

    public String getNombre() {
        return this.nombre;
    }

    public List<Clase> getClase() {
        return this.clase;
    }

    public List<Modulo> getModulo() {
        return this.modulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarreraTecnica that = (CarreraTecnica) o;
        return Objects.equals(codigoCarrera, that.codigoCarrera) && Objects.equals(nombre, that.nombre) && Objects.equals(clase, that.clase) && Objects.equals(modulo, that.modulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoCarrera, nombre, clase, modulo);
    }
}