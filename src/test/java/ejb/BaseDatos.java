package ejb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDatos {
  public static void inicializaBaseDatos(String nombreUnidadPersistencia) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
    EntityManager em = emf.createEntityManager();
    
       
    em.close();
    emf.close();
  }

}
