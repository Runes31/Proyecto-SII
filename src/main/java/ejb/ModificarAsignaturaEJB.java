package ejb;

import java.util.Iterator;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.Asignatura;
import domain.AsignaturasMatricula;
import domain.GruposPorAsignatura;
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
	  
	  for (Iterator<GruposPorAsignatura> iterator = asig.getGruposPorAsignatura().iterator(); iterator.hasNext();) {
		GruposPorAsignatura ga = iterator.next();
		em.remove(ga);
	}
	  
	  for (Iterator<AsignaturasMatricula> iterator = asig.getAsignaturasMatricula().iterator(); iterator.hasNext();) {
		AsignaturasMatricula am = iterator.next();
		em.remove(am);
	}
	  
	  em.remove(asignatura);
	  
	  
	 
  }


  
  
}
