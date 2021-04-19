package ejb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import domain.Alumno;

@Stateless
public class ImportarDatosEJB implements ImportarDatos{

  @PersistenceContext(name="Proyecto-SII")
  private EntityManager em;
  
  @Override
  public void importarAlumnosCSV(String pathFichero) throws FileNotFoundException, IOException, CsvException {
    try (CSVReader reader = new CSVReader(new FileReader("pathFichero"))) {
      List<String[]> l = reader.readAll();
      for(String[] str : l) {
        Alumno al = new Alumno(str[0].toString(),str[1].toString(),str[2].toString(),str[3].toString(),str[4].toString(),str[5].toString());
        em.persist(al);
      }
    }
    
    
  }

  @Override
  public void importarPreferenciasCSV() {
    // TODO Auto-generated method stub
    
  }

}
