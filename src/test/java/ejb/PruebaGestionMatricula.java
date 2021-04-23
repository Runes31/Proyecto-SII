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
  
  @Test
  @Requisitos({"008"})
  public void testModificarMatricula() {
    Matricula m = null;
    m = gestionMatricula.getAllMatriculas().stream().findFirst().orElse(null);
    if(m == null) fail ("No hay matriculas");
    String cursoAcademico = "20/21";
    
    m.setCursoAcademico(cursoAcademico);
    
    try {
    	gestionMatricula.actualizarMatricula(m);
    }catch(MatriculaNoEncontradaException ex) {
    	fail("Error al actualizar el Matricula");
    }
    
    Matricula modificada = null;
    try {
    	modificada = gestionMatricula.findMatricula(m.getCursoAcademico());
    }catch(MatriculaNoEncontradaException ex) {
    	fail("Matricula no encontrada");
    }
    
    assertEquals(cursoAcademico, modificada.getCursoAcademico());
    
  }
}