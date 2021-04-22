package ejb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.opencsv.exceptions.CsvException;

import ejb.ImportarDatosEJB;

public class BaseDatos {
  public static void inicializaBaseDatos(String nombreUnidadPersistencia) throws FileNotFoundException, IOException, CsvException, ParseException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
    EntityManager em = emf.createEntityManager();
    
    ImportarDatosEJB importarDatos = new ImportarDatosEJB();
    File file = new File("D:\\Universidad - Informática\\Tercero\\Sistemas de Información para Internet\\Trabajo\\Tarea 1\\ProyectoSII\\Proyecto-SII\\DATOS\\Oferta asignaturas.xlsx");
    importarDatos.importarAsignaturasExcel(file);
    
    em.close();
    emf.close();
  }

}
