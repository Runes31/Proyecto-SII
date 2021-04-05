package domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Expediente {
	
	@Id
	private int numExpediente;
	private boolean activo;
	private double notaMediaProvisional;
	@Column(nullable = false)
	private int creditosSuperados;
	@Column(nullable = false)
	private int creditosFB;
	@Column(nullable = false)
	private int creditosOB;
	@Column(nullable = false)
	private int creditosOP;
	@Column(nullable = false)
	private int creditosCF;
	@Column(nullable = false)
	private int creditosPE;
	@Column(nullable = false)
	private int creditosTF;
	@OneToMany
  private List<Encuesta> encuesta;


	public Expediente() {
	}

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
	
	public int getCreditosSuperados() {
		return creditosSuperados;
	}

	public void setCreditosSuperados(int creditosSuperados) {
		this.creditosSuperados = creditosSuperados;
	}

	public int getCreditosFB() {
		return creditosFB;
	}

	public void setCreditosFB(int creditosFB) {
		this.creditosFB = creditosFB;
	}

	public int getCreditosOB() {
		return creditosOB;
	}

	public void setCreditosOB(int creditosOB) {
		this.creditosOB = creditosOB;
	}

	public int getCreditosOP() {
		return creditosOP;
	}

	public void setCreditosOP(int creditosOP) {
		this.creditosOP = creditosOP;
	}

	public int getCreditosCF() {
		return creditosCF;
	}

	public void setCreditosCF(int creditosCF) {
		this.creditosCF = creditosCF;
	}

	public int getCreditosPE() {
		return creditosPE;
	}

	public void setCreditosPE(int creditosPE) {
		this.creditosPE = creditosPE;
	}

	public int getCreditosTF() {
		return creditosTF;
	}

	public void setCreditosTF(int creditosTF) {
		this.creditosTF = creditosTF;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (activo ? 1231 : 1237);
		result = prime * result + creditosCF;
		result = prime * result + creditosFB;
		result = prime * result + creditosOB;
		result = prime * result + creditosOP;
		result = prime * result + creditosPE;
		result = prime * result + creditosSuperados;
		result = prime * result + creditosTF;
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
		if (creditosCF != other.creditosCF)
			return false;
		if (creditosFB != other.creditosFB)
			return false;
		if (creditosOB != other.creditosOB)
			return false;
		if (creditosOP != other.creditosOP)
			return false;
		if (creditosPE != other.creditosPE)
			return false;
		if (creditosSuperados != other.creditosSuperados)
			return false;
		if (creditosTF != other.creditosTF)
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
				+ notaMediaProvisional + ", creditosSuperados=" + creditosSuperados + ", creditosFB=" + creditosFB
				+ ", creditosOB=" + creditosOB + ", creditosOP=" + creditosOP + ", creditosCF=" + creditosCF
				+ ", creditosPE=" + creditosPE + ", creditosTF=" + creditosTF + "]";
	}
  
  
}