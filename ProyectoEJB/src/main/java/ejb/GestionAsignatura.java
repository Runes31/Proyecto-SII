package ejb;

import domain.Asignatura;
import domain.Grupo;
import exceptions.AsignaturaNoEncontradaException;
import exceptions.GrupoAsignaturaYaRelacionadoException;
import exceptions.GrupoNoEncontradoException;
import java.util.List;
import javax.ejb.Local;



@Local
public interface GestionAsignatura {
  /**
   * Actualizar una asignatura a partir de los datos de entrada
   * @param asignatura
   * @throws AsignaturaNoEncontradaException
   */
  void actualizarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException;
  
  /**
   * Da de baja una asignatura para que no sea ofertada ese curso
   * @param asignatura
   * @throws AsignaturaNoEncontradaException
   */
  void borrarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException;

  /**
   * Añade un grupo a una asignatura
   * @param asignatura Asignatura a la que se le va a añadir un grupo
   * @param grupo Grupo al que se le añade una asignatura
   * @throws GrupoAsignaturaYaRelacionadoException Si el grupo ya tiene esa asignatura
   * @throws AsignaturaNoEncontradaException Si no existe esa asignatura
   * @throws GrupoNoEncontradoException Si no existe ese grupo
   */
  void addGrupoAsignatura(Asignatura asignatura, Grupo grupo)
      throws GrupoAsignaturaYaRelacionadoException, AsignaturaNoEncontradaException, GrupoNoEncontradoException;


  /**
   * Encuentra una asignatura por su pk
   * @param referencia
   * @param codigo Codigo de la titulacion a la que pertenece
   * @return Objeto tipo Asignatura
   * @throws AsignaturaNoEncontradaException
   */
  Asignatura findAsignatura(String referencia, String codigo) throws AsignaturaNoEncontradaException;

  /**
   * Genera una lista con todas las asignaturas
   * @return
   */
  List<Asignatura> getAllAsignatura();
}
