package ejb;

import exceptions.GrupoNoEncontradoException;
import javax.ejb.Local;

import domain.Matricula;
import exceptions.MatriculaNoEncontradaException;


@Local
public interface GestionMatricula {
  /**
   * Actualizar una matricula a partir de los datos de entrada
   * @param matricula
   * @throws MatriculaNoEncontradaException
   */
  public void actualizarMatricula(Matricula matricula) throws MatriculaNoEncontradaException ;
  
  /**
   * Visualizar todos los datos de la matr�cula solicita como entrada
   * @param matricula
   * @throws MatriculaNoEncontradaException
   */
  
  public void visualizarMatricula(Matricula matricula) throws MatriculaNoEncontradaException ;

  public void generarAsignaciones() throws GrupoNoEncontradoException;
}
