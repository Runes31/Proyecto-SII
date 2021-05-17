package ejb;

import domain.Expediente;
import exceptions.ExpedienteNoEncontradoException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class ModificarExpedienteEJB implements GestionExpediente {
  
  private static final Logger LOG = Logger.getLogger(ModificarExpedienteEJB.class.getCanonicalName());
  
  @PersistenceContext(name="Proyecto-SII")
  private EntityManager em;

  @Override
  public void actualizarExpediente(Expediente expediente) throws ExpedienteNoEncontradoException {
    //busca el expediente si no esta lanaza una exception y si esta se modifica con el .merge
    Expediente p=em.find(Expediente.class, expediente.getNumExpediente());
    if(p==null) throw new ExpedienteNoEncontradoException();
    em.merge(expediente);
    
  }

	@Override
	public Expediente findExpediente(int n) throws ExpedienteNoEncontradoException {
		 Expediente e = em.find(Expediente.class, n);
		 if(e == null) throw new ExpedienteNoEncontradoException();
		    return e;
	}

	@Override
	public List<Expediente> getAllExpediente() {
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		 CriteriaQuery<Expediente> cq = cb.createQuery(Expediente.class);
		 Root<Expediente> rootEntry = cq.from(Expediente.class);
		 CriteriaQuery<Expediente> all = cq.select(rootEntry);
		 TypedQuery<Expediente> allQuery = em.createQuery(all);
		 return allQuery.getResultList();
	}
}