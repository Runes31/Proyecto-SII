package ejb;

import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({PruebaGestionMatricula.class,PruebaImportarDatos.class,PruebaModificar.class,
    PruebaModificarAlumnoEJB.class, PruebaModificarAsignaturaEJB.class, PruebaModificarExpedienteEJB.class})
public class SuiteTest {
  
  private static final Logger LOG = Logger.getLogger(SuiteTest.class.getCanonicalName());

  private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
  private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";

  public static EJBContainer ejbContainer;
  public static Context ctx;
  
  
  @BeforeClass
  public static void setUpClass()
      throws NamingException, CsvException, ParseException, IOException {
    Properties properties = new Properties();
    properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
    ejbContainer = EJBContainer.createEJBContainer(properties);
    ctx = ejbContainer.getContext();
    BaseDatos.inicializaBaseDatos("Proyecto-SII");
  }

  @AfterClass
  public static void tearDownClass() {
    if (ejbContainer != null) {
      ejbContainer.close();
    }
  }
}
