package ejb;

import static org.junit.Assert.*;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

public class ModificarTest {
  
  @Before
  public void setup() throws NamingException  {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto-SII");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    //TODO: Crear base de batos con usando las importaciones
    em.getTransaction().commit();
  }

  @Test
  public void testModificarAlumno() {
    //TODO: Comprobar que se puede modificar un alumno    
  }
  
  @Test
  public void testModificarAsignatura() {
    //TODO: Comprobar que se puede modificar una asignatura
  }
  
  @Test
  public void testModificarExpediente() {
    //TODO: Comprobar que se puede modificar un expediente
  }

}
