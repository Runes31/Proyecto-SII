package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import domain.Asignatura;
import exceptions.AsignaturaNoEncontradaException;

@Stateless
public class ModificarAsignaturaEJB implements GestionAsignatura {

  private EntityManager em;
  
  @Override
  public void actualizarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException {
    Asignatura p = em.find(Asignatura.class, asignatura);
    if (p == null) throw new AsignaturaNoEncontradaException();
    em.merge(asignatura);
      
  }
}
