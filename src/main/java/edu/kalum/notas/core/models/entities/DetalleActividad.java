package edu.kalum.notas.core.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.kalum.notas.core.models.entities.DetalleNota;
import edu.kalum.notas.core.models.entities.Seminario;

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

@Table(name = "detalle_actividad")
@Entity
public class DetalleActividad implements Serializable {
    @Id
    @Column(name = "detalle_actividad_id")
    private String detalleActividadId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seminario_id", referencedColumnName = "seminario_id")
    private Seminario seminario;

    @Column(name = "nombre_actividad")
    private String nombreActividad;

    @Column(name = "nota_actividad")
    private Integer notaActividad;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_entrega")
    private Date fechaEntrega;

    @Column(name = "fecha_postergacion")
    private Date fechaPostergacion;

    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "detalleActividad", fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<DetalleNota> detalleNota;

    public DetalleActividad() {
    }

    public DetalleActividad(String detalleActividadId, Seminario seminario, String nombreActividad, Integer notaActividad, Date fechaCreacion, Date fechaEntrega, Date fechaPostergacion, String estado, List<DetalleNota> detalleNota) {
        this.detalleActividadId = detalleActividadId;
        this.seminario = seminario;
        this.nombreActividad = nombreActividad;
        this.notaActividad = notaActividad;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.fechaPostergacion = fechaPostergacion;
        this.estado = estado;
        this.detalleNota = detalleNota;
    }

    public void setDetalleActividadId(String detalleActividadId) {
        this.detalleActividadId = detalleActividadId;
    }

    public void setSeminario(Seminario seminario) {
        this.seminario = seminario;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public void setNotaActividad(Integer notaActividad) {
        this.notaActividad = notaActividad;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setFechaPostergacion(Date fechaPostergacion) {
        this.fechaPostergacion = fechaPostergacion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setDetalleNota(List<DetalleNota> detalleNota) {
        this.detalleNota = detalleNota;
    }

    protected boolean canEqual(Object other) {
        return other instanceof edu.kalum.notas.core.models.entities.DetalleActividad;
    }

    public String toString() {
        return "DetalleActividad(detalleActividadId=" + getDetalleActividadId() + ", seminario=" + getSeminario() + ", nombreActividad=" + getNombreActividad() + ", notaActividad=" + getNotaActividad() + ", fechaCreacion=" + getFechaCreacion() + ", fechaEntrega=" + getFechaEntrega() + ", fechaPostergacion=" + getFechaPostergacion() + ", estado=" + getEstado() + ", detalleNota=" + getDetalleNota() + ")";
    }

    public String getDetalleActividadId() {
        return this.detalleActividadId;
    }

    public Seminario getSeminario() {
        return this.seminario;
    }

    public String getNombreActividad() {
        return this.nombreActividad;
    }

    public Integer getNotaActividad() {
        return this.notaActividad;
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public Date getFechaEntrega() {
        return this.fechaEntrega;
    }

    public Date getFechaPostergacion() {
        return this.fechaPostergacion;
    }

    public String getEstado() {
        return this.estado;
    }

    public List<DetalleNota> getDetalleNota() {
        return this.detalleNota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleActividad that = (DetalleActividad) o;
        return Objects.equals(detalleActividadId, that.detalleActividadId) && Objects.equals(seminario, that.seminario) && Objects.equals(nombreActividad, that.nombreActividad) && Objects.equals(notaActividad, that.notaActividad) && Objects.equals(fechaCreacion, that.fechaCreacion) && Objects.equals(fechaEntrega, that.fechaEntrega) && Objects.equals(fechaPostergacion, that.fechaPostergacion) && Objects.equals(estado, that.estado) && Objects.equals(detalleNota, that.detalleNota);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detalleActividadId, seminario, nombreActividad, notaActividad, fechaCreacion, fechaEntrega, fechaPostergacion, estado, detalleNota);
    }
}