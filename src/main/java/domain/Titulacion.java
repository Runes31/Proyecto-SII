package domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Titulacion {
   
  @Id 
  private String codigo;
  @Column(nullable = false)
  private String nombre;
  @Column(nullable = false)
  private int creditos;
  @ManyToMany(mappedBy = "titulaciones")
  private List<Centro> centros;
  @OneToMany(mappedBy = "titulacion")
  private List<Asignatura> asignaturas;
  @OneToMany(mappedBy = "titulacion")
  private List<Expediente> expedientes;
  @OneToMany(mappedBy = "titulacion")
  private List<Grupo> grupos;
  
  public Titulacion() { }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getCreditos() {
    return creditos;
  }

  public void setCreditos(int creditos) {
    this.creditos = creditos;
  }

  public List<Centro> getCentros() {
    return centros;
  }

  public void setCentros(List<Centro> centros) {
    this.centros = centros;
  }

  public List<Asignatura> getAsignaturas() {
    return asignaturas;
  }

  public void setAsignaturas(List<Asignatura> asignaturas) {
    this.asignaturas = asignaturas;
  }

  public List<Expediente> getExpedientes() {
    return expedientes;
  }

  public void setExpedientes(List<Expediente> expedientes) {
    this.expedientes = expedientes;
  }

  public List<Grupo> getGrupos() {
    return grupos;
  }

  public void setGrupos(List<Grupo> grupos) {
    this.grupos = grupos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Titulacion that = (Titulacion) o;
    return creditos == that.creditos && codigo.equals(that.codigo) && nombre.equals(that.nombre)
        && Objects.equals(centros, that.centros) && Objects
        .equals(asignaturas, that.asignaturas) && Objects
        .equals(expedientes, that.expedientes) && Objects.equals(grupos, that.grupos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, nombre, creditos, centros, asignaturas, expedientes, grupos);
  }

  @Override
  public String toString() {
    return "Titulacion{" +
        "codigo='" + codigo + '\'' +
        ", nombre='" + nombre + '\'' +
        ", creditos=" + creditos +
        ", centros=" + centros +
        ", asignaturas=" + asignaturas +
        ", expedientes=" + expedientes +
        ", grupos=" + grupos +
        '}';
  }
}
