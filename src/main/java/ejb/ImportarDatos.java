package ejb;

import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import javax.ejb.Local;

@Local
public interface ImportarDatos {
  
  /**
   * Importa los datos de los alumnos desde un fichero Excel
   * @param fichero
   */
  void importarAlumnosExcel(File fichero);

  /**
   * Importa los datos de las titulaciones en Excel
   * @param fichero
   */
  void importarTitulacionExcel(File fichero);
  
  /**
   * Importa las asignaturas ofertadas en Excel
   * @param fichero
   */
  
  void importarAsignaturasExcel(File fichero);

  /**
   * Importa las preferencias de los alumnos desde un excel
   * @param fichero
   */
  void importarEncuestaExcel(File fichero);

  /**
   * Importa los grupos y las asignaturas que tienen
   * @param fichero
   */
  void importarGruposExcel(File fichero);
}
