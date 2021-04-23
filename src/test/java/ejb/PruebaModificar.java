package ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.opencsv.exceptions.CsvException;
import domain.Alumno;
import domain.Asignatura;
import domain.Expediente;
import domain.Matricula;
import es.uma.informatica.sii.anotaciones.Requisitos;
import exceptions.AlumnoNoEncontradoException;
import exceptions.AsignaturaNoEncontradaException;
import exceptions.ExpedienteNoEcontradoException;
import exceptions.MatriculaNoEncontradaException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Before;
import org.junit.Test;


public class PruebaModificar {

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
  @Requisitos({"005"})
  public void testModificarAlumno() {
    Alumno a = null;
    a = ga.getAllAlumnos().stream().findFirst().orElse(null);
    if(a == null) fail("No hay alumnos");
    String dni = "1111111";
    String name = "test modificar";
    a.setDni(dni);
    a.setNombre(name);
    try {
      ga.actualizarAlumno(a);
    } catch (AlumnoNoEncontradoException e) {
      fail("Excepción en testModificarAlumno, no encuentra el alumno");
    }

    Alumno modified = null;
    try {
      modified = ga.findAlumno(a.getId());
    } catch (AlumnoNoEncontradoException e) {
      fail("Alumno no encontrado");
    }
    assertEquals(dni, modified.getDni());
    assertEquals(name, modified.getNombre());
  }
  
  @Test
  @Requisitos({"003"})
  public void testModificarAsignatura() {
    Asignatura asig = null;
    asig = gasi.getAllAsignatura().stream().findFirst().orElse(null);
    if(asig == null) fail ("No hay asignaturas");
    String codigo = "000";
    String nombre = "ABD";
    
    asig.setNombre(nombre);
    asig.setCodigo(codigo);
    
    try {
    	gasi.actualizarAsignatura(asig);
    }catch(AsignaturaNoEncontradaException e) {
    	fail("Error al actualizar la asignatura");
    }
    
    Asignatura modificada = null;
    try {
    	modificada = gasi.findAsignatura(asig.getReferencia());
    }catch(AsignaturaNoEncontradaException e) {
    	fail("Asignatura no encontrada");
    }
    
    assertEquals(codigo, modificada.getCodigo());
    assertEquals(nombre,modificada.getNombre());
    
    
  }
  
  @Test
  @Requisitos({"007"})
  public void testModificarExpediente() {
    Expediente e = null;
    e = ge.getAllExpediente().stream().findFirst().orElse(null);
    if(e == null) fail ("No hay expedientes");
    int creditosSup = 6;
    
    e.setCreditosSuperados(creditosSup);
    
    try {
    	ge.actualizarExpediente(e);
    }catch(ExpedienteNoEcontradoException ex) {
    	fail("Error al actualizar el expediente");
    }
    
    Expediente modificada = null;
    try {
    	modificada = ge.findExpediente(e.getNumExpediente());
    }catch(ExpedienteNoEcontradoException ex) {
    	fail("Expediente no encontrado");
    }
    
    assertEquals(creditosSup,modificada.getCreditosSuperados());
    
  }
  
  @Test
  @Requisitos({"008"})
  public void testModificarMatricula() {
    Matricula m = null;
    m = gestionMatricula.getAllMatriculas().stream().findFirst().orElse(null);
    if(m == null) fail ("No hay matriculas");
    int numArchivo = 10;
    
    m.setNumArchivo(numArchivo);
    
    try {
    	gestionMatricula.actualizarMatricula(m);
    }catch(MatriculaNoEncontradaException ex) {
    	fail("Error al actualizar la Matricula");
    }
    
    Matricula modificada = null;
    try {
    	modificada = gestionMatricula.findMatricula(m.getCursoAcademico());
    }catch(MatriculaNoEncontradaException ex) {
    	fail("Matricula no encontrada");
    }
    
    assertEquals(numArchivo,modificada.getNumArchivo());
    
  }

}
