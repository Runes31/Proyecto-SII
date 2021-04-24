package ejb;

import domain.Alumno;
import exceptions.AlumnoNoEncontradoException;
import java.util.List;
import javax.ejb.Local;

@Local
public interface GestionAlumno {
  /**
   * Actualizar un alumno a partir de los datos de entrada
   * @param alumno
   * @throws AlumnoNoEncontradoException
   */
  void actualizarAlumno(Alumno alumno) throws AlumnoNoEncontradoException;

  /**
   * Encuentra a un alumno por su pk
   * @param id Id del alumno
   * @return Objeto tipo Alumno
   * @throws AlumnoNoEncontradoException
   */
  Alumno findAlumno(int id) throws AlumnoNoEncontradoException;

  /**
   * Genera una lista con todos los alumnos
   * @return List con los alumnos
   */
  List<Alumno> getAllAlumnos();
}
