package ejb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.opencsv.exceptions.CsvException;

import domain.Asignatura;
import exceptions.AsignaturaNoEncontradaException;

public class PruebaImportarDatos {
  
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
  public void testImportarAsignaturas() throws AsignaturaNoEncontradaException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto-SII");
    EntityManager em = emf.createEntityManager();
    
    Asignatura asig = em.find(Asignatura.class, "50659");
    if(asig == null) throw new AsignaturaNoEncontradaException();
    em.close();
    emf.close();
  }

}
