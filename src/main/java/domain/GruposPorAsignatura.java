package domain;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

public class GruposPorAsignatura {
  @Id @GeneratedValue
  private String cursoAcademico;
  private boolean oferta;
  @OneToMany
  private List<Clase> clases;
  @ManyToMany
  private List<Encuesta> Encuestas;
  
  public GruposPorAsignatura() { }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cursoAcademico == null) ? 0 : cursoAcademico.hashCode());
    result = prime * result + (oferta ? 1231 : 1237);
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
    GruposPorAsignatura other = (GruposPorAsignatura) obj;
    if (cursoAcademico == null) {
      if (other.cursoAcademico != null)
        return false;
    } else if (!cursoAcademico.equals(other.cursoAcademico))
      return false;
    if (oferta != other.oferta)
      return false;
    return true;
  }

  public String getCursoAcademico() {
    return cursoAcademico;
  }

  public void setCursoAcademico(String cursoAcademico) {
    this.cursoAcademico = cursoAcademico;
  }

  public boolean isOferta() {
    return oferta;
  }

  public void setOferta(boolean oferta) {
    this.oferta = oferta;
  }

  @Override
  public String toString() {
    return "Curso Academico [curso=" + cursoAcademico + ", Oferta=" + oferta + "]";
  }
}
