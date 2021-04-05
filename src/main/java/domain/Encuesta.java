package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Encuesta{

  @Id
  private Date fechaEnvio;
  private List<GruposPorAsignatura> gruposPorAsignatura;
  
  public Encuesta() { }

  public Date getFechaEnvio() {
    return fechaEnvio;
  }

  public void setFechaEnvio(Date fechaEnvio) {
    this.fechaEnvio = fechaEnvio;
  }
  
  public List<GruposPorAsignatura> getGrupoPorAsignatura() {
    return gruposPorAsignatura;
  }

  public void setGrupoPorAsignatura(List<GruposPorAsignatura> gruposPorAsignatura) {
    this.gruposPorAsignatura = gruposPorAsignatura;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fechaEnvio == null) ? 0 : fechaEnvio.hashCode());
    result = prime * result + ((gruposPorAsignatura == null) ? 0 : gruposPorAsignatura.hashCode());
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
    Encuesta other = (Encuesta) obj;
    if (fechaEnvio == null) {
      if (other.fechaEnvio != null)
        return false;
    } else if (!fechaEnvio.equals(other.fechaEnvio))
      return false;
    if (gruposPorAsignatura == null) {
      if (other.gruposPorAsignatura != null)
        return false;
    } else if (!gruposPorAsignatura.equals(other.gruposPorAsignatura))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Encuesta [fechaEnvio=" + fechaEnvio + ", gruposPorAsignatura=" + gruposPorAsignatura + "]";
  }
  
}
