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
  @ManyToMany(mappedBy = "gruposPorAsignatura")
  private List<Encuesta> encuestas;
  
  public GruposPorAsignatura() { }
  
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
  
  public List<Clase> getClases() {
    return clases;
  }

  public void setClases(List<Clase> clases) {
    this.clases = clases;
  }

  public List<Encuesta> getEncuestas() {
    return encuestas;
  }

  public void setEncuestas(List<Encuesta> encuestas) {
    this.encuestas = encuestas;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((clases == null) ? 0 : clases.hashCode());
    result = prime * result + ((cursoAcademico == null) ? 0 : cursoAcademico.hashCode());
    result = prime * result + ((encuestas == null) ? 0 : encuestas.hashCode());
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
    if (clases == null) {
      if (other.clases != null)
        return false;
    } else if (!clases.equals(other.clases))
      return false;
    if (cursoAcademico == null) {
      if (other.cursoAcademico != null)
        return false;
    } else if (!cursoAcademico.equals(other.cursoAcademico))
      return false;
    if (encuestas == null) {
      if (other.encuestas != null)
        return false;
    } else if (!encuestas.equals(other.encuestas))
      return false;
    if (oferta != other.oferta)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "GruposPorAsignatura [cursoAcademico=" + cursoAcademico + ", oferta=" + oferta + ", clases=" + clases
        + ", encuestas=" + encuestas + "]";
  }
}
