package ejb;

import static org.junit.Assert.*;

import domain.Alumno;
import es.uma.informatica.sii.anotaciones.Requisitos;
import exceptions.AlumnoNoEncontradoException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PruebaModificarAlumnoEJB {

  GestionAlumno ga;

  @Before
  public void setUp() throws Exception {
    ga = (GestionAlumno) SuiteTest.ctx.lookup("java:global/classes/ModificarAlumnoEJB");
  }

  @Test
  @Requisitos({"005"})
  public void findAlumno() {
    try {
      assertNotNull(ga.findAlumno(1));
    } catch (AlumnoNoEncontradoException e) {
      fail();
      e.printStackTrace();
    }
  }

  @Test
  @Requisitos({"005"})
  public void findAlumnoNoExiste() {
      assertThrows(AlumnoNoEncontradoException.class, () -> ga.findAlumno(-1));
  }

  @Test
  @Requisitos({"005"})
  public void getAllAlumnos() {
    List<Alumno> alumnos = ga.getAllAlumnos();
    assertNotEquals(alumnos, null);
    assertTrue(alumnos.size() > 1);
    alumnos.forEach(Assert::assertNotNull);
  }
}