package domain;

import java.util.List;

import javax.persistence.*;

@Entity
public class Titulacion {
   
  @Id 
  private String codigo;
  @Column(nullable = false)
  private String nombre;
  @Column(nullable = false)
  private int creditos;
  @OneToMany
  private List<Asignatura> asignatura;
  @OneToMany
  private List<Expediente> expedientes;
  @OneToMany
  private List<Grupo> grupo;
  private List<Centro> centros;
  
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

  public List<Asignatura> getAsignatura() {
    return asignatura;
  }

  public void setAsignatura(List<Asignatura> asignatura) {
    this.asignatura = asignatura;
  }

  public List<Expediente> getExpedientes() {
    return expedientes;
  }

  public void setExpedientes(List<Expediente> expedientes) {
    this.expedientes = expedientes;
  }

  public List<Grupo> getGrupo() {
    return grupo;
  }

  public void setGrupo(List<Grupo> grupo) {
    this.grupo = grupo;
  }

  public List<Centro> getCentros() {
    return centros;
  }

  public void setCentros(List<Centro> centros) {
    this.centros = centros;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
    result = prime * result + ((centros == null) ? 0 : centros.hashCode());
    result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
    result = prime * result + creditos;
    result = prime * result + ((expedientes == null) ? 0 : expedientes.hashCode());
    result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
    result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Titulacion other = (Titulacion) obj;
    if (asignatura == null) {
      if (other.asignatura != null)
        return false;
    } else if (!asignatura.equals(other.asignatura))
      return false;
    if (centros == null) {
      if (other.centros != null)
        return false;
    } else if (!centros.equals(other.centros))
      return false;
    if (codigo == null) {
      if (other.codigo != null)
        return false;
    } else if (!codigo.equals(other.codigo))
      return false;
    if (creditos != other.creditos)
      return false;
    if (expedientes == null) {
      if (other.expedientes != null)
        return false;
    } else if (!expedientes.equals(other.expedientes))
      return false;
    if (grupo == null) {
      if (other.grupo != null)
        return false;
    } else if (!grupo.equals(other.grupo))
      return false;
    if (nombre == null) {
      if (other.nombre != null)
        return false;
    } else if (!nombre.equals(other.nombre))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Titulacion [codigo=" + codigo + ", nombre=" + nombre + ", creditos=" + creditos + ", asignatura="
        + asignatura + ", expedientes=" + expedientes + ", grupo=" + grupo + ", centros=" + centros + "]";
  }
}
