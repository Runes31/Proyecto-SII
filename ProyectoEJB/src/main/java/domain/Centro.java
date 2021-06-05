package domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Centro {

  @Id @GeneratedValue
  private int id;
  @Column(unique = true,nullable = false)
  private String nombre;
  @Column(nullable = false)
  private String direccion;
  private int tlfConsejeria;
  @ManyToMany
  @JoinTable(
      name = "centro_titulacion",
      joinColumns = @JoinColumn(name = "centro_id"),
      inverseJoinColumns = @JoinColumn(name = "titulacion_id")
  )
  private List<Titulacion> titulaciones;
  
  public Centro() { }

  public int getId() {
    return id;
  }

  public void setId(int iD) {
    id = iD;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public int getTlfConsejeria() {
    return tlfConsejeria;
  }

  public void setTlfConsejeria(int tLFconsejeria) {
    tlfConsejeria = tLFconsejeria;
  }
  
  public List<Titulacion> getTitulaciones() {
    return titulaciones;
  }

  public void setTitulaciones(List<Titulacion> titulaciones) {
    this.titulaciones = titulaciones;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Centro centro = (Centro) o;
    return id == centro.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Centro{" +
        "ID=" + id +
        ", nombre='" + nombre + '\'' +
        ", direccion='" + direccion + '\'' +
        ", TLFconsejeria=" + tlfConsejeria +
        '}';
  }

}
