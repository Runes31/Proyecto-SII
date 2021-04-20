package ejb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.io.FileInputStream;  
import java.util.Iterator; 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import domain.Alumno;
import domain.Asignatura;
import domain.Expediente;
import domain.Matricula;
import domain.Optativa;
import domain.Titulacion;

@Stateless
public class ImportarDatosEJB implements ImportarDatos{

  @PersistenceContext(name="Proyecto-SII")
  private EntityManager em;
  
  @Override
  public void importarAlumnosCSV(File fichero) throws FileNotFoundException, IOException, CsvException, ParseException {
    String str;
    String cursoAcademido = "", estadoMatricula = "";
    try (CSVReader reader = new CSVReader(new FileReader(fichero))) {
      List<String[]> l = reader.readAll();
      for(int i = 0; i < l.size(); i++) {
        if(i == 0) {
          str = Arrays.toString(l.get(i));
          String[] aux = str.split(";");
          cursoAcademido = aux[1];
        }
        if(i == 2) {
          str = Arrays.toString(l.get(i));
          String[] aux = str.split(";");
          estadoMatricula = aux[1];
        }
        if(i > 3) {
          str = Arrays.toString(l.get(i));
          String[] aux = str.split(";");
          
          Alumno al = new Alumno(aux[0].substring(1,aux[0].length()), aux[1] + " " + aux[2] + " " + aux[3], aux[6], aux[7], aux[8], aux[9]);
          al.setDireccion(aux[10]);
          al.setLocalidad(aux[11]);
          al.setProvincia(aux[12]);
          al.setCodigoPostal(aux[13]);
          
          Expediente exp = new Expediente(Integer.valueOf(aux[4]), true, Double.parseDouble(aux[17]));   
          exp.setCreditosSuperados(Integer.valueOf(aux[18]));
          exp.setCreditosFB(Integer.valueOf(aux[19]));
          exp.setCreditosOB(Integer.valueOf(aux[20]));
          exp.setCreditosOP(Integer.valueOf(aux[21]));
          exp.setCreditosCF(Integer.valueOf(aux[22]));
          exp.setCreditosPE(Integer.valueOf(aux[23]));
          exp.setCreditosTF(Integer.valueOf(aux[24].substring(0,aux[24].length()-1)));
          exp.setAlumno(al);          
          
          Matricula m = new Matricula();
          m.setCursoAcademico(cursoAcademido);
          m.setEstado(estadoMatricula);
          m.setNumArchivo(Integer.valueOf(aux[5]));
          m.setTurnoPreferente(aux[15]);
          String fM = aux[14];
          Date fechaMatricula = new SimpleDateFormat("dd/MM/yyyy").parse(fM);
          m.setFechaMatricula(fechaMatricula);
          m.setNuevoIngreso(false);
          m.setListadoAsignaturas(aux[16]);
          m.setExpediente(exp);
          
          em.persist(al);
          em.persist(m);
          em.persist(exp);
        }
        
      }
    }
    
  }
  
  @Override
  public void importarAlumnosExcel(File fichero) {
    try {
      FileInputStream file = new FileInputStream(fichero);
      XSSFWorkbook workbook = new XSSFWorkbook(file);
      XSSFSheet sheet = workbook.getSheetAt(0);
      
      Iterator<Row> rowIterator = sheet.iterator();
      while (rowIterator.hasNext()) {
          Alumno al = new Alumno();
          Expediente exp = new Expediente();
          Matricula m = new Matricula();
          Row row = rowIterator.next();
          Iterator<Cell> cellIterator = row.cellIterator();
          Cell cell = cellIterator.next();
          m.setCursoAcademico(cellIterator.next().toString());
          cell = cellIterator.next();
          cell = cellIterator.next();
          cell = cellIterator.next();
          m.setEstado(cellIterator.next().toString());
          row = rowIterator.next();
          al.setDni(cellIterator.next().toString());
          String nombre = cellIterator.next().toString();
          String apellido1 = cellIterator.next().toString();
          String apellido2 = cellIterator.next().toString();
          String nombreCompleto = nombre + " " + apellido1 + " " + apellido2;
          al.setNombre(nombreCompleto);
          exp.setNumExpediente(Integer.valueOf(cellIterator.next().toString()));
          m.setNumArchivo(Integer.valueOf(cellIterator.next().toString()));
          al.setEmailInstitucional(cellIterator.next().toString());
          al.setEmailPersonal(cellIterator.next().toString());
          al.setTelefono(cellIterator.next().toString());
          al.setMovil(cellIterator.next().toString());
          al.setDireccion(cellIterator.next().toString());
          al.setLocalidad(cellIterator.next().toString());
          al.setProvincia(cellIterator.next().toString());
          al.setCodigoPostal(cellIterator.next().toString());
          String fM = cellIterator.next().toString();
          Date fechaMatricula = new SimpleDateFormat("dd/MM/yyyy").parse(fM);
          m.setFechaMatricula(fechaMatricula);
          m.setTurnoPreferente(cellIterator.next().toString());
          m.setListadoAsignaturas(cellIterator.next().toString());
          exp.setCreditosSuperados(Integer.valueOf(cellIterator.next().toString()));
          exp.setCreditosFB(Integer.valueOf(cellIterator.next().toString()));
          exp.setCreditosOB(Integer.valueOf(cellIterator.next().toString()));
          exp.setCreditosOP(Integer.valueOf(cellIterator.next().toString()));
          exp.setCreditosCF(Integer.valueOf(cellIterator.next().toString()));
          exp.setCreditosPE(Integer.valueOf(cellIterator.next().toString()));
          exp.setCreditosTF(Integer.valueOf(cellIterator.next().toString()));
          
          em.persist(al);
          em.persist(m);
          em.persist(exp);
         
      }
      file.close(); workbook.close();
    } catch (Exception e) { e.printStackTrace(); }    
  }
  
  public void importarTitulacionCSV(File fichero) throws IOException, CsvException {
    try (CSVReader reader = new CSVReader(new FileReader(fichero))) {
      List<String[]> l = reader.readAll();
      for(int i = 0; i < l.size(); i++) {
        Titulacion t = new Titulacion();
        String str = Arrays.toString(l.get(i));
        String[] aux = str.split(";");
        t.setCodigo(aux[0]);
        t.setNombre(aux[1]);
        t.setCodigo(aux[2]);          
        
        em.persist(t);
      }
    }
  }
  
  public void importarTitulacionExcel(File fichero) {
    try {
      FileInputStream file = new FileInputStream(fichero);
      XSSFWorkbook workbook = new XSSFWorkbook(file);
      XSSFSheet sheet = workbook.getSheetAt(0);
      
      Iterator<Row> rowIterator = sheet.iterator();
      while (rowIterator.hasNext()) {
        Titulacion t = new Titulacion();
        Row row = rowIterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();
        t.setCodigo(cellIterator.next().toString());
        t.setNombre(cellIterator.next().toString());
        t.setCodigo(cellIterator.next().toString());  
        
        em.persist(t);
      }
      file.close(); workbook.close();
    } catch (Exception e) { e.printStackTrace(); }    
  }
  
  
  public void importarAsignaturanExcel(File fichero) {
    try {
      FileInputStream file = new FileInputStream(fichero);
      XSSFWorkbook workbook = new XSSFWorkbook(file);
      for(int i = 0; i < workbook.getNumberOfSheets(); i++) {
        if(i == 0) {
          XSSFSheet sheet = workbook.getSheetAt(i);
          Iterator<Row> rowIterator = sheet.iterator();
          while (rowIterator.hasNext()) {
            Optativa opt = new Optativa();
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            asig.setOfertada(Boolean.parseBoolean(cellIterator.next().toString()));
            asig.setCodigo(cellIterator.next().toString());
            asig.setReferencia(cellIterator.next().toString());
            asig.setNombre(cellIterator.next().toString());
            asig.setCurso(Integer.valueOf(cellIterator.next().toString()));
            asig.setCreditos(Integer.valueOf(cellIterator.next().toString()));
            asig.setCuatrimestre(cellIterator.next().toString());
            asig.setIdiomas(cellIterator.next().toString());
           
            em.persist(asig);
          }
        }
        if(i == 1) {
          XSSFSheet sheet = workbook.getSheetAt(i);
          Iterator<Row> rowIterator = sheet.iterator();
          while (rowIterator.hasNext()) {
            Asignatura asig = new Asignatura();
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            asig.setOfertada(Boolean.parseBoolean(cellIterator.next().toString()));
            asig.setCodigo(cellIterator.next().toString());
            asig.setReferencia(cellIterator.next().toString());
            asig.setNombre(cellIterator.next().toString());
            asig.setCurso(Integer.valueOf(cellIterator.next().toString()));
            asig.setCreditos(Integer.valueOf(cellIterator.next().toString()));
            asig.setCuatrimestre(cellIterator.next().toString());
            asig.setIdiomas(cellIterator.next().toString());
           
            em.persist(asig);
          }
        }
        if(i > 1) {
          XSSFSheet sheet = workbook.getSheetAt(i);
          Iterator<Row> rowIterator = sheet.iterator();
          while (rowIterator.hasNext()) {
            Asignatura asig = new Asignatura();
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            asig.setOfertada(Boolean.parseBoolean(cellIterator.next().toString()));
            asig.setCodigo(cellIterator.next().toString());
            asig.setReferencia(cellIterator.next().toString());
            asig.setNombre(cellIterator.next().toString());
            asig.setCurso(Integer.valueOf(cellIterator.next().toString()));
            asig.setCreditos(Integer.valueOf(cellIterator.next().toString()));
            asig.setCuatrimestre(cellIterator.next().toString());
            asig.setIdiomas(cellIterator.next().toString());
           
            em.persist(asig);
          }
        }
      }
      file.close(); workbook.close();
    } catch (Exception e) { e.printStackTrace(); }    
  }

  
}
