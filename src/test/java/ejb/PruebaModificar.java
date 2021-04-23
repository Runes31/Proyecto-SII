package ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.opencsv.exceptions.CsvException;
import domain.Alumno;
import es.uma.informatica.sii.anotaciones.Requisitos;
import exceptions.AlumnoNoEncontradoException;
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

  @Before
  public void setup() throws NamingException, FileNotFoundException, IOException, CsvException, ParseException  {
    ga = (GestionAlumno) SuiteTest.ctx.lookup("java:global/classes/ModificarAlumnoEJB");
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
  public void testModificarAsignatura() {
    //Comprobar que se puede modificar una asignatura
  }
  
  @Test
  public void testModificarExpediente() {
    //Comprobar que se puede modificar un expediente
  }

}
