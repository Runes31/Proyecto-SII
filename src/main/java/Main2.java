import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import domain.Alumno;
import domain.Expediente;
import domain.Matricula;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Main2 {

    public static void main(String[] args) throws IOException, CsvException, ParseException {
      String str;
      String cursoAcademido = "", estadoMatricula = "";
      try (CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Efrain\\Desktop\\BD\\Libro1.csv"))) {
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
          if(i == 3) {}
          if(i > 3) {
            str = Arrays.toString(l.get(i));
            String[] aux = str.split(";");
            Alumno al = new Alumno(aux[0].substring(1,aux[0].length()), aux[1] + " " + aux[2] + " " + aux[3], aux[6], aux[7], aux[8], aux[9]);
            System.out.println(al.toString());
            
            Expediente ex = new Expediente(Integer.valueOf(aux[4]), true, Double.parseDouble(aux[18]));   
            ex.setCreditosSuperados(Integer.valueOf(aux[19]));
            ex.setCreditosFB(Integer.valueOf(aux[20]));
            ex.setCreditosOB(Integer.valueOf(aux[21]));
            ex.setCreditosOP(Integer.valueOf(aux[22]));
            ex.setCreditosCF(Integer.valueOf(aux[23]));
            ex.setCreditosPE(Integer.valueOf(aux[24]));
            ex.setCreditosTF(Integer.valueOf(aux[25]));
            System.out.println(ex.toString());
            
            Matricula m = new Matricula();
            m.setCursoAcademico(cursoAcademido);
            m.setEstado(estadoMatricula);
            m.setNumArchivo(Integer.valueOf(aux[5]));
            m.setTurnoPreferente(aux[15]);
            String fM = aux[14];
            Date fechaMatricula = new SimpleDateFormat("dd/MM/yyyy").parse(fM);
            m.setFechaMatricula(fechaMatricula);
            m.setNuevoIngreso(true);
            m.setListadoAsignaturas(aux[16]);
            System.out.println(m.toString());
            
          }
          
        }
      }
          
   }
}