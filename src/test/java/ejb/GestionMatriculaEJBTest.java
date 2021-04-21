package ejb;

import static org.junit.Assert.*;

import domain.Asignatura;
import domain.AsignaturasMatricula;
import domain.Expediente;
import domain.Matricula;
import es.uma.informatica.sii.anotaciones.Requisitos;
import exceptions.GrupoNoEncontradoException;
import exceptions.MatriculaNoEncontradaException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.Before;
import org.junit.Test;

public class GestionMatriculaEJBTest {

  private GestionMatricula gestionMatricula;

  @Before
  public void setUp() throws Exception {
    gestionMatricula = new GestionMatriculaEJB();
  }

  @Test
  @Requisitos({"001"})
  /**
   * Explicacion
   */
  public void generarAsignaciones() {
  }

  @Test()
  @Requisitos({"001"})
  /**
   * Explicacion
   */
  public void generarAsignacionesNoGrupo(){
    assertThrows(GrupoNoEncontradoException.class, () -> gestionMatricula.generarAsignaciones());
  }
}