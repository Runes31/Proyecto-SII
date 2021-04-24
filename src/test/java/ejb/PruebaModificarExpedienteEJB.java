package ejb;

import static org.junit.Assert.*;

import domain.Asignatura;
import domain.Expediente;
import es.uma.informatica.sii.anotaciones.Requisitos;
import exceptions.ExpedienteNoEncontradoException;
import exceptions.ExpedienteNoEncontradoException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PruebaModificarExpedienteEJB {

  GestionExpediente ge;

  @Before
  public void setUp() throws Exception {
    ge = (GestionExpediente) SuiteTest.ctx.lookup("java:global/classes/ModificarExpedienteEJB");
  }

  @Test
  @Requisitos({"007"})
  public void findExpediente() {
    Expediente e = ge.getAllExpediente().get(0);
    try {
      assertEquals(e, ge.findExpediente(e.getNumExpediente()));
    } catch (ExpedienteNoEncontradoException expedienteNoEncontradoException) {
      fail();
      expedienteNoEncontradoException.printStackTrace();
    }
  }

  @Test
  @Requisitos({"007"})
  public void findExpedienteNoExiste() {
    assertThrows(ExpedienteNoEncontradoException.class, () -> ge.findExpediente(-1));
  }

  @Test
  @Requisitos({"007"})
  public void getAllExpediente() {
    List<Expediente> expedientes = ge.getAllExpediente();
    assertNotEquals(expedientes, null);
    assertTrue(expedientes.size() > 1);
    expedientes.forEach(Assert::assertNotNull);
  }
}