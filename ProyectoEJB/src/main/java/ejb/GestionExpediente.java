package ejb;

import domain.Expediente;
import exceptions.ExpedienteNoEncontradoException;
import java.util.List;
import javax.ejb.Local;

@Local
public interface GestionExpediente {
  /**
   * Actualizar un expediente a partir de los datos de entrada
   * @param expediente
   * @throws ExpedienteNoEncontradoException
   */
  void actualizarExpediente(Expediente expediente) throws ExpedienteNoEncontradoException;
  
  /**
   * Encuentra un expediente por su pk
   * @param n
   * @return Objeto tipo Expediente
   * @throws ExpedienteNoEncontradoException
   */
  Expediente findExpediente(int n) throws ExpedienteNoEncontradoException;

  /**
   * Genera una lista con todos los expedientes
   * @return
   */
  List<Expediente> getAllExpediente();

}