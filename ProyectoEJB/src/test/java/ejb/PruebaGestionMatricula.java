package ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import domain.AsignaturasMatricula;
import domain.Encuesta;
import domain.Expediente;
import domain.Grupo;
import domain.Matricula;
import es.uma.informatica.sii.anotaciones.Requisitos;
import exceptions.AsignaturaNoEncontradaException;
import exceptions.GrupoNoEncontradoException;
import exceptions.MatriculaNoEncontradaException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PruebaGestionMatricula {
  
  private GestionMatricula gestionMatricula;
  private GestionExpediente ge;
  
  @Before
  public void setUp() throws Exception  {
    gestionMatricula = (GestionMatricula) SuiteTest.ctx.lookup("java:global/classes/GestionMatriculaEJB");
    ge = (GestionExpediente) SuiteTest.ctx.lookup("java:global/classes/ModificarExpedienteEJB");
  }

  @Test
  @Requisitos({"001"})
  public void generarAsignaciones() {
    gestionMatricula.generarAsignaciones();
    List<Matricula> matriculas = gestionMatricula.getAllMatriculas();
    matriculas.forEach(m -> {
      m.getAsignaturasMatriculas().forEach(am -> {
        if(am.getGrupo() == null && !am.getAsignatura().getGruposPorAsignatura().isEmpty()) fail("Hay un alumno no asignado");
      });
    });
  }

  @Test
  @Requisitos({"009"})
  public void getAllMatriculas(){
    List<Matricula> matriculas = gestionMatricula.getAllMatriculas();
    assertNotEquals(matriculas, null);
    assertTrue(matriculas.size() > 1);
    matriculas.forEach(Assert::assertNotNull);
  }

  @Test
  @Requisitos({"011"})
  public void listarAsignaciones(){
    List<AsignaturasMatricula> ams = gestionMatricula.listarAsignaciones();
    assertNotEquals(ams, null);
    for(AsignaturasMatricula am: ams){
      assertNotNull(am);
      assertNotNull(am.getGrupo());
    }
  }

  @Test
  @Requisitos({"011"})
  public void listarAsignacionesNuevoIngreso(){
    List<AsignaturasMatricula> ams = gestionMatricula.listarAsignaciones(true);
    assertNotEquals(ams, null);
    for(AsignaturasMatricula am: ams){
      assertNotNull(am);
      assertNotNull(am.getGrupo());
      assertTrue(am.getMatricula().isNuevoIngreso());
    }
  }

  @Test
  @Requisitos({"013"})
  public void obtenerPreferencias(){
    try {
      Matricula m = gestionMatricula.findMatricula("2020/2021", 104200001);

      Encuesta enc = gestionMatricula.obtenerPreferencias(m);
      assertNotNull(enc);

      Encuesta ultima = m.getExpediente().getEncuestas().stream().max(Comparator.comparing(Encuesta::getFechaEnvio)).orElse(null);
      assertNotNull(ultima);

      assertEquals(enc, ultima);
    } catch (MatriculaNoEncontradaException matriculaNoEncontradaException) {
      fail("Matricula no encontrada");
      matriculaNoEncontradaException.printStackTrace();
    }
  }

  @Test
  @Requisitos({"013"})
  public void guardarPreferencias(){
    Encuesta encuesta = new Encuesta();
    encuesta.setFechaEnvio(new Date());
    Expediente e = ge.getAllExpediente().stream()
        .filter(exp -> !exp.getMatriculas().isEmpty() && exp.getEncuestas() == null || exp.getEncuestas().isEmpty()).findFirst().orElse(null);
    encuesta.setExpediente(e);

    encuesta.setGruposPorAsignaturas(null);
    gestionMatricula.guardarPreferencias(encuesta);

    try {
      Encuesta modified = gestionMatricula.obtenerPreferencias(e.getMatriculas().get(0));
      assertEquals(modified, encuesta);
    } catch (MatriculaNoEncontradaException matriculaNoEncontradaException) {
      fail("Matricula no encontrada");
      matriculaNoEncontradaException.printStackTrace();
    }

  }

  @Test
  @Requisitos({"009"})
  public void findMatricula(){
    Matricula m = gestionMatricula.getAllMatriculas().get(0);
    try {
      assertEquals(m, gestionMatricula.findMatricula(m.getCursoAcademico(), m.getExpediente().getNumExpediente()));
    } catch (MatriculaNoEncontradaException e) {
      fail();
      e.printStackTrace();
    }
  }

  @Test
  @Requisitos({"009"})
  public void findMatriculaNoExist(){
    assertThrows(MatriculaNoEncontradaException.class, () -> gestionMatricula.findMatricula("...", 1));
  }

  @Test
  @Requisitos({"002"})
  public void asignarGrupo(){
    AsignaturasMatricula am = gestionMatricula.getAllMatriculas().stream()
        .filter(m -> m.getAsignaturasMatriculas() != null && !m.getAsignaturasMatriculas().isEmpty())
        .map(Matricula::getAsignaturasMatriculas).flatMap(List::stream)
        .filter(ams -> ams.getAsignatura().getTitulacion().getGrupos().size() > 1).findFirst().orElse(null);
    Grupo g = am.getAsignatura().getTitulacion().getGrupos().stream().filter(gr -> !gr.equals(am.getGrupo())).findFirst().orElse(null);
    try {
      gestionMatricula.asignarGrupo(am.getMatricula(), am.getAsignatura(), g);
    } catch (MatriculaNoEncontradaException | AsignaturaNoEncontradaException | GrupoNoEncontradoException e) {
      fail();
      e.printStackTrace();
    }
    Matricula mat = gestionMatricula.getAllMatriculas().stream().filter(m -> m.getAsignaturasMatriculas()
        .stream().anyMatch(x -> x.getMatricula().equals(am.getMatricula()) && x.getAsignatura().equals(am.getAsignatura())))
        .findFirst().orElse(null);
    assertNotNull(mat);
    AsignaturasMatricula modified = mat.getAsignaturasMatriculas().stream()
        .filter(x -> x.getMatricula().equals(am.getMatricula()) && x.getAsignatura().equals(am.getAsignatura()))
        .findFirst().orElse(null);
    assertNotNull(modified);
    assertNotEquals(modified, am);
    assertEquals(modified.getGrupo(), g);
  }
}