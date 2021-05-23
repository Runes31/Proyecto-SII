package ejb;

import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javax.naming.NamingException;

public class BaseDatos {

  public static void inicializaBaseDatos(String nombreUnidadPersistencia)
      throws FileNotFoundException, IOException, CsvException, ParseException, NamingException {
    ImportarDatos importarDatos = (ImportarDatos) SuiteTest.ctx.lookup("java:global/classes/ImportarDatosEJB");
    
    File t = new File("../DATOS/Titulacion.xlsx");
    importarDatos.importarTitulacionExcel(t);
    File file = new File("../DATOS/Oferta_asignaturas.xlsx");
    importarDatos.importarAsignaturasExcel(file);
    importarDatos.importarAlumnosExcel(new File("../DATOS/alumnos.xlsx"));
    importarDatos.importarGruposExcel(new File("../DATOS/grupos.xlsx"));
    importarDatos.importarEncuestaExcel(new File("../DATOS/Encuesta.xlsx"));
  }
}