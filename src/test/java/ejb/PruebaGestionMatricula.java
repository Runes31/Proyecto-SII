package ejb;

//import static org.junit.Assert.assertThrows;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
//import exceptions.GrupoNoEncontradoException;

public class PruebaGestionMatricula {

  private GestionMatricula gestionMatricula;
  
  @Before
  public void setup() throws NamingException  {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto-SII");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    //Crear base de batos con usando las importaciones
    em.getTransaction().commit();
  }

  @Before
  public void setUp() throws Exception {
    gestionMatricula = new GestionMatriculaEJB();
  }

  @Test
  @Requisitos({"001"})
  /**
   * Explicacion
   */
  public void generarAsignaciones() {
  }

  @Test()
  @Requisitos({"001"})
  /**
   * Explicacion
   */
  public void generarAsignacionesNoGrupo(){
    //assertThrows(GrupoNoEncontradoException.class, () -> gestionMatricula.generarAsignaciones());
  }
}