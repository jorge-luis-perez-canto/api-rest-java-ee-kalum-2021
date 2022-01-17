package edu.kalum.notas.core.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.kalum.notas.core.models.entities.Clase;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "horario")
public class Horario implements Serializable {
    @Id
    @Column(name = "horario_id")
    private String horarioId;

    @Column(name = "horario_inicio", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horarioInicio;

    @Column(name = "hoario_final", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horarioFinal;

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "horario", fetch = FetchType.LAZY)
    private List<Clase> clases;

    public void setHorarioId(String horarioId) {
        this.horarioId = horarioId;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public void setHorarioFinal(Date horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    protected boolean canEqual(Object other) {
        return other instanceof edu.kalum.notas.core.models.entities.Horario;
    }

    public Horario() {
    }

    public Horario(String horarioId, Date horarioInicio, Date horarioFinal, List<Clase> clases) {
        this.horarioId = horarioId;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.clases = clases;
    }

    public String getHorarioId() {
        return this.horarioId;
    }

    public Date getHorarioInicio() {
        return this.horarioInicio;
    }

    public Date getHorarioFinal() {
        return this.horarioFinal;
    }

    public List<Clase> getClases() {
        return this.clases;
    }

    public String toString() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        DateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(getHorarioInicio()).concat(" | ").concat(formato.format(getHorarioFinal()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horario horario = (Horario) o;
        return Objects.equals(horarioId, horario.horarioId) && Objects.equals(horarioInicio, horario.horarioInicio) && Objects.equals(horarioFinal, horario.horarioFinal) && Objects.equals(clases, horario.clases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horarioId, horarioInicio, horarioFinal, clases);
    }
}