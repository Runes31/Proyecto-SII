package ejb;

import javax.ejb.Local;

import domain.Asignatura;
import exceptions.AsignaturaNoEncontradaException;



@Local
public interface GestionAsignatura {
  /**
   * Actualizar una asignatura a partir de los datos de entrada
   * @param asignatura
   * @throws AsignaturaNoEncontradaException
   */
  public void actualizarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException;
  
  /**
   * Borrar una asignatura a partir de los datos de entrada
   * @param asignatura
   * @throws AsignaturaNoEncontradaException
   */
  public void borrarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException;

}
