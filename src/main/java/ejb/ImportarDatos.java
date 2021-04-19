package ejb;

import javax.ejb.Local;

@Local
public interface ImportarDatos {
  /**
   * Requisito 006
   * Importa los datos de los alumnos desde un fichero Excel o CSV
   */
  public void importarAlumnos();
  
  /**
   * Requisito 012
   * Importar los datos recopilados de las preferencias de los alumnos en la elección de grupos
   */
  public void importarPreferencias();

}
