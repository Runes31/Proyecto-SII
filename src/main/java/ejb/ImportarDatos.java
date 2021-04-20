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
   * Requisito 006 y Requisito 012
   * Importa los datos de los alumnos desde un fichero Excel o CSV
   * Además importa las preferecnias de los alumnos en la elección de grupos
   * @param fichero
   * @throws IOException 
   * @throws FileNotFoundException 
   * @throws CsvException 
   * @throws ParseException 
   */
  public void importarAlumnosCSV(File fichero) throws IOException, CsvException, ParseException;
  
  public void importarAlumnosExcel(File fichero);
  
  /**
   * Importa los datos de las titulaciones en CSV o Excel
   * @param fichero
   * @throws IOException 
   * @throws CsvException 
   * @throws FileNotFoundException 
   */
  public void importarTitulacionCSV(File fichero) throws IOException, CsvException;
  
  public void importarTitulacionExcel(File fichero);
  
  /**
   * Importa las asignaturas ofertadas en CSV o Excel
   * @param fichero
   * @throws IOException 
   * @throws CsvException 
   * @throws FileNotFoundException 
   */
  
  public void importarAsignaturanExcel(File fichero);

}
