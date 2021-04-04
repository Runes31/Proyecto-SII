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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (activo ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(notaMediaProvisional);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + numExpediente;
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
		Expediente other = (Expediente) obj;
		if (activo != other.activo)
			return false;
		if (Double.doubleToLongBits(notaMediaProvisional) != Double.doubleToLongBits(other.notaMediaProvisional))
			return false;
		if (numExpediente != other.numExpediente)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Expediente [numExpediente=" + numExpediente + ", activo=" + activo + ", notaMediaProvisional="
				+ notaMediaProvisional + "]";
	}
	
	
  
  
  
  
}