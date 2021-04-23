package ejb;

import domain.Asignatura;
import domain.AsignaturasMatricula;
import domain.Encuesta;
import domain.GruposPorAsignatura;
import exceptions.AsignaturaNoEncontradaException;
import exceptions.GrupoNoEncontradoException;


import java.util.List;
import java.util.Map;
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
   * Visualizar todos las matriculas 
   * @return List con las matriculas
   */
  
  public List<Matricula> getAllMatriculas();

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

  /**
   * Genera la lista de opciones que tiene un alumno para cada asignatura en la que se ha matriculado
   * @param matricula Matricula del alumno en cuestión
   * @return Map que de key tiene la asignatura y de value los posibles grupos
   * @throws MatriculaNoEncontradaException Si no existe la matricula
   */
  public Map<Asignatura, List<GruposPorAsignatura>> generarEncuesta(Matricula matricula) throws MatriculaNoEncontradaException;

  /**
   * Obtiene las preferencias de un alumno
   * @param matricula Matricula del alumno
   * @return Encuesta con las preferencias que rellenó
   * @throws MatriculaNoEncontradaException Si no existe la matricula
   */
  public Encuesta obtenerPreferencias(Matricula matricula) throws MatriculaNoEncontradaException;

  /**
   * Guarda las nuevas preferencias del alumno, independientemente de si ya había rellenado una
   * encuesta antes o no
   * @param encuesta Encuesta con las preferencias a guardar
   */
  public void guardarPreferencias(Encuesta encuesta);

  /**
   * Encuentra una matricula por su pk
   * @param curso academico
   * @return Objeto tipo Matricula
   * @throws MatriculaaNoEncontradaException
   */
  public Matricula findMatricula(String cursoAcademico) throws MatriculaNoEncontradaException;
}
