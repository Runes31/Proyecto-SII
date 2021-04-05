package domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Grupos_por_asignatura {
  @Id 
  private String Curso_Academico;
  private boolean Oferta;
  
  public Grupos_por_asignatura() { }
  
  
  
  public String getCurso_Academico() {
    return Curso_Academico;
  }



  public void setCurso_Academico(String curso_Academico) {
    Curso_Academico = curso_Academico;
  }



  public boolean isOferta() {
    return Oferta;
  }



  public void setOferta(boolean oferta) {
    Oferta = oferta;
  }


 
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((Curso_Academico == null) ? 0 : Curso_Academico.hashCode());
    result = prime * result + (Oferta ? 1231 : 1237);
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
    Grupos_por_asignatura other = (Grupos_por_asignatura) obj;
    if (Curso_Academico == null) {
      if (other.Curso_Academico != null)
        return false;
    } else if (!Curso_Academico.equals(other.Curso_Academico))
      return false;
    if (Oferta != other.Oferta)
      return false;
    return true;
  }



  @Override
  public String toString() {
    return "Curso Academico [curso=" + Curso_Academico + ", Oferta=" + Oferta + "]";
  }
}
