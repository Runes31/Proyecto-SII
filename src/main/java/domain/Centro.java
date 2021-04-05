package domain;

import javax.persistence.*;

@Entity
public class Centro {

  @Id @GeneratedValue
  private int ID;
  @Column(unique = true,nullable = false)
  private String nombre;
  @Column(nullable = false)
  private String dirección;
  private int TLFconsejeria;
  
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

  public String getDirección() {
    return dirección;
  }

  public void setDirección(String dirección) {
    this.dirección = dirección;
  }

  public int getTLFconsejeria() {
    return TLFconsejeria;
  }

  public void setTLFconsejeria(int tLFconsejeria) {
    TLFconsejeria = tLFconsejeria;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ID;
    result = prime * result + TLFconsejeria;
    result = prime * result + ((dirección == null) ? 0 : dirección.hashCode());
    result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
    if (dirección == null) {
      if (other.dirección != null)
        return false;
    } else if (!dirección.equals(other.dirección))
      return false;
    if (nombre == null) {
      if (other.nombre != null)
        return false;
    } else if (!nombre.equals(other.nombre))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Centro [ID=" + ID + ", nombre=" + nombre + ", dirección=" + dirección + ", TLFconsejeria=" + TLFconsejeria
        + "]";
  }

}
