package ejb;


//import static org.junit.Assert.assertThrows;


import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
//import exceptions.GrupoNoEncontradoException;

public class PruebaGestionMatricula {
  
  //private GestionMatricula gestionMatricula;
  
  @Before
  public void setUp() throws Exception  {
    BaseDatos.inicializaBaseDatos("Proyecto-SII");
    //gestionMatricula = new GestionMatriculaEJB();
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