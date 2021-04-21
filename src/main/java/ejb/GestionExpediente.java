package ejb;

import javax.ejb.Local;
import domain.Expediente;
import exceptions.ExpedienteNoEcontradoException;

@Local
public interface GestionExpediente {
  /**
   * Requisito 007
   * Actualizar un expediente a partir de los datos de entrada
   * @param expediente
   * @throws ExpedienteNoEcontradoException
   */
  public void actualizarExpediente(Expediente expediente) throws ExpedienteNoEcontradoException;

  }

