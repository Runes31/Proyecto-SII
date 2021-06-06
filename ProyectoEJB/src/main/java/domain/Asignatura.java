package domain;

import domain.Asignatura.AsignaturaId;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@IdClass(AsignaturaId.class)
public class Asignatura {

  public static class AsignaturaId implements Serializable {
    private String referencia;
    private String titulacion;

    public AsignaturaId(){}

    public AsignaturaId(String referencia, String titulacion) {
      this.referencia = referencia;
      this.titulacion = titulacion;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + ((titulacion == null) ? 0 : titulacion.hashCode());
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
		AsignaturaId other = (AsignaturaId) obj;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (titulacion == null) {
			if (other.titulacion != null)
				return false;
		} else if (!titulacion.equals(other.titulacion))
			return false;
		return true;
	}
    
  }
  
  @Id
  private String referencia;
  @Column(nullable = false)
  private String codigo;
  @Column(nullable = false)
  private int creditos;
  @Column(nullable = false)
  private boolean ofertada;
  @Column(nullable = false)
  private String nombre;
  private int curso;
  private String caracter;
  private int duracion;
  private String cuatrimestre;
  private String idiomas;
  @Id
  @ManyToOne(optional = false)
  private Titulacion titulacion;
  @OneToMany(mappedBy = "asignatura")
  private List<GruposPorAsignatura> gruposPorAsignatura;

	public Asignatura() { }

  public String getReferencia() {
    return referencia;
  }

  public void setReferencia(String referencia) {
    this.referencia = referencia;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public int getCreditos() {
    return creditos;
  }

  public void setCreditos(int creditos) {
    this.creditos = creditos;
  }

  public boolean isOfertada() {
    return ofertada;
  }

  public void setOfertada(boolean ofertada) {
    this.ofertada = ofertada;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getCurso() {
    return curso;
  }

  public void setCurso(int curso) {
    this.curso = curso;
  }

  public String getCaracter() {
    return caracter;
  }

  public void setCaracter(String caracter) {
    this.caracter = caracter;
  }

  public int getDuracion() {
    return duracion;
  }

  public void setDuracion(int duracion) {
    this.duracion = duracion;
  }

  public String getCuatrimestre() {
    return cuatrimestre;
  }

  public void setCuatrimestre(String cuatrimestre) {
    this.cuatrimestre = cuatrimestre;
  }

  public String getIdiomas() {
    return idiomas;
  }

  public void setIdiomas(String idiomas) {
    this.idiomas = idiomas;
  }

  public Titulacion getTitulacion() {
    return titulacion;
  }

  public void setTitulacion(Titulacion titulacion) {
    this.titulacion = titulacion;
  }
  
  public List<GruposPorAsignatura> getGruposPorAsignatura() {
		return gruposPorAsignatura;
	}

	public void setGruposPorAsignatura(List<GruposPorAsignatura> gruposPorAsignatura) {
		this.gruposPorAsignatura = gruposPorAsignatura;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + ((titulacion == null) ? 0 : titulacion.hashCode());
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
		Asignatura other = (Asignatura) obj;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (titulacion == null) {
			if (other.titulacion != null)
				return false;
		} else if (!titulacion.equals(other.titulacion))
			return false;
		return true;
	}

  @Override
  public String toString() {
    return "Asignatura [referencia=" + referencia + ", codigo=" + codigo + ", creditos=" + creditos + ", ofertada="
        + ofertada + ", nombre=" + nombre + ", curso=" + curso + ", caracter=" + caracter + ", duracion=" + duracion
        + ", cuatrimestre=" + cuatrimestre + ", idiomas=" + idiomas + "]";
  }
}
