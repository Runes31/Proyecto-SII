package ejb;

import javax.ejb.Local;
import domain.Alumno;
import exceptions.AlumnoNoEncontradoException;

@Local
public interface GestionAlumno {
  /**
   * Requisito 005
   * Actualizar un alumno a partir de los datos de entrada
   * @param alumno
   * @throws AlumnoNoEncontradoException
   */
  public void actualizarAlumno(Alumno alumno) throws AlumnoNoEncontradoException;
}
