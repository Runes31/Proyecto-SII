package ejb;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;


public class PruebaModificar {
  
  private static final Logger LOG = Logger.getLogger(PruebaModificar.class.getCanonicalName());

  
  @Before
  public void setup() throws NamingException  {
    BaseDatos.inicializaBaseDatos("Proyecto-SII");
  }
  
  @Test
  public void testModificarAlumno() {
    //Comprobar que se puede modificar un alumno    
  }
  
  @Test
  public void testModificarAsignatura() {
    //Comprobar que se puede modificar una asignatura
  }
  
  @Test
  public void testModificarExpediente() {
    //Comprobar que se puede modificar un expediente
  }

}
