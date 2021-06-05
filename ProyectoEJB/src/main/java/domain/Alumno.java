package domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Alumno {
  
  @Id @GeneratedValue
  private int id;
  @Column(unique = true, nullable = false)
  private String dni;
  @Column(nullable = false)
  private String nombre;
  @Column(nullable = false)
  private String emailInstitucional;
  private String emailPersonal;
  private String telefono;
  private String movil;
  @Column(nullable = false)
  private String direccion;
  @Column(nullable = false)
  private String localidad;
  @Column(nullable = false)
  private String provincia;
  private String codigoPostal;
  @OneToMany(mappedBy = "alumno")
  private List<Expediente> expedientes;

  public Alumno() { }

  public Alumno(String dni, String nombre, String emailInstitucional, String emailPersonal,
      String telefono, String movil) {
    this.dni = dni;
    this.nombre = nombre;
    this.emailInstitucional = emailInstitucional;
    this.emailPersonal = emailPersonal;
    this.telefono = telefono;
    this.movil = movil;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public String getDni() {
    return dni;
  }

  public void setDni(String DNI) {
    this.dni = DNI;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmailInstitucional() {
    return emailInstitucional;
  }

  public void setEmailInstitucional(String emailInstitucional) {
    this.emailInstitucional = emailInstitucional;
  }

  public String getEmailPersonal() {
    return emailPersonal;
  }

  public void setEmailPersonal(String emailPersonal) {
    this.emailPersonal = emailPersonal;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getMovil() {
    return movil;
  }

  public void setMovil(String movil) {
    this.movil = movil;
  }

  public List<Expediente> getExpedientes() {
    return expedientes;
  }

  public void setExpedientes(List<Expediente> expedientes) {
    this.expedientes = expedientes;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getLocalidad() {
    return localidad;
  }

  public void setLocalidad(String localidad) {
    this.localidad = localidad;
  }

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  public String getCodigoPostal() {
    return codigoPostal;
  }

  public void setCodigoPostal(String codigoPostal) {
    this.codigoPostal = codigoPostal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Alumno alumno = (Alumno) o;
    return id == alumno.id;
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(id);
  }

  @Override
  public String toString() {
    return "Alumno{" +
        "id=" + id +
        ", DNI='" + dni + '\'' +
        ", nombre='" + nombre + '\'' +
        ", emailInstitucional='" + emailInstitucional + '\'' +
        ", emailPersonal='" + emailPersonal + '\'' +
        ", telefono='" + telefono + '\'' +
        ", movil='" + movil + '\'' +
        ", direccion='" + direccion + '\'' +
        ", localidad='" + localidad + '\'' +
        ", provincia='" + provincia + '\'' +
        ", codigoPostal='" + codigoPostal + '\'' +
        '}';
  }
}