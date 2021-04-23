package ejb;

import java.util.List;
import javax.ejb.Local;
import domain.Alumno;
import exceptions.AlumnoNoEncontradoException;

@Local
public interface GestionAlumno {
  /**
   * Actualizar un alumno a partir de los datos de entrada
   * @param alumno
   * @throws AlumnoNoEncontradoException
   */
  public void actualizarAlumno(Alumno alumno) throws AlumnoNoEncontradoException;

  /**
   * Encuentra a un alumno por su pk
   * @param id Id del alumno
   * @return Objeto tipo Alumno
   * @throws AlumnoNoEncontradoException
   */
  public Alumno findAlumno(int id) throws AlumnoNoEncontradoException;

  /**
   * Genera una lista con todos los alumnos
   * @return List con los alumnos
   */
  public List<Alumno> getAllAlumnos();
}
