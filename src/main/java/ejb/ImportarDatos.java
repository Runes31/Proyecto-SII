package ejb;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.ejb.Local;

import com.opencsv.exceptions.CsvException;

@Local
public interface ImportarDatos {
  /**
   * Requisito 006
   * Importa los datos de los alumnos desde un fichero Excel o CSV
   * @param pathFichero
   * @throws IOException 
   * @throws FileNotFoundException 
   * @throws CsvException 
   */
  public void importarAlumnosCSV(String pathFichero) throws FileNotFoundException, IOException, CsvException;
  
  /**
   * Requisito 012
   * Importar los datos recopilados de las preferencias de los alumnos en la elección de grupos
   */
  public void importarPreferenciasCSV();

}
