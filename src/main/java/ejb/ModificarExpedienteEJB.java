package ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import domain.Expediente;
import exceptions.ExpedienteNoEcontradoException;

@Stateless
public class ModificarExpedienteEJB implements GestionExpediente {
  
  private static final Logger LOG = Logger.getLogger(ModificarExpedienteEJB.class.getCanonicalName());
  
  @PersistenceContext(name="Proyecto-SII")
  private EntityManager em;

  @Override
  public void actualizarExpediente(Expediente expediente) throws ExpedienteNoEcontradoException {
    //busca el expediente si no esta lanaza una exception y si esta se modifica con el .merge
    Expediente p=em.find(Expediente.class, expediente);
    if(p==null) throw new ExpedienteNoEcontradoException();
    em.merge(expediente);
    
  }
}