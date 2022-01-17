package edu.kalum.notas.core.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.kalum.notas.core.models.entities.Usuario;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "role_app")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Es necesario ingresar un role válido")
    @Column(name = "nombre", unique = true)
    private String nombre;

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    protected boolean canEqual(Object other) {
        return other instanceof edu.kalum.notas.core.models.entities.Role;
    }

    public String toString() {
        return "Role(id=" + getId() + ", nombre=" + getNombre() + ", usuarios=" + getUsuarios() + ")";
    }

    public Role() {
    }

    public Role(Long id, String nombre, List<Usuario> usuarios) {
        this.id = id;
        this.nombre = nombre;
        this.usuarios = usuarios;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(nombre, role.nombre) && Objects.equals(usuarios, role.usuarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, usuarios);
    }
}
