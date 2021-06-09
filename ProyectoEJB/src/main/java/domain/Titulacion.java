package domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
  @OneToMany(mappedBy = "titulacion", fetch = FetchType.EAGER)
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
  public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
	if (codigo == null) {
		if (other.codigo != null)
			return false;
	} else if (!codigo.equals(other.codigo))
		return false;
	return true;
}

@Override
  public String toString() {
    return "Titulacion{" +
        "codigo='" + codigo + '\'' +
        ", nombre='" + nombre + '\'' +
        ", creditos=" + creditos +
        '}';
  }
}
