package domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name="disc", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("A")
public class Asignatura {
  
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
  @ManyToOne(optional = false)
  @JoinColumn(nullable = false)
  private Titulacion titulacion;
  @OneToMany(mappedBy = "asignatura")
  private List<GruposPorAsignatura> gruposPorAsignatura;
  @OneToMany(mappedBy = "asignatura")
  private List<AsignaturasMatricula> asignaturasMatricula;

	

	
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
	
	public List<AsignaturasMatricula> getAsignaturasMatricula() {
		return asignaturasMatricula;
	}

	public void setAsignaturasMatricula(List<AsignaturasMatricula> asignaturasMatricula) {
		this.asignaturasMatricula = asignaturasMatricula;
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
	if (asignaturasMatricula == null) {
		if (other.asignaturasMatricula != null)
			return false;
	} else if (!asignaturasMatricula.equals(other.asignaturasMatricula))
		return false;
	if (caracter == null) {
		if (other.caracter != null)
			return false;
	} else if (!caracter.equals(other.caracter))
		return false;
	if (codigo == null) {
		if (other.codigo != null)
			return false;
	} else if (!codigo.equals(other.codigo))
		return false;
	if (creditos != other.creditos)
		return false;
	if (cuatrimestre == null) {
		if (other.cuatrimestre != null)
			return false;
	} else if (!cuatrimestre.equals(other.cuatrimestre))
		return false;
	if (curso != other.curso)
		return false;
	if (duracion != other.duracion)
		return false;
	if (gruposPorAsignatura == null) {
		if (other.gruposPorAsignatura != null)
			return false;
	} else if (!gruposPorAsignatura.equals(other.gruposPorAsignatura))
		return false;
	if (idiomas == null) {
		if (other.idiomas != null)
			return false;
	} else if (!idiomas.equals(other.idiomas))
		return false;
	if (nombre == null) {
		if (other.nombre != null)
			return false;
	} else if (!nombre.equals(other.nombre))
		return false;
	if (ofertada != other.ofertada)
		return false;
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
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((asignaturasMatricula == null) ? 0 : asignaturasMatricula.hashCode());
	result = prime * result + ((caracter == null) ? 0 : caracter.hashCode());
	result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
	result = prime * result + creditos;
	result = prime * result + ((cuatrimestre == null) ? 0 : cuatrimestre.hashCode());
	result = prime * result + curso;
	result = prime * result + duracion;
	result = prime * result + ((gruposPorAsignatura == null) ? 0 : gruposPorAsignatura.hashCode());
	result = prime * result + ((idiomas == null) ? 0 : idiomas.hashCode());
	result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
	result = prime * result + (ofertada ? 1231 : 1237);
	result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
	result = prime * result + ((titulacion == null) ? 0 : titulacion.hashCode());
	return result;
}

  @Override
public String toString() {
	return "Asignatura [referencia=" + referencia + ", codigo=" + codigo + ", creditos=" + creditos + ", ofertada="
			+ ofertada + ", nombre=" + nombre + ", curso=" + curso + ", caracter=" + caracter + ", duracion=" + duracion
			+ ", cuatrimestre=" + cuatrimestre + ", idiomas=" + idiomas + ", titulacion=" + titulacion
			+ ", gruposPorAsignatura=" + gruposPorAsignatura + ", asignaturasMatricula=" + asignaturasMatricula + "]";
}
}
