package domain;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Clase {

  @Id @Column(nullable = false)
  private Date dia;
  @Id @Column(nullable = false)
  private Date horaIni;
  private Date horaFin;
  
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((dia == null) ? 0 : dia.hashCode());
    result = prime * result + ((horaFin == null) ? 0 : horaFin.hashCode());
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
    if (horaFin == null) {
      if (other.horaFin != null)
        return false;
    } else if (!horaFin.equals(other.horaFin))
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
    return "Clase [dia=" + dia + ", horaIni=" + horaIni + ", horaFin=" + horaFin + "]";
  }

}
