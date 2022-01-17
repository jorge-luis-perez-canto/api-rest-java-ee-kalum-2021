package edu.kalum.notas.core.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.kalum.notas.core.models.entities.CarreraTecnica;
import edu.kalum.notas.core.models.entities.Seminario;

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

@Table(name = "modulo")
@Entity
public class Modulo implements Serializable {
    @Id
    @Column(name = "modulo_id")
    private String modulo_id;

    @Column(name = "nombre_modulo")
    private String nombre_modulo;

    @Column(name = "numero_seminarios")
    private Integer numero_seminarios;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_carrera", referencedColumnName = "codigo_carrera")
    private CarreraTecnica carreraTecnica;

    @OneToMany(mappedBy = "modulo", fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Seminario> seminario;

    public Modulo() {
    }

    public Modulo(String modulo_id, String nombre_modulo, Integer numero_seminarios, CarreraTecnica carreraTecnica, List<Seminario> seminario) {
        this.modulo_id = modulo_id;
        this.nombre_modulo = nombre_modulo;
        this.numero_seminarios = numero_seminarios;
        this.carreraTecnica = carreraTecnica;
        this.seminario = seminario;
    }

    public void setModulo_id(String modulo_id) {
        this.modulo_id = modulo_id;
    }

    public void setNombre_modulo(String nombre_modulo) {
        this.nombre_modulo = nombre_modulo;
    }

    public void setNumero_seminarios(Integer numero_seminarios) {
        this.numero_seminarios = numero_seminarios;
    }

    public void setCarreraTecnica(CarreraTecnica carreraTecnica) {
        this.carreraTecnica = carreraTecnica;
    }

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setSeminario(List<Seminario> seminario) {
        this.seminario = seminario;
    }

    protected boolean canEqual(Object other) {
        return other instanceof edu.kalum.notas.core.models.entities.Modulo;
    }

    public String toString() {
        return "Modulo(modulo_id=" + getModulo_id() + ", nombre_modulo=" + getNombre_modulo() + ", numero_seminarios=" + getNumero_seminarios() + ", carreraTecnica=" + getCarreraTecnica() + ", seminario=" + getSeminario() + ")";
    }

    public String getModulo_id() {
        return this.modulo_id;
    }

    public String getNombre_modulo() {
        return this.nombre_modulo;
    }

    public Integer getNumero_seminarios() {
        return this.numero_seminarios;
    }

    public CarreraTecnica getCarreraTecnica() {
        return this.carreraTecnica;
    }

    public List<Seminario> getSeminario() {
        return this.seminario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modulo modulo = (Modulo) o;
        return Objects.equals(modulo_id, modulo.modulo_id) && Objects.equals(nombre_modulo, modulo.nombre_modulo) && Objects.equals(numero_seminarios, modulo.numero_seminarios) && Objects.equals(carreraTecnica, modulo.carreraTecnica) && Objects.equals(seminario, modulo.seminario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modulo_id, nombre_modulo, numero_seminarios, carreraTecnica, seminario);
    }
}