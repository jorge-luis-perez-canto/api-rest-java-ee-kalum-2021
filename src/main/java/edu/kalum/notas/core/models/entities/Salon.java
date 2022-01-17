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
@Table(name = "salon")
public class Salon implements Serializable {
    @Id
    @Column(name = "salon_id")
    private String salonId;

    @Column(name = "capacidad")
    private Integer capacidad;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "nombre_salon")
    private String nombreSalon;

    @OneToMany(mappedBy = "salon", fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Clase> clases;

    public void setSalonId(String salonId) {
        this.salonId = salonId;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombreSalon(String nombreSalon) {
        this.nombreSalon = nombreSalon;
    }

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    protected boolean canEqual(Object other) {
        return other instanceof edu.kalum.notas.core.models.entities.Salon;
    }

    public String toString() {
        return "Salon(salonId=" + getSalonId() + ", capacidad=" + getCapacidad() + ", descripcion=" + getDescripcion() + ", nombreSalon=" + getNombreSalon() + ", clases=" + getClases() + ")";
    }

    public Salon(String salonId, Integer capacidad, String descripcion, String nombreSalon, List<Clase> clases) {
        this.salonId = salonId;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.nombreSalon = nombreSalon;
        this.clases = clases;
    }

    public Salon() {
    }

    public String getSalonId() {
        return this.salonId;
    }

    public Integer getCapacidad() {
        return this.capacidad;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getNombreSalon() {
        return this.nombreSalon;
    }

    public List<Clase> getClases() {
        return this.clases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salon salon = (Salon) o;
        return Objects.equals(salonId, salon.salonId) && Objects.equals(capacidad, salon.capacidad) && Objects.equals(descripcion, salon.descripcion) && Objects.equals(nombreSalon, salon.nombreSalon) && Objects.equals(clases, salon.clases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salonId, capacidad, descripcion, nombreSalon, clases);
    }
}