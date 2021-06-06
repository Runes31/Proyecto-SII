package domain;

import domain.Clase.ClaseId;
import domain.GruposPorAsignatura.GruposPorAsignaturaId;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(ClaseId.class)
public class Clase {

  public static class ClaseId implements Serializable {
    private Date dia;
    private Date horaIni;
    private GruposPorAsignaturaId gruposPorAsignaturas;
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result + ((gruposPorAsignaturas == null) ? 0 : gruposPorAsignaturas.hashCode());
		result = prime * result + ((horaIni == null) ? 0 : horaIni.hashCode());
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
		ClaseId other = (ClaseId) obj;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (gruposPorAsignaturas == null) {
			if (other.gruposPorAsignaturas != null)
				return false;
		} else if (!gruposPorAsignaturas.equals(other.gruposPorAsignaturas))
			return false;
		if (horaIni == null) {
			if (other.horaIni != null)
				return false;
		} else if (!horaIni.equals(other.horaIni))
			return false;
		return true;
	}
    
  }

  @Id
  @Temporal(TemporalType.DATE)
  private Date dia;
  @Id
  @Temporal(TemporalType.TIME)
  private Date horaIni;
  @Temporal(TemporalType.TIME)
  private Date horaFin;
  @Id
  @ManyToOne(optional = false)
  private GruposPorAsignatura gruposPorAsignaturas;

  public Clase() {
    
  }
  
  public Clase(Date d, Date hi, Date hf) {
    this.dia = d;
    this.horaFin = hf;
    this.horaIni = hi;
  }

  public Date getDia() {
    return dia;
  }

  public void setDia(Date dia) {
    this.dia = dia;
  }

  public Date getHoraIni() {
    return horaIni;
  }

  public void setHoraIni(Date horaIni) {
    this.horaIni = horaIni;
  }

  public Date getHoraFin() {
    return horaFin;
  }

  public void setHoraFin(Date horaFin) {
    this.horaFin = horaFin;
  }

  public GruposPorAsignatura getGruposPorAsignaturas() {
    return gruposPorAsignaturas;
  }

  public void setGruposPorAsignaturas(GruposPorAsignatura gruposPorAsignaturas) {
    this.gruposPorAsignaturas = gruposPorAsignaturas;
  }

  @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result + ((gruposPorAsignaturas == null) ? 0 : gruposPorAsignaturas.hashCode());
		result = prime * result + ((horaIni == null) ? 0 : horaIni.hashCode());
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
		Clase other = (Clase) obj;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (gruposPorAsignaturas == null) {
			if (other.gruposPorAsignaturas != null)
				return false;
		} else if (!gruposPorAsignaturas.equals(other.gruposPorAsignaturas))
			return false;
		if (horaIni == null) {
			if (other.horaIni != null)
				return false;
		} else if (!horaIni.equals(other.horaIni))
			return false;
		return true;
	}

  @Override
  public String toString() {
    return "Clase{" +
        "dia=" + dia +
        ", horaIni=" + horaIni +
        ", horaFin=" + horaFin +
        ", gruposPorAsignaturas=" + gruposPorAsignaturas +
        '}';
  }
}