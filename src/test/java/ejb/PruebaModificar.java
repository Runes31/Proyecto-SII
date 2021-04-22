package ejb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.opencsv.exceptions.CsvException;


public class PruebaModificar {
  
  @Before
  public void setup() throws NamingException, FileNotFoundException, IOException, CsvException, ParseException  {
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
