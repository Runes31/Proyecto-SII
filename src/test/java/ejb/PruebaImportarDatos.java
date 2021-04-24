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
import domain.Titulacion;
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
  @Requisitos({"015","012"})
  public void testImportarAsignaturasTitulaciones() throws AsignaturaNoEncontradaException, FileNotFoundException, IOException, CsvException, ParseException {
	  Asignatura asig = null;
	  Titulacion t = new Titulacion();
	  t.setNombre("Grado en Ingeniería Informática");
	  t.setCodigo("1041");
	  t.setCreditos(240);
	  asig = gasi.getAllAsignatura().stream().findFirst().orElse(null);
	  if(asig == null) fail("No hay asignaturas");
	  String codigo = "101";
	  asig.setCodigo(codigo);
	  int creditos = 6;
	  asig.setCreditos(creditos);
	  String referencia = "50658";
	  asig.setReferencia(referencia);
	  asig.setOfertada(true);
	  String nombre = "Cálculo para la Computación";
	  asig.setNombre(nombre);
	  asig.setTitulacion(t);
	  
	  Asignatura asig2 = null;
	  try {
		asig2 = gasi.findAsignatura(asig.getReferencia());
	  } catch (AsignaturaNoEncontradaException e) {
	  		fail("Asignatura no encontrada");
	  }
	  assertEquals(codigo, asig2.getCodigo());
	  assertEquals(nombre, asig2.getNombre());	  
	  

  }
}
