package ejb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.opencsv.exceptions.CsvException;

import ejb.ImportarDatosEJB;

public class BaseDatos {

  public static void inicializaBaseDatos(String nombreUnidadPersistencia)
      throws FileNotFoundException, IOException, CsvException, ParseException, NamingException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
    EntityManager em = emf.createEntityManager();

    ImportarDatos importarDatos = (ImportarDatos) SuiteTest.ctx.lookup("java:global/classes/ImportarDatosEJB");
    
    File t = new File("DATOS/Titulacion.xlsx");
    importarDatos.importarTitulacionExcel(t);
    File file = new File("DATOS/Oferta_asignaturas.xlsx");
    importarDatos.importarAsignaturasExcel(file);
    importarDatos.importarAlumnosExcel(new File("DATOS/alumnos.xlsx"));
  }
}