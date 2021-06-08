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
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + expediente;
		result = prime * result + ((fechaEnvio == null) ? 0 : fechaEnvio.hashCode());
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
		EncuestaId other = (EncuestaId) obj;
		if (expediente != other.expediente)
			return false;
		if (fechaEnvio == null) {
			if (other.fechaEnvio != null)
				return false;
		} else if (!fechaEnvio.equals(other.fechaEnvio))
			return false;
		return true;
	}
    
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
  public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + expediente.hashCode();
	result = prime * result + ((fechaEnvio == null) ? 0 : fechaEnvio.hashCode());
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
	if (expediente != other.expediente)
		return false;
	if (fechaEnvio == null) {
		if (other.fechaEnvio != null)
			return false;
	} else if (!fechaEnvio.equals(other.fechaEnvio))
		return false;
	return true;
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