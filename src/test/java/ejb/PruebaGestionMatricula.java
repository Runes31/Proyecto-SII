package ejb;

import es.uma.informatica.sii.anotaciones.Requisitos;
import exceptions.ExpedienteNoEcontradoException;
import exceptions.MatriculaNoEncontradaException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import domain.Expediente;
import domain.Matricula;

public class PruebaGestionMatricula {
  
  private GestionMatricula gestionMatricula;
  
  @Before
  public void setUp() throws Exception  {
    gestionMatricula = (GestionMatricula) SuiteTest.ctx.lookup("java:global/classes/GestionMatriculaEJB");
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
    //assertThrows(GrupoNoEncontradoException.class, () -> gestionMatricula.generarAsignaciones());
  }
  
  
}