package ejb;

import static org.junit.Assert.*;

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
  
  private static final String UNIDAD_PERSITENCIA_PRUEBAS = "Proyecto-SII";
  private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
  private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
  
  private static EJBContainer ejbContainer;
  private static Context ctx;
  
  @BeforeClass
  public static void setUpClass() {
    Properties properties = new Properties();
    properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
    ejbContainer = EJBContainer.createEJBContainer(properties);
    ctx = ejbContainer.getContext();
  }
  
    
    
  @AfterClass
  public static void tearDownClass() {
    if (ejbContainer != null) {
      ejbContainer.close();
    }
  }
}
