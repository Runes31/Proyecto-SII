package domain;

import javax.persistence.*;

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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((caracter == null) ? 0 : caracter.hashCode());
    result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
    result = prime * result + creditos;
    result = prime * result + ((cuatrimestre == null) ? 0 : cuatrimestre.hashCode());
    result = prime * result + curso;
    result = prime * result + duracion;
    result = prime * result + ((idiomas == null) ? 0 : idiomas.hashCode());
    result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
    result = prime * result + (ofertada ? 1231 : 1237);
    result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
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
    return true;
  }

  @Override
  public String toString() {
    return "Asignatura [referencia=" + referencia + ", codigo=" + codigo + ", creditos=" + creditos + ", ofertada="
        + ofertada + ", nombre=" + nombre + ", curso=" + curso + ", caracter=" + caracter + ", duracion=" + duracion
        + ", cuatrimestre=" + cuatrimestre + ", idiomas=" + idiomas + "]";
  }
	
	
}
