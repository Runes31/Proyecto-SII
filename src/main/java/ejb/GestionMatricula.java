package ejb;


import javax.ejb.Local;

import domain.Matricula;
import exceptions.MatriculaNoEncontradaException;


@Local
public interface GestionMatricula {
  /**
   * Requisito 008
   * Actualizar una matricula a partir de los datos de entrada
   * @param matricula
   * @throws MatriculaNoEncontradaException
   */
  public void actualizarMatricula(Matricula matricula) throws MatriculaNoEncontradaException ;
  
  public void visualizarMatricula(Matricula matricula) throws MatriculaNoEncontradaException ;
  
}
