package ejb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.io.FileInputStream;  
import java.util.Iterator; 
import org.apache.poi.*;
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
import domain.Expediente;
import domain.Matricula;

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
          
          Expediente ex = new Expediente(Integer.valueOf(aux[4]), true, Double.parseDouble(aux[17]));   
          ex.setCreditosSuperados(Integer.valueOf(aux[18]));
          ex.setCreditosFB(Integer.valueOf(aux[19]));
          ex.setCreditosOB(Integer.valueOf(aux[20]));
          ex.setCreditosOP(Integer.valueOf(aux[21]));
          ex.setCreditosCF(Integer.valueOf(aux[22]));
          ex.setCreditosPE(Integer.valueOf(aux[23]));
          ex.setCreditosTF(Integer.valueOf(aux[24].substring(0,aux[24].length()-1)));
          ex.setAlumno(al);          
          
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
          
          List<Expediente> exp = new ArrayList<Expediente>();
          exp.add(ex);
          al.setExpedientes(exp);
          List<Matricula> mat = new ArrayList<Matricula>();
          mat.add(m);
          ex.setMatriculas(mat);
          
          em.persist(al);
          em.persist(m);
          em.persist(ex);
        }
        
      }
    }
    
  }

  @Override
  public void importarPreferenciasCSV() {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void importarAlumnosExcell(File fichero) throws IOException {
    int i = 0;
    String str;
    String cursoAcademido = "", estadoMatricula = "";
    Alumno al = null;
    Expediente exp = null;
    Matricula m = null;
    try {
      FileInputStream file = new FileInputStream(fichero);
      XSSFWorkbook workbook = new XSSFWorkbook(file);
      XSSFSheet sheet = workbook.getSheetAt(0);
      
      Iterator<Row> rowIterator = sheet.iterator();
      while (rowIterator.hasNext()) {
          Row row = rowIterator.next();
          Iterator<Cell> cellIterator = row.cellIterator();
          Cell cell = cellIterator.next();
          cell = cellIterator.next();
          cursoAcademido = cell.toString();
          cell = cellIterator.next();
          cell = cellIterator.next();
          cell = cellIterator.next();
          cell = cellIterator.next();
          estadoMatricula = cell.toString();
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
          String gruposAsig = cellIterator.next().toString();
          exp.setCreditosSuperados(Integer.valueOf(cellIterator.next().toString()));
          exp.setCreditosFB(Integer.valueOf(cellIterator.next().toString()));
          exp.setCreditosOB(Integer.valueOf(cellIterator.next().toString()));
          exp.setCreditosOP(Integer.valueOf(cellIterator.next().toString()));
          exp.setCreditosCF(Integer.valueOf(cellIterator.next().toString()));
          exp.setCreditosPE(Integer.valueOf(cellIterator.next().toString()));
          exp.setCreditosTF(Integer.valueOf(cellIterator.next().toString()));
         
          
          List<Expediente> ex = new ArrayList<Expediente>();
          ex.add(exp);
          al.setExpedientes(ex);
          List<Matricula> mat = new ArrayList<Matricula>();
          mat.add(m);
          exp.setMatriculas(mat);
      }
      file.close();
    } catch (Exception e) { e.printStackTrace(); }    
  }
}
