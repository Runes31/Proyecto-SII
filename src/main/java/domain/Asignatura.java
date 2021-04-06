package domain;

import java.util.Objects;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Asignatura that = (Asignatura) o;
    return creditos == that.creditos && ofertada == that.ofertada && curso == that.curso
        && duracion == that.duracion && referencia.equals(that.referencia) && codigo
        .equals(that.codigo) && nombre.equals(that.nombre) && Objects
        .equals(caracter, that.caracter) && Objects.equals(cuatrimestre, that.cuatrimestre)
        && Objects.equals(idiomas, that.idiomas) && titulacion.equals(that.titulacion);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(referencia, codigo, creditos, ofertada, nombre, curso, caracter, duracion,
            cuatrimestre,
            idiomas, titulacion);
  }

  @Override
  public String toString() {
    return "Asignatura{" +
        "referencia='" + referencia + '\'' +
        ", codigo='" + codigo + '\'' +
        ", creditos=" + creditos +
        ", ofertada=" + ofertada +
        ", nombre='" + nombre + '\'' +
        ", curso=" + curso +
        ", caracter='" + caracter + '\'' +
        ", duracion=" + duracion +
        ", cuatrimestre='" + cuatrimestre + '\'' +
        ", idiomas='" + idiomas + '\'' +
        ", titulacion=" + titulacion +
        '}';
  }
}
