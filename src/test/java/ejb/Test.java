package ejb;

import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class Test {

  private static final Logger LOG = Logger.getLogger(Test.class.getCanonicalName());


  private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
  private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
  private static final String UNIDAD_PERSITENCIA_PRUEBAS = "Proyecto-SII";
  
  private static final String GESTION_MATRICULA_EJB = "java:global/classes/GestionMatriculaEJB";
  private static final String IMPORTAR_DATOS_EJB = "java:global/classes/ImportarDatosEJB";
  private static final String MODIFICAR_ALUMNO_EJB = "java:global/classes/ModificarAlumnoEJB";
  private static final String MODIFICAR_ASIGNATURA_EJB = "java:global/classes/ModificarAsignaturaEJB";
  private static final String MODIFICAR_EXPEDIENTE_EJB = "java:global/classes/ModificarExpedienteEJB";
  
  private static EJBContainer ejbContainer;
  private static Context ctx;
  
  
  @BeforeClass
  public static void setUpClass() {
    Properties properties = new Properties();
    properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
    ejbContainer = EJBContainer.createEJBContainer(properties);
    ctx = ejbContainer.getContext();
  }
  
  @Before
  public void setup() throws NamingException  {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(UNIDAD_PERSITENCIA_PRUEBAS);
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    //TODO: Crear base de batos con usando las importaciones
    em.getTransaction().commit();
  }
  
    
    
  @AfterClass
  public static void tearDownClass() {
    if (ejbContainer != null) {
      ejbContainer.close();
    }
  }
}
