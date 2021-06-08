package domain;

import java.util.List;
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

   @Override
   public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
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
	Grupo other = (Grupo) obj;
	if (id != other.id)
		return false;
	return true;
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
        ", titulacion=" + titulacion +
        '}';
  }

}
