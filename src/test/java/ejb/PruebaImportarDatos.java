package ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import domain.Encuesta;
import domain.Expediente;
import exceptions.MatriculaNoEncontradaException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.opencsv.exceptions.CsvException;

import es.uma.informatica.sii.anotaciones.*;

import domain.Alumno;
import domain.Asignatura;
import domain.Titulacion;
import es.uma.informatica.sii.anotaciones.Requisitos;
import exceptions.AlumnoNoEncontradoException;
import exceptions.AsignaturaNoEncontradaException;

public class PruebaImportarDatos {
	
	GestionAlumno ga;
	GestionAsignatura gasi;
	GestionExpediente ge;
	GestionMatricula gestionMatricula;
  
  @Before
  public void setup() throws NamingException, FileNotFoundException, IOException, CsvException, ParseException  {
	  ga = (GestionAlumno) SuiteTest.ctx.lookup("java:global/classes/ModificarAlumnoEJB");
	  gasi = (GestionAsignatura) SuiteTest.ctx.lookup("java:global/classes/ModificarAsignaturaEJB");
	  gestionMatricula = (GestionMatricula) SuiteTest.ctx.lookup("java:global/classes/GestionMatriculaEJB");
    ge = (GestionExpediente) SuiteTest.ctx.lookup("java:global/classes/ModificarExpedienteEJB");
  }

  @Test
  @Requisitos({"006"})
  public void testImportarAlumnosExcel(){
    Alumno alumno = ga.getAllAlumnos().stream().filter(a -> a.getDni().equals("95115697E")).findFirst().orElse(null);
    assertNotNull(alumno);
    if(!alumno.getNombre().contains("Carmelita")) fail();
  }

  @Test
  @Requisitos({"015"})
  public void testImportarTitulacionExcel(){
    assertEquals(5, gasi.getAllAsignatura().stream().map(Asignatura::getTitulacion).distinct().count());
  }

  @Test
  @Requisitos({"014"})
  public void testImportarAsignaturasExcel(){
    try {
      Asignatura asignatura = gasi.findAsignatura("51046.0", "1041.0");
      assertEquals("413.0", asignatura.getCodigo());
    } catch (AsignaturaNoEncontradaException e) {
      fail();
      e.printStackTrace();
    }
  }

  @Test
  @Requisitos({"012"})
  public void testImportarEncuestaExcel() throws ParseException {
    Expediente e = ge.getAllExpediente().stream().filter(exp -> !exp.getEncuestas().isEmpty()).findFirst().orElse(null);
    assertNotNull(e);
    assertTrue(e.getEncuestas().get(0).getFechaEnvio().compareTo(new SimpleDateFormat("dd-MM-yyyy").parse("19-10-2020")) >= 0);
  }

  @Test
  @Requisitos({"15"})
  public void testImportarGruposExcel(){
    assertTrue(ge.getAllExpediente().stream().map(Expediente::getTitulacion).map(Titulacion::getGrupos).flatMap(
        List::stream).anyMatch(g -> g.getCurso().equals("1.0") && g.getLetra().equals("a")));
  }
}
