package domain;

import javax.persistence.*;

@Entity
public class Centro {

  @Id @GeneratedValue
  private String ID;
  @Column(unique = true,nullable = false)
  private String Nombre;
  @Column(nullable = false)
  private String Dirección;
  private int TLF_Consejeria;
  
	public Centro() { }

  public String getID() {
    return ID;
  }

  public void setID(String iD) {
    ID = iD;
  }

  public String getNombre() {
    return Nombre;
  }

  public void setNombre(String nombre) {
    Nombre = nombre;
  }

  public String getDirección() {
    return Dirección;
  }

  public void setDirección(String dirección) {
    Dirección = dirección;
  }

  public int getTLF_Consejeria() {
    return TLF_Consejeria;
  }

  public void setTLF_Consejeria(int tLF_Consejeria) {
    TLF_Consejeria = tLF_Consejeria;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((Dirección == null) ? 0 : Dirección.hashCode());
    result = prime * result + ((ID == null) ? 0 : ID.hashCode());
    result = prime * result + ((Nombre == null) ? 0 : Nombre.hashCode());
    result = prime * result + TLF_Consejeria;
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
    if (Dirección == null) {
      if (other.Dirección != null)
        return false;
    } else if (!Dirección.equals(other.Dirección))
      return false;
    if (ID == null) {
      if (other.ID != null)
        return false;
    } else if (!ID.equals(other.ID))
      return false;
    if (Nombre == null) {
      if (other.Nombre != null)
        return false;
    } else if (!Nombre.equals(other.Nombre))
      return false;
    if (TLF_Consejeria != other.TLF_Consejeria)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Centro [ID=" + ID + ", Nombre=" + Nombre + ", Dirección=" + Dirección + ", TLF_Consejeria=" + TLF_Consejeria
        + "]";
  }
   
	
}
