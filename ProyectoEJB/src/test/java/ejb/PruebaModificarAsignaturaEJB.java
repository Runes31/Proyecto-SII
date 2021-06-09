package ejb;

import static org.junit.Assert.*;

import domain.Asignatura;
import domain.Grupo;
import domain.Matricula;
import es.uma.informatica.sii.anotaciones.Requisitos;
import exceptions.AsignaturaNoEncontradaException;
import exceptions.GrupoAsignaturaYaRelacionadoException;
import exceptions.GrupoNoEncontradoException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PruebaModificarAsignaturaEJB {

  GestionAsignatura ga;

  @Before
  public void setUp() throws Exception {
    ga = (GestionAsignatura) SuiteTest.ctx.lookup("java:global/classes/ModificarAsignaturaEJB");
  }

  @Test
  @Requisitos({"004"})
  public void borrarAsignatura() {
    Asignatura a = ga.getAllAsignatura().stream().filter(Asignatura::isOfertada).findFirst().orElse(null);
    // TODO: Fix a is null
    try {
      ga.borrarAsignatura(a);
    } catch (AsignaturaNoEncontradaException e) {
      fail();
      e.printStackTrace();
    }
  }

  @Test
  @Requisitos({"010"})
  public void addGrupoAsignatura() {
    Asignatura a = ga.getAllAsignatura().stream().filter(as -> !as.getTitulacion().getGrupos().isEmpty() && as.getGruposPorAsignatura().isEmpty()).findFirst().orElse(null);
    assertNotNull(a);
    Grupo g = a.getTitulacion().getGrupos().get(0);
    try {
      ga.addGrupoAsignatura(a, g, "2020/2021");
    } catch (GrupoAsignaturaYaRelacionadoException | AsignaturaNoEncontradaException | GrupoNoEncontradoException e) {
      fail();
      e.printStackTrace();
    }
    try {
      Asignatura modified = ga.findAsignatura(a.getReferencia(), a.getTitulacion().getCodigo());
      assertTrue(modified.getGruposPorAsignatura().stream().anyMatch(gpa -> gpa.getGrupo().equals(g)));
    } catch (AsignaturaNoEncontradaException e) {
      fail();
      e.printStackTrace();
    }
  }

  @Test
  @Requisitos({"010"})
  public void addGrupoAsignaturaYaRelacionado() {
    Asignatura a = ga.getAllAsignatura().stream().filter(as -> !as.getTitulacion().getGrupos().isEmpty() && as.getGruposPorAsignatura().isEmpty()).findFirst().orElse(null);
    assertNotNull(a);
    Grupo g = a.getTitulacion().getGrupos().get(0);
    try {
      ga.addGrupoAsignatura(a, g, "2020/2021");
    } catch (GrupoAsignaturaYaRelacionadoException | AsignaturaNoEncontradaException | GrupoNoEncontradoException e) {
      fail();
      e.printStackTrace();
    }
    assertThrows(GrupoAsignaturaYaRelacionadoException.class, () -> ga.addGrupoAsignatura(a, g, "2020/2021"));
  }

  @Test
  @Requisitos({"003"})
  public void findAsignatura() {
    Asignatura a = ga.getAllAsignatura().get(0);
    try {
      assertEquals(a, ga.findAsignatura(a.getReferencia(), a.getTitulacion().getCodigo()));
    } catch (AsignaturaNoEncontradaException e) {
      fail();
      e.printStackTrace();
    }
  }

  @Test
  @Requisitos({"003"})
  public void getAllAsignatura() {
    List<Asignatura> asignaturas = ga.getAllAsignatura();
    assertNotEquals(asignaturas, null);
    assertTrue(asignaturas.size() > 1);
    asignaturas.forEach(Assert::assertNotNull);
  }
}