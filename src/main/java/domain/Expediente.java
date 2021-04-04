package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Expediente {
  // TODO: Añadir los atributos que faltan
  @Id
  private int numExpediente;
  private boolean activo;
  private double notaMediaProvisional;

  public Expediente() { }

  public Expediente(int numExpediente, boolean activo) {
    this.numExpediente = numExpediente;
    this.activo = activo;
  }

  public Expediente(int numExpediente, boolean activo, double notaMediaProvisional) {
    this.numExpediente = numExpediente;
    this.activo = activo;
    this.notaMediaProvisional = notaMediaProvisional;
  }

  public int getNumExpediente() {
    return numExpediente;
  }

  public void setNumExpediente(int numExpediente) {
    this.numExpediente = numExpediente;
  }

  public boolean isActivo() {
    return activo;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }

  public double getNotaMediaProvisional() {
    return notaMediaProvisional;
  }

  public void setNotaMediaProvisional(double notaMediaProvisional) {
    this.notaMediaProvisional = notaMediaProvisional;
  }
}