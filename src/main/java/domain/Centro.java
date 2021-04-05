package domain;

import javax.persistence.*;

@Entity
public class Centro {

  @Id @GeneratedValue
  private String ID;
  @Column(unique = true,nullable = false)
  private String Nombre;
  @Column(nullable = false)
  private String Direcci�n;
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

  public String getDirecci�n() {
    return Direcci�n;
  }

  public void setDirecci�n(String direcci�n) {
    Direcci�n = direcci�n;
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
    result = prime * result + ((Direcci�n == null) ? 0 : Direcci�n.hashCode());
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
    if (Direcci�n == null) {
      if (other.Direcci�n != null)
        return false;
    } else if (!Direcci�n.equals(other.Direcci�n))
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
    return "Centro [ID=" + ID + ", Nombre=" + Nombre + ", Direcci�n=" + Direcci�n + ", TLF_Consejeria=" + TLF_Consejeria
        + "]";
  }
   
	
}
