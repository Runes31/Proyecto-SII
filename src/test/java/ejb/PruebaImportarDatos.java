package ejb;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

public class PruebaImportarDatos {
  
  @Before
  public void setup() throws NamingException  {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto-SII");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    //Crear base de batos con usando las importaciones
    em.getTransaction().commit();
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
