package ejb;

import domain.Asignatura;
import domain.AsignaturasMatricula;
import domain.Encuesta;
import domain.Grupo;
import domain.GruposPorAsignatura;
import domain.Matricula;
import exceptions.AsignaturaNoEncontradaException;
import exceptions.GrupoNoEncontradoException;
import exceptions.MatriculaNoEncontradaException;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;



@Local
public interface GestionMatricula {
  /**
   * Actualizar una matricula a partir de los datos de entrada
   * @param matricula
   * @throws MatriculaNoEncontradaException
   */
  void actualizarMatricula(Matricula matricula) throws MatriculaNoEncontradaException ;
  
  /**
   * Visualizar todos las matriculas 
   * @return List con las matriculas
   */
  
  List<Matricula> getAllMatriculas();

  /**
   * Asigna grupos a los alumnos que no tengan
   */
  void generarAsignaciones();

  /**
   * Genera una lista con todas las asignaciones de grupo
   * @return List con las asignaciones
   */
  List<AsignaturasMatricula> listarAsignaciones();

  /**
   * Genera una lista con todas las asignaciones de grupo filtrando si es de nuevo ingreso o no
   * @param nuevoIngreso boolean para filtrar si es de nuevo ingreso o no
   * @return List con las asignaciones
   */
  List<AsignaturasMatricula> listarAsignaciones(boolean nuevoIngreso);

  /**
   * Obtiene las preferencias de un alumno
   * @param matricula Matricula del alumno
   * @return Encuesta con las preferencias que rellenó
   * @throws MatriculaNoEncontradaException Si no existe la matricula
   */
  Encuesta obtenerPreferencias(Matricula matricula) throws MatriculaNoEncontradaException;

  /**
   * Guarda las nuevas preferencias del alumno, independientemente de si ya había rellenado una
   * encuesta antes o no
   * @param encuesta Encuesta con las preferencias a guardar
   */
  void guardarPreferencias(Encuesta encuesta);

  /**
   * Encuentra una matricula por su pk
   * @param cursoAcademico
   * @param expediente
   * @return Objeto tipo Matricula
   * @throws MatriculaNoEncontradaException
   */
  Matricula findMatricula(String cursoAcademico, int expediente) throws MatriculaNoEncontradaException;

  /**
   * Asigna de forma manual un grupo a un alumno
   * @param matricula
   * @param asignatura
   * @param grupo
   */
  void asignarGrupo(Matricula matricula, Asignatura asignatura, Grupo grupo)
      throws MatriculaNoEncontradaException, AsignaturaNoEncontradaException, GrupoNoEncontradoException;
}
