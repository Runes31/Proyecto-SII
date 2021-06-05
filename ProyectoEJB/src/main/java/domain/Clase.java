package domain;

import domain.Clase.ClaseId;
import domain.GruposPorAsignatura.GruposPorAsignaturaId;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Clase clase = (Clase) o;
    return dia.equals(clase.dia) && horaIni.equals(clase.horaIni) && gruposPorAsignaturas.equals(clase.getGruposPorAsignaturas());
  }

  @Override
  public int hashCode() {
    return Objects.hash(dia, horaIni, gruposPorAsignaturas);
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