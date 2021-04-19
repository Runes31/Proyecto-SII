import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import domain.Alumno;
import domain.Expediente;
import domain.Matricula;

public class Main2 {

  public static void main(String[] args) {
    String cursoAcademido = "", estadoMatricula = "";
    Alumno al = null;
    Expediente exp = null;
    Matricula m = null;
    try {
      FileInputStream file = new FileInputStream(new File("C:\\Users\\Efrain\\Desktop\\BD\\Libro1.excell"));
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
