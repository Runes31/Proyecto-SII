package ejb;

import java.util.List;

import javax.ejb.Local;


import domain.Expediente;
import exceptions.ExpedienteNoEcontradoException;

@Local
public interface GestionExpediente {
  /**
   * Actualizar un expediente a partir de los datos de entrada
   * @param expediente
   * @throws ExpedienteNoEcontradoException
   */
  public void actualizarExpediente(Expediente expediente) throws ExpedienteNoEcontradoException;
  
  /**
   * Encuentra un expediente por su pk
   * @param expedediente
   * @return Objeto tipo Expediente
   * @throws ExpedienteNoEncontradoException
   */
  public Expediente findExpediente(int n) throws ExpedienteNoEcontradoException;
  
  public List<Expediente> getAllExpediente();

  }

