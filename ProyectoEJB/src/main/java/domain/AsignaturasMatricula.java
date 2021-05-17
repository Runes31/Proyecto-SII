package domain;

import domain.Asignatura.AsignaturaId;
import domain.AsignaturasMatricula.AsignaturasMatriculaId;
import domain.Matricula.MatriculaId;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(AsignaturasMatriculaId.class)
public class AsignaturasMatricula {

  public static class AsignaturasMatriculaId implements Serializable {
    private MatriculaId matricula;
    private AsignaturaId asignatura;
  }

  @Id
  @ManyToOne
  private Matricula matricula;

  @Id
  @ManyToOne
  private Asignatura asignatura;

  @ManyToOne
  private Grupo grupo;

  public AsignaturasMatricula() {}

  public Matricula getMatricula() {
    return matricula;
  }

  public void setMatricula(Matricula matricula) {
    this.matricula = matricula;
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
    AsignaturasMatricula that = (AsignaturasMatricula) o;
    return matricula.equals(that.matricula) && asignatura.equals(that.asignatura) && Objects
        .equals(grupo, that.grupo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(matricula, asignatura, grupo);
  }

  @Override
  public String toString() {
    return "AsginaturasMatricula{" +
        "matricula=" + matricula +
        ", asignatura=" + asignatura +
        ", grupo=" + grupo +
        '}';
  }
}
