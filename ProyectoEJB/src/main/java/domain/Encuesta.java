package domain;

import domain.Encuesta.EncuestaId;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(EncuestaId.class)
public class Encuesta{

  public static class EncuestaId implements Serializable {
    private Date fechaEnvio;
    private int expediente;
  }

  @Id
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaEnvio;
  @Id
  @ManyToOne
  private Expediente expediente;
  @ManyToMany
  private List<GruposPorAsignatura> gruposPorAsignaturas;

  public Encuesta() { }

  public Date getFechaEnvio() {
    return fechaEnvio;
  }

  public void setFechaEnvio(Date fechaEnvio) {
    this.fechaEnvio = fechaEnvio;
  }

  public Expediente getExpediente() {
    return expediente;
  }

  public void setExpediente(Expediente expediente) {
    this.expediente = expediente;
  }

  public List<GruposPorAsignatura> getGruposPorAsignaturas() {
    return gruposPorAsignaturas;
  }

  public void setGruposPorAsignaturas(List<GruposPorAsignatura> gruposPorAsignaturas) {
    this.gruposPorAsignaturas = gruposPorAsignaturas;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Encuesta encuesta = (Encuesta) o;
    return fechaEnvio.equals(encuesta.fechaEnvio) && expediente.equals(encuesta.expediente)
        && Objects.equals(gruposPorAsignaturas, encuesta.gruposPorAsignaturas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fechaEnvio, expediente, gruposPorAsignaturas);
  }

  @Override
  public String toString() {
    return "Encuesta{" +
        "fechaEnvio=" + fechaEnvio +
        ", expediente=" + expediente +
        ", gruposPorAsignaturas=" + gruposPorAsignaturas +
        '}';
  }
}