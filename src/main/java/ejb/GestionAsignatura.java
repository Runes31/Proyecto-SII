package ejb;

import javax.ejb.Local;

import domain.Asignatura;
import exceptions.AsignaturaNoEncontradaException;



@Local
public interface GestionAsignatura {
  /**
   * Requisito 003
   * Actualizar una asignatura a partir de los datos de entrada
   * @param asignatura
   * @throws AsignaturaNoEncontradaException
   */
  public void actualizarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException;
  
  /**
   * Requisito 004
   * Borrar una asignatura a partir de los datos de entrada
   * @param asignatura
   * @throws AsignaturaNoEncontradaException
   */
  public void borrarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException;

}
