package edu.kalum.notas.core.models.entities;


import edu.kalum.notas.core.models.entities.Alumno;
import edu.kalum.notas.core.models.entities.DetalleActividad;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "detalle_nota")
@Entity
public class DetalleNota implements Serializable {
    @Id
    @Column(name = "detalle_nota_id")
    private String detalleNotaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "detalle_actividad_id", referencedColumnName = "detalle_actividad_id")
    private DetalleActividad detalleActividad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carne", referencedColumnName = "carne")
    private Alumno alumno;

    @Column(name = "valor_nota")
    private Integer valorNota;

    public DetalleNota() {
    }

    public DetalleNota(String detalleNotaId, DetalleActividad detalleActividad, Alumno alumno, Integer valorNota) {
        this.detalleNotaId = detalleNotaId;
        this.detalleActividad = detalleActividad;
        this.alumno = alumno;
        this.valorNota = valorNota;
    }

    public void setDetalleNotaId(String detalleNotaId) {
        this.detalleNotaId = detalleNotaId;
    }

    public void setDetalleActividad(DetalleActividad detalleActividad) {
        this.detalleActividad = detalleActividad;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public void setValorNota(Integer valorNota) {
        this.valorNota = valorNota;
    }

    protected boolean canEqual(Object other) {
        return other instanceof edu.kalum.notas.core.models.entities.DetalleNota;
    }

    public String toString() {
        return "DetalleNota(detalleNotaId=" + getDetalleNotaId() + ", detalleActividad=" + getDetalleActividad() + ", alumno=" + getAlumno() + ", valorNota=" + getValorNota() + ")";
    }

    public String getDetalleNotaId() {
        return this.detalleNotaId;
    }

    public DetalleActividad getDetalleActividad() {
        return this.detalleActividad;
    }

    public Alumno getAlumno() {
        return this.alumno;
    }

    public Integer getValorNota() {
        return this.valorNota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleNota that = (DetalleNota) o;
        return Objects.equals(detalleNotaId, that.detalleNotaId) && Objects.equals(detalleActividad, that.detalleActividad) && Objects.equals(alumno, that.alumno) && Objects.equals(valorNota, that.valorNota);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detalleNotaId, detalleActividad, alumno, valorNota);
    }
}