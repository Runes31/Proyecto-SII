package ejb;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

public class PruebaImportarDatos {
  
  private static final Logger LOG = Logger.getLogger(PruebaImportarDatos.class.getCanonicalName());

  
  @Before
  public void setup() throws NamingException  {
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
