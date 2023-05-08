package es.upm.dit.isst.grupo10tucomunidad.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table( name="usuarios",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "tlfNumber"),
        })
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 9)
    private String tlfNumber;

    @Column(nullable = false, length = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "usuarios_roles",
                joinColumns = @JoinColumn(name = "usuarios_id"),
                inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Rol> roles = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable( name = "usuarios_datos",
                joinColumns = @JoinColumn(name = "usuarios_id"),
                inverseJoinColumns = @JoinColumn(name = "datosvecino_id"))
    private DatosVecino datosVecino;

    public Usuario(String tlfNumber, String password, Set<Rol> roles, DatosVecino datosVecino) {
        this.tlfNumber = tlfNumber;
        this.password = password;
        this.roles = roles;
        this.datosVecino = datosVecino;
    }

    public Usuario(String tlfNumber, String password, Set<Rol> roles) {
        this.tlfNumber = tlfNumber;
        this.password = password;
        this.roles = roles;
    }

    public Usuario(String tlfNumber, String password) {
        this.tlfNumber = tlfNumber;
        this.password = password;
    }

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTlfNumber() {
        return tlfNumber;
    }

    public void setTlfNumber(String tlfNumber) {
        this.tlfNumber = tlfNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public DatosVecino getDatosVecino() {
        return datosVecino;
    }

    public void setDatosVecino(DatosVecino datosVecino) {
        this.datosVecino = datosVecino;
    }
}
