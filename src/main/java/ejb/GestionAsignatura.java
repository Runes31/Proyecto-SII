package ejb;

import domain.Grupo;
import exceptions.GrupoAsignaturaYaRelacionadoException;
import exceptions.GrupoNoEncontradoException;

import java.util.List;

import javax.ejb.Local;

import domain.Alumno;
import domain.Asignatura;
import exceptions.AlumnoNoEncontradoException;
import exceptions.AsignaturaNoEncontradaException;



@Local
public interface GestionAsignatura {
  /**
   * Actualizar una asignatura a partir de los datos de entrada
   * @param asignatura
   * @throws AsignaturaNoEncontradaException
   */
  public void actualizarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException;
  
  /**
   * Borrar una asignatura a partir de los datos de entrada
   * @param asignatura
   * @throws AsignaturaNoEncontradaException
   */
  public void borrarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException;


  /**
   * Añade un grupo a una asignatura
   * @param asignatura Asignatura a la que se le va a añadir un grupo
   * @param grupo Grupo al que se le añade una asignatura
   * @throws GrupoAsignaturaYaRelacionadoException Si el grupo ya tiene esa asignatura
   * @throws AsignaturaNoEncontradaException Si no existe esa asignatura
   * @throws GrupoNoEncontradoException Si no existe ese grupo
   */
  public void addGrupoAsignatura(Asignatura asignatura, Grupo grupo)
      throws GrupoAsignaturaYaRelacionadoException, AsignaturaNoEncontradaException, GrupoNoEncontradoException;


  /**
   * Encuentra una asignatura por su pk
   * @param referencia
   * @return Objeto tipo Asignatura
   * @throws AsignaturaNoEncontradaException
   */
  public Asignatura findAsignatura(String referencia) throws AsignaturaNoEncontradaException;
  
  public List<Asignatura> getAllAsignatura();
}
