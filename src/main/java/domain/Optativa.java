package domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("O")
public class Optativa extends Asignatura {

  @Column(nullable = false)
	private int plazas;
  private String mencion;

  public Optativa() { super(); }

  public int getPlazas() {
    return plazas;
  }

  public void setPlazas(int plazas) {
    this.plazas = plazas;
  }

  public String getMencion() {
    return mencion;
  }

  public void setMencion(String mencion) {
    this.mencion = mencion;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((mencion == null) ? 0 : mencion.hashCode());
    result = prime * result + plazas;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Optativa other = (Optativa) obj;
    if (mencion == null) {
      if (other.mencion != null)
        return false;
    } else if (!mencion.equals(other.mencion))
      return false;
    if (plazas != other.plazas)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Optativa [plazas=" + plazas + ", mencion=" + mencion + "]";
  }
  
}
