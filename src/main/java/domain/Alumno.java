package domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Alumno {
  // TODO: Comprobar que están todos los atributos
  @Id @GeneratedValue
  private int id;
  @Column(unique = true, nullable = false)
  private String DNI;
  @Column(nullable = false)
  private String nombre;
  @Column(nullable = false)
  private String emailInstitucional;
  private String emailPersonal;
  private String telefono;
  private String movil;
  @OneToMany
  private List<Expediente> expedientes;

  public Alumno() { }

  public Alumno(String DNI, String nombre, String emailInstitucional, String emailPersonal,
      String telefono, String movil) {
    this.DNI = DNI;
    this.nombre = nombre;
    this.emailInstitucional = emailInstitucional;
    this.emailPersonal = emailPersonal;
    this.telefono = telefono;
    this.movil = movil;
  }

  public Alumno(String DNI, String nombre, String emailInstitucional,
      String emailPersonal, String telefono, String movil,
      List<Expediente> expedientes) {
    this.DNI = DNI;
    this.nombre = nombre;
    this.emailInstitucional = emailInstitucional;
    this.emailPersonal = emailPersonal;
    this.telefono = telefono;
    this.movil = movil;
    this.expedientes = expedientes;
  }

  public int getId() {
    return id;
  }

  public String getDNI() {
    return DNI;
  }

  public void setDNI(String DNI) {
    this.DNI = DNI;
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
}
