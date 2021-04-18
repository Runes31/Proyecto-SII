package ejb;

import javax.ejb.Local;

import domain.Asignatura;
import exceptions.AsignaturaNoEncontradaException;

@Local
public interface GestionAsignatura {
  
  public void actualizarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException;

}
