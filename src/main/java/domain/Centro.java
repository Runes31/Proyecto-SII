package domain;

import java.util.List;

import javax.persistence.*;

@Entity
public class Centro {

  @Id @GeneratedValue
  private int ID;
  @Column(unique = true,nullable = false)
  private String nombre;
  @Column(nullable = false)
  private String direcci�n;
  private int TLFconsejeria;
  @ManyToMany(mappedBy = "centros")
  private List<Titulacion> titulaciones;
  
  public Centro() { }

  public int getID() {
    return ID;
  }

  public void setID(int iD) {
    ID = iD;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDirecci�n() {
    return direcci�n;
  }

  public void setDirecci�n(String direcci�n) {
    this.direcci�n = direcci�n;
  }

  public int getTLFconsejeria() {
    return TLFconsejeria;
  }

  public void setTLFconsejeria(int tLFconsejeria) {
    TLFconsejeria = tLFconsejeria;
  }
  
  public List<Titulacion> getTitulaciones() {
    return titulaciones;
  }

  public void setTitulaciones(List<Titulacion> titulaciones) {
    this.titulaciones = titulaciones;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ID;
    result = prime * result + TLFconsejeria;
    result = prime * result + ((direcci�n == null) ? 0 : direcci�n.hashCode());
    result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
    result = prime * result + ((titulaciones == null) ? 0 : titulaciones.hashCode());
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
    Centro other = (Centro) obj;
    if (ID != other.ID)
      return false;
    if (TLFconsejeria != other.TLFconsejeria)
      return false;
    if (direcci�n == null) {
      if (other.direcci�n != null)
        return false;
    } else if (!direcci�n.equals(other.direcci�n))
      return false;
    if (nombre == null) {
      if (other.nombre != null)
        return false;
    } else if (!nombre.equals(other.nombre))
      return false;
    if (titulaciones == null) {
      if (other.titulaciones != null)
        return false;
    } else if (!titulaciones.equals(other.titulaciones))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Centro [ID=" + ID + ", nombre=" + nombre + ", direcci�n=" + direcci�n + ", TLFconsejeria=" + TLFconsejeria
        + ", titulaciones=" + titulaciones + "]";
  }

}
