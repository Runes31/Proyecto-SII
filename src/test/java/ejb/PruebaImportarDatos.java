package ejb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.opencsv.exceptions.CsvException;

public class PruebaImportarDatos {
  
  private static final Logger LOG = Logger.getLogger(PruebaImportarDatos.class.getCanonicalName());

  
  @Before
  public void setup() throws NamingException, FileNotFoundException, IOException, CsvException, ParseException  {
    BaseDatos.inicializaBaseDatos("Proyecto-SII");
  }

  @Test
  public void testImportarAlumnos() {
    //Comprobar que las importaciones son correctas    
  }
  
  @Test
  public void testImportarTitulaciones() {
    //Comprobar que las importaciones son correctas    
  }
  
  @Test
  public void testImportarAsignaturas() {
    //Comprobar que las importaciones son correctas    
  }

}
