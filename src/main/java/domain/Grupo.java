package domain;

import javax.persistence.*;

@Entity
public class Grupo {

  @Id
  private int id;
  @Column(unique = true, nullable = false)
  private String curso;
  @Column(unique = true, nullable = false)
  private String letra;
  @Column(nullable = false)
  private String Turno;
  @Column(nullable = false)
  private boolean ingles;
  private boolean visible;
  private boolean asignar;
  private int plazas;
  
  public Grupo() {
    
  }

  public Grupo(int id, String curso, String letra, String turno, boolean ingles, boolean visible, boolean asignar,
      int plazas) {
    super();
    this.id = id;
    this.curso = curso;
    this.letra = letra;
    Turno = turno;
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
    return Turno;
  }

  public void setTurno(String turno) {
    Turno = turno;
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

  @Override
  public String toString() {
    return "Grupo [id=" + id + ", curso=" + curso + ", letra=" + letra + ", Turno=" + Turno + ", ingles=" + ingles
        + ", visible=" + visible + ", asignar=" + asignar + ", plazas=" + plazas + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((Turno == null) ? 0 : Turno.hashCode());
    result = prime * result + (asignar ? 1231 : 1237);
    result = prime * result + ((curso == null) ? 0 : curso.hashCode());
    result = prime * result + id;
    result = prime * result + (ingles ? 1231 : 1237);
    result = prime * result + ((letra == null) ? 0 : letra.hashCode());
    result = prime * result + plazas;
    result = prime * result + (visible ? 1231 : 1237);
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
    if (Turno == null) {
      if (other.Turno != null)
        return false;
    } else if (!Turno.equals(other.Turno))
      return false;
    if (asignar != other.asignar)
      return false;
    if (curso == null) {
      if (other.curso != null)
        return false;
    } else if (!curso.equals(other.curso))
      return false;
    if (id != other.id)
      return false;
    if (ingles != other.ingles)
      return false;
    if (letra == null) {
      if (other.letra != null)
        return false;
    } else if (!letra.equals(other.letra))
      return false;
    if (plazas != other.plazas)
      return false;
    if (visible != other.visible)
      return false;
    return true;
  }
}
