package domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
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
  public String toString() {
    return "Optativa [plazas=" + plazas + ", mencion=" + mencion + "]";
  }
  
}
