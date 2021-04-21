package ejb;

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
}
