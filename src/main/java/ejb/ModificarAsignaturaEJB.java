package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.Asignatura;
import exceptions.AsignaturaNoEncontradaException;

@Stateless
public class ModificarAsignaturaEJB implements GestionAsignatura {

  @PersistenceContext(name="Proyecto-SII")
  private EntityManager em;
  
  @Override
  public void actualizarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException {
    Asignatura p = em.find(Asignatura.class, asignatura);
    if (p == null) throw new AsignaturaNoEncontradaException();
    em.merge(asignatura);
  }

  @Override
  public void borrarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException {
	  Asignatura asig = em.find(Asignatura.class, asignatura);
	  if (asig == null) throw new AsignaturaNoEncontradaException();
	  em.remove(asignatura);
  }
  
  
}
