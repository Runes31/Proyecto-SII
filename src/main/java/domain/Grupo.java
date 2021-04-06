package domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Grupo {

  @Id @GeneratedValue
  private int id;
  @Column(unique = true, nullable = false)
  private String curso;
  @Column(unique = true, nullable = false)
  private String letra;
  @Column(nullable = false)
  private String turno;
  @Column(nullable = false)
  private boolean ingles;
  private boolean visible;
  private boolean asignar;
  private int plazas;
  @ManyToOne
  private Grupo grupo;
  @OneToMany(mappedBy = "grupo")
  private List<Grupo> grupos;
  @ManyToOne
  private Titulacion titulacion;
  @OneToMany(mappedBy = "grupo")
  private List<GruposPorAsignatura> gruposPorAsignatura;
  @OneToMany(mappedBy = "grupo")
  private List<Clase> clases;

  public Grupo() { }

  public Grupo(int id, String curso, String letra, String turno, boolean ingles, boolean visible, boolean asignar,
      int plazas) {
    super();
    this.id = id;
    this.curso = curso;
    this.letra = letra;
    this.turno = turno;
    this.ingles = ingles;
    this.visible = visible;
    this.asignar = asignar;
    this.plazas = plazas;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCurso() {
    return curso;
  }

  public void setCurso(String curso) {
    this.curso = curso;
  }

  public String getLetra() {
    return letra;
  }

  public void setLetra(String letra) {
    this.letra = letra;
  }

  public String getTurno() {
    return turno;
  }

  public void setTurno(String turno) {
    this.turno = turno;
  }

  public boolean isIngles() {
    return ingles;
  }

  public void setIngles(boolean ingles) {
    this.ingles = ingles;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  public boolean isAsignar() {
    return asignar;
  }

  public void setAsignar(boolean asignar) {
    this.asignar = asignar;
  }

  public int getPlazas() {
    return plazas;
  }

  public void setPlazas(int plazas) {
    this.plazas = plazas;
  }

  public Grupo getGrupo() {
    return grupo;
  }

  public void setGrupo(Grupo grupo) {
    this.grupo = grupo;
  }

  public List<Grupo> getGrupos() {
    return grupos;
  }

  public void setGrupos(List<Grupo> grupos) {
    this.grupos = grupos;
  }

  public Titulacion getTitulacion() {
    return titulacion;
  }

  public void setTitulacion(Titulacion titulacion) {
    this.titulacion = titulacion;
  }

  public List<GruposPorAsignatura> getGruposPorAsignatura() {
    return gruposPorAsignatura;
  }

  public void setGruposPorAsignatura(List<GruposPorAsignatura> gruposPorAsignatura) {
    this.gruposPorAsignatura = gruposPorAsignatura;
  }

  public List<Clase> getClases() {
    return clases;
  }

  public void setClases(List<Clase> clases) {
    this.clases = clases;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Grupo grupo1 = (Grupo) o;
    return id == grupo1.id && ingles == grupo1.ingles && visible == grupo1.visible
        && asignar == grupo1.asignar && plazas == grupo1.plazas && curso.equals(grupo1.curso)
        && letra
        .equals(grupo1.letra) && turno.equals(grupo1.turno) && Objects
        .equals(grupo, grupo1.grupo) && Objects.equals(grupos, grupo1.grupos)
        && Objects.equals(titulacion, grupo1.titulacion) && Objects
        .equals(gruposPorAsignatura, grupo1.gruposPorAsignatura) && Objects
        .equals(clases, grupo1.clases);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(id, curso, letra, turno, ingles, visible, asignar, plazas, grupo, grupos, titulacion,
            gruposPorAsignatura, clases);
  }

  @Override
  public String toString() {
    return "Grupo{" +
        "id=" + id +
        ", curso='" + curso + '\'' +
        ", letra='" + letra + '\'' +
        ", turno='" + turno + '\'' +
        ", ingles=" + ingles +
        ", visible=" + visible +
        ", asignar=" + asignar +
        ", plazas=" + plazas +
        ", grupo=" + grupo +
        ", grupos=" + grupos +
        ", titulacion=" + titulacion +
        ", gruposPorAsignatura=" + gruposPorAsignatura +
        ", clases=" + clases +
        '}';
  }

}
