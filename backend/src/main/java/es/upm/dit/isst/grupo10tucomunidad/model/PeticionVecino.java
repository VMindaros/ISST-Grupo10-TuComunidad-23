package es.upm.dit.isst.grupo10tucomunidad.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "peticionesregistro",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "tlfNumber")
       })
public class PeticionVecino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String tlfNumber;

    @NotBlank
    private String password;

    @Min(0)
    @Max(10)
    private int piso;

    @NotBlank
    private String letra;

    @NotBlank
    private String dni;

    @Lob
    private byte[] adjunto;

    public PeticionVecino() {
    }

    public PeticionVecino(String tlfNumber, String password, int piso, String letra, String dni, byte[] adjunto) {
        this.tlfNumber = tlfNumber;
        this.password = password;
        this.piso = piso;
        this.letra = letra;
        this.dni = dni;
        this.adjunto = adjunto;
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

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public byte[] getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(byte[] adjunto) {
        this.adjunto = adjunto;
    }
}
