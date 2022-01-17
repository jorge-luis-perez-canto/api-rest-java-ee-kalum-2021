package edu.kalum.notas.core.models.entities;


import edu.kalum.notas.core.models.entities.Role;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "usuario_app")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "El campo username no debe estar vacio")
    @Column(name = "username", unique = true)
    private String username;

    @NotEmpty(message = "Es necesario ingresar una contraseña")
    @Column(name = "password", length = 128)
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @NotEmpty(message = "El campo nombre no debe estar vacio")
    @Column(name = "nombres")
    private String nombres;

    @NotEmpty(message = "El campo apellido no debe estar vacio")
    @Column(name = "apellidos")
    private String apellidos;

    @NotEmpty(message = "El campo correo eléctronico no debe estar vacio")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "usuario_role", joinColumns = {@JoinColumn(name = "usuario_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})})
    private List<Role> roles;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Usuario;
    }

    public String toString() {
        return "Usuario(id=" + getId() + ", username=" + getUsername() + ", password=" + getPassword() + ", enabled=" + getEnabled() + ", nombres=" + getNombres() + ", apellidos=" + getApellidos() + ", email=" + getEmail() + ", telefono=" + getTelefono() + ", direccion=" + getDireccion() + ", roles=" + getRoles() + ")";
    }

    public Usuario(Long id, String username, String password, Boolean enabled, String nombres, String apellidos, String email, String telefono, String direccion, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.roles = roles;
    }

    public Usuario() {
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public String getNombres() {
        return this.nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(username, usuario.username) && Objects.equals(password, usuario.password) && Objects.equals(enabled, usuario.enabled) && Objects.equals(nombres, usuario.nombres) && Objects.equals(apellidos, usuario.apellidos) && Objects.equals(email, usuario.email) && Objects.equals(telefono, usuario.telefono) && Objects.equals(direccion, usuario.direccion) && Objects.equals(roles, usuario.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, enabled, nombres, apellidos, email, telefono, direccion, roles);
    }
}
