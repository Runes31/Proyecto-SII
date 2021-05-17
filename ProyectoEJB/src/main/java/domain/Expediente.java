package domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Titulacion titulacion;
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Alumno alumno;
	@OneToMany(mappedBy = "expediente")
	private List<Encuesta> encuestas;
	@OneToMany(mappedBy = "expediente")
	private List<Matricula> matriculas;

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

	public Titulacion getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<Encuesta> getEncuestas() {
		return encuestas;
	}

	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Expediente that = (Expediente) o;
		return numExpediente == that.numExpediente && activo == that.activo
				&& Double.compare(that.notaMediaProvisional, notaMediaProvisional) == 0
				&& creditosSuperados == that.creditosSuperados && creditosFB == that.creditosFB
				&& creditosOB == that.creditosOB && creditosOP == that.creditosOP
				&& creditosCF == that.creditosCF && creditosPE == that.creditosPE
				&& creditosTF == that.creditosTF && titulacion.equals(that.titulacion) && Objects
				.equals(alumno, that.alumno);
	}

	@Override
	public int hashCode() {
		return Objects
				.hash(numExpediente, activo, notaMediaProvisional, creditosSuperados, creditosFB,
						creditosOB,
						creditosOP, creditosCF, creditosPE, creditosTF, titulacion, alumno);
	}

	@Override
	public String toString() {
		return "Expediente{" +
				"numExpediente=" + numExpediente +
				", activo=" + activo +
				", notaMediaProvisional=" + notaMediaProvisional +
				", creditosSuperados=" + creditosSuperados +
				", creditosFB=" + creditosFB +
				", creditosOB=" + creditosOB +
				", creditosOP=" + creditosOP +
				", creditosCF=" + creditosCF +
				", creditosPE=" + creditosPE +
				", creditosTF=" + creditosTF +
				", titulacion=" + titulacion +
				", alumno=" + alumno +
				'}';
	}
}