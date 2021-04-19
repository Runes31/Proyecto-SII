package ejb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

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
   * @throws ParseException 
   */
  public void importarAlumnosCSV(File fichero) throws FileNotFoundException, IOException, CsvException, ParseException;
  
  public void importarAlumnosExcel(File fichero) throws IOException;
  
  /**
   * Requisito 012
   * Importar los datos recopilados de las preferencias de los alumnos en la elección de grupos
   */
  public void importarPreferenciasCSV();

  public void importarPreferenciasExcel();

  

}
