package domain;

import domain.Matricula.MatriculaId;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@IdClass(MatriculaId.class)
public class Matricula{

  public static class MatriculaId implements Serializable {
    private String cursoAcademico;
    private int expediente;

    public MatriculaId(){}
    public MatriculaId(String c, int e){
      cursoAcademico = c;
      expediente = e;
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cursoAcademico == null) ? 0 : cursoAcademico.hashCode());
		result = prime * result + expediente;
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
		MatriculaId other = (MatriculaId) obj;
		if (cursoAcademico == null) {
			if (other.cursoAcademico != null)
				return false;
		} else if (!cursoAcademico.equals(other.cursoAcademico))
			return false;
		if (expediente != other.expediente)
			return false;
		return true;
	}
    
  }

  @Id
  private String cursoAcademico;
  @Column(nullable = false)
  private String estado;
  @Column(nullable = false)
  private int numArchivo;
  private String turnoPreferente;
  @Column(nullable = false)
  private Date fechaMatricula;
  private boolean nuevoIngreso;
  private String listadoAsignaturas;
  @OneToMany(mappedBy = "matricula", fetch = FetchType.EAGER)
  private List<AsignaturasMatricula> asignaturasMatriculas;
  @Id
  @ManyToOne
  private Expediente expediente;

  public Matricula() { }

  public String getCursoAcademico() {
    return cursoAcademico;
  }

  public void setCursoAcademico(String cursoAcademico) {
    this.cursoAcademico = cursoAcademico;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public int getNumArchivo() {
    return numArchivo;
  }

  public void setNumArchivo(int numArchivo) {
    this.numArchivo = numArchivo;
  }

  public String getTurnoPreferente() {
    return turnoPreferente;
  }

  public void setTurnoPreferente(String turnoPreferente) {
    this.turnoPreferente = turnoPreferente;
  }

  public Date getFechaMatricula() {
    return fechaMatricula;
  }

  public void setFechaMatricula(Date fechaMatricula) {
    this.fechaMatricula = fechaMatricula;
  }

  public boolean isNuevoIngreso() {
    return nuevoIngreso;
  }

  public void setNuevoIngreso(boolean nuevoIngreso) {
    this.nuevoIngreso = nuevoIngreso;
  }

  public String getListadoAsignaturas() {
    return listadoAsignaturas;
  }

  public void setListadoAsignaturas(String listadoAsignaturas) {
    this.listadoAsignaturas = listadoAsignaturas;
  }
  
  public Expediente getExpediente() {
    return expediente;
  }

  public void setExpediente(Expediente expediente) {
    this.expediente = expediente;
  }

  public List<AsignaturasMatricula> getAsignaturasMatriculas() {
    return asignaturasMatriculas;
  }

  public void setAsignaturasMatriculas(List<AsignaturasMatricula> asignaturasMatriculas) {
    this.asignaturasMatriculas = asignaturasMatriculas;
  }

  @Override
  public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cursoAcademico == null) ? 0 : cursoAcademico.hashCode());
	result = prime * result + expediente.hashCode();
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
	Matricula other = (Matricula) obj;
	if (cursoAcademico == null) {
		if (other.cursoAcademico != null)
			return false;
	} else if (!cursoAcademico.equals(other.cursoAcademico))
		return false;
	if (expediente != other.expediente)
		return false;
	return true;
}
  
  @Override
  public String toString() {
    return "Matricula [cursoAcademico=" + cursoAcademico + ", estado=" + estado + ", numArchivo=" + numArchivo
        + ", turnoPreferente=" + turnoPreferente + ", fechaMatricula=" + fechaMatricula + ", nuevoIngreso="
        + nuevoIngreso + ", listadoAsignaturas=" + listadoAsignaturas + "]";
  }
  
}
