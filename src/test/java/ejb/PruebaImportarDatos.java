package ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.opencsv.exceptions.CsvException;

import domain.Alumno;
import domain.Asignatura;
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
  }
  
  /*@Test
  @Requisitos({"006"})
  public void testImportarAlumnos() {
	  Alumno a = null;
	  a = ga.getAllAlumnos().stream().findFirst().orElse(null);
	  if(a == null) fail("No hay alumnos");
	  String dni = "95115697E";
	  a.setDni(dni);
	  String nombre = "Carmelita Enriquez Navarro";
	  a.setNombre(nombre);
	  Alumno a2 = null;
	  try {
		a2 = ga.findAlumno(a.getId());
	  } catch (AlumnoNoEncontradoException e) {
	  		fail("Alumno no encontrado");
	  }
	  assertEquals(dni, a2.getDni());
	  assertEquals(nombre, a2.getNombre());
  }*/
  
  @Test
  @Requisitos({"012"})
  public void testImportarTitulaciones() {
    //Comprobar que las importaciones son correctas    
  }
  
  @Test
  @Requisitos({"015"})
  public void testImportarAsignaturas() throws AsignaturaNoEncontradaException, FileNotFoundException, IOException, CsvException, ParseException {
	  Asignatura asig = null;
	  asig = gasi.getAllAsignatura().stream().findFirst().orElse(null);
	  if(asig == null) fail("No hay asignaturas");
	  String codigo = "106";
	  asig.setCodigo(codigo);
	  String referencia = "50659";
	  asig.setReferencia(referencia);
	  Asignatura asig2 = null;
	  try {
		asig = gasi.findAsignatura(asig.getReferencia());
	  } catch (AsignaturaNoEncontradaException e) {
	  		fail("Asignatura no encontrado");
	  }
	  assertEquals(codigo, asig2.getCodigo());
	  assertEquals(referencia, asig2.getReferencia());
  }
}
