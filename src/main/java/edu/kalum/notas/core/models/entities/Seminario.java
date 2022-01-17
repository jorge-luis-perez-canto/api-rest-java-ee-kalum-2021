package edu.kalum.notas.core.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.kalum.notas.core.models.entities.DetalleActividad;
import edu.kalum.notas.core.models.entities.Modulo;

import java.io.Serializable;
import java.util.Date;
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

@Table(name = "seminario")
@Entity
public class Seminario implements Serializable {
    @Id
    @Column(name = "seminario_id")
    private String seminarioId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modulo_id", referencedColumnName = "modulo_id")
    private Modulo modulo;

    @Column(name = "nombre_seminario")
    private String nombreSeminario;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @OneToMany(mappedBy = "seminario", fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<DetalleActividad> detalleActividad;

    public Seminario() {
    }

    public Seminario(String seminarioId, Modulo modulo, String nombreSeminario, Date fechaInicio, Date fechaFin, List<DetalleActividad> detalleActividad) {
        this.seminarioId = seminarioId;
        this.modulo = modulo;
        this.nombreSeminario = nombreSeminario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.detalleActividad = detalleActividad;
    }

    public void setSeminarioId(String seminarioId) {
        this.seminarioId = seminarioId;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public void setNombreSeminario(String nombreSeminario) {
        this.nombreSeminario = nombreSeminario;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setDetalleActividad(List<DetalleActividad> detalleActividad) {
        this.detalleActividad = detalleActividad;
    }

    protected boolean canEqual(Object other) {
        return other instanceof edu.kalum.notas.core.models.entities.Seminario;
    }

    public String toString() {
        return "Seminario(seminarioId=" + getSeminarioId() + ", modulo=" + getModulo() + ", nombreSeminario=" + getNombreSeminario() + ", fechaInicio=" + getFechaInicio() + ", fechaFin=" + getFechaFin() + ", detalleActividad=" + getDetalleActividad() + ")";
    }

    public String getSeminarioId() {
        return this.seminarioId;
    }

    public Modulo getModulo() {
        return this.modulo;
    }

    public String getNombreSeminario() {
        return this.nombreSeminario;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public List<DetalleActividad> getDetalleActividad() {
        return this.detalleActividad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seminario seminario = (Seminario) o;
        return Objects.equals(seminarioId, seminario.seminarioId) && Objects.equals(modulo, seminario.modulo) && Objects.equals(nombreSeminario, seminario.nombreSeminario) && Objects.equals(fechaInicio, seminario.fechaInicio) && Objects.equals(fechaFin, seminario.fechaFin) && Objects.equals(detalleActividad, seminario.detalleActividad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seminarioId, modulo, nombreSeminario, fechaInicio, fechaFin, detalleActividad);
    }
}