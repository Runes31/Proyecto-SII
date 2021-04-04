package domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Alumno {
  // TODO: Comprobar que están todos los atributos
  @Id @GeneratedValue
  private int id;
  @Column(unique = true, nullable = false)
  private String DNI;
  @Column(nullable = false)
  private String nombre;
  @Column(nullable = false)
  private String emailInstitucional;
  private String emailPersonal;
  private String telefono;
  private String movil;
  @OneToMany
  private List<Expediente> expedientes;

  public Alumno() { }

  public Alumno(String DNI, String nombre, String emailInstitucional, String emailPersonal,
      String telefono, String movil) {
    this.DNI = DNI;
    this.nombre = nombre;
    this.emailInstitucional = emailInstitucional;
    this.emailPersonal = emailPersonal;
    this.telefono = telefono;
    this.movil = movil;
  }

  public Alumno(String DNI, String nombre, String emailInstitucional,
      String emailPersonal, String telefono, String movil,
      List<Expediente> expedientes) {
    this.DNI = DNI;
    this.nombre = nombre;
    this.emailInstitucional = emailInstitucional;
    this.emailPersonal = emailPersonal;
    this.telefono = telefono;
    this.movil = movil;
    this.expedientes = expedientes;
  }

  public int getId() {
    return id;
  }

  public String getDNI() {
    return DNI;
  }

  public void setDNI(String DNI) {
    this.DNI = DNI;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmailInstitucional() {
    return emailInstitucional;
  }

  public void setEmailInstitucional(String emailInstitucional) {
    this.emailInstitucional = emailInstitucional;
  }

  public String getEmailPersonal() {
    return emailPersonal;
  }

  public void setEmailPersonal(String emailPersonal) {
    this.emailPersonal = emailPersonal;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getMovil() {
    return movil;
  }

  public void setMovil(String movil) {
    this.movil = movil;
  }

  public List<Expediente> getExpedientes() {
    return expedientes;
  }

  public void setExpedientes(List<Expediente> expedientes) {
    this.expedientes = expedientes;
  }
  
  @Override
  public int hashCode() {
	  final int prime = 31;
	  int result = 1;
	  result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
	  result = prime * result + ((emailInstitucional == null) ? 0 : emailInstitucional.hashCode());
	  result = prime * result + ((emailPersonal == null) ? 0 : emailPersonal.hashCode());
	  result = prime * result + ((expedientes == null) ? 0 : expedientes.hashCode());
	  result = prime * result + id;
	  result = prime * result + ((movil == null) ? 0 : movil.hashCode());
	  result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
	  result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
	  Alumno other = (Alumno) obj;
	  if (DNI == null) {
		  if (other.DNI != null)
			  return false;
	  } else if (!DNI.equals(other.DNI))
		  return false;
	  if (emailInstitucional == null) {
		  if (other.emailInstitucional != null)
			  return false;
	  } else if (!emailInstitucional.equals(other.emailInstitucional))
		  return false;
	  if (emailPersonal == null) {
		  if (other.emailPersonal != null)
			  return false;
	  } else if (!emailPersonal.equals(other.emailPersonal))
			  return false;
		if (expedientes == null) {
			if (other.expedientes != null)
				return false;
		} else if (!expedientes.equals(other.expedientes))
			return false;
		if (id != other.id)
			return false;
		if (movil == null) {
			if (other.movil != null)
				return false;
		} else if (!movil.equals(other.movil))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
		}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", DNI=" + DNI + ", nombre=" + nombre + ", emailInstitucional=" + emailInstitucional
				+ ", emailPersonal=" + emailPersonal + ", telefono=" + telefono + ", movil=" + movil + ", expedientes="
				+ expedientes + "]";
	}
  
  
}