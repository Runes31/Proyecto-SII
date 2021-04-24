package domain;

import domain.Asignatura.AsignaturaId;
import domain.GruposPorAsignatura.GruposPorAsignaturaId;
import domain.Matricula.MatriculaId;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@IdClass(GruposPorAsignaturaId.class)
public class GruposPorAsignatura {

  public static class GruposPorAsignaturaId implements Serializable {
    private String cursoAcademico;
    private AsignaturaId asignatura;
    private int grupo;

    public GruposPorAsignaturaId(){}

    public GruposPorAsignaturaId(String cursoAcademico, AsignaturaId asignatura, int grupo) {
      this.cursoAcademico = cursoAcademico;
      this.asignatura = asignatura;
      this.grupo = grupo;
    }
  }

  @Id
  @GeneratedValue
  private String cursoAcademico;
  private boolean oferta;
  @OneToMany(mappedBy = "gruposPorAsignaturas")
  private List<Clase> clases;
  @ManyToMany(mappedBy = "gruposPorAsignaturas")
  private List<Encuesta> encuestas;
  @Id
  @ManyToOne
  private Asignatura asignatura;
  @Id
  @ManyToOne
  private Grupo grupo;

  public GruposPorAsignatura() {
  }

  public String getCursoAcademico() {
    return cursoAcademico;
  }

  public void setCursoAcademico(String cursoAcademico) {
    this.cursoAcademico = cursoAcademico;
  }

  public boolean isOferta() {
    return oferta;
  }

  public void setOferta(boolean oferta) {
    this.oferta = oferta;
  }

  public List<Clase> getClases() {
    return clases;
  }

  public void setClases(List<Clase> clases) {
    this.clases = clases;
  }

  public List<Encuesta> getEncuestas() {
    return encuestas;
  }

  public void setEncuestas(List<Encuesta> encuestas) {
    this.encuestas = encuestas;
  }

  public Asignatura getAsignatura() {
    return asignatura;
  }

  public void setAsignatura(Asignatura asignatura) {
    this.asignatura = asignatura;
  }

  public Grupo getGrupo() {
    return grupo;
  }

  public void setGrupo(Grupo grupo) {
    this.grupo = grupo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GruposPorAsignatura that = (GruposPorAsignatura) o;
    return oferta == that.oferta && cursoAcademico.equals(that.cursoAcademico) && Objects
        .equals(clases, that.clases)
        && asignatura.equals(that.asignatura) && grupo.equals(that.grupo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cursoAcademico, oferta, clases, asignatura, grupo);
  }

  @Override
  public String toString() {
    return "GruposPorAsignatura{" +
        "cursoAcademico='" + cursoAcademico + '\'' +
        ", oferta=" + oferta +
        ", clases=" + clases +
        ", asignatura=" + asignatura +
        ", grupo=" + grupo +
        '}';
  }
}