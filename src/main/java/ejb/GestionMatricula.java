package ejb;

import domain.AsignaturasMatricula;
import exceptions.GrupoNoEncontradoException;
import java.util.List;
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

  /**
   * Asigna grupos a los alumnos que no tengan
   * @throws GrupoNoEncontradoException Si no existen grupos disponibles para una asignatura
   */
  public void generarAsignaciones() throws GrupoNoEncontradoException;

  /**
   * Genera una lista con todas las asignaciones de grupo
   * @return List con las asignaciones
   */
  public List<AsignaturasMatricula> listarAsignaciones();

  /**
   * Genera una lista con todas las asignaciones de grupo filtrando si es de nuevo ingreso o no
   * @param nuevoIngreso boolean para filtrar si es de nuevo ingreso o no
   * @return List con las asignaciones
   */
  public List<AsignaturasMatricula> listarAsignaciones(boolean nuevoIngreso);
}
