package ejb;

import domain.Grupo;
import exceptions.GrupoAsignaturaYaRelacionadoException;
import exceptions.GrupoNoEncontradoException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import domain.Alumno;
import domain.Asignatura;
import domain.AsignaturasMatricula;
import domain.GruposPorAsignatura;
import exceptions.AlumnoNoEncontradoException;
import exceptions.AsignaturaNoEncontradaException;


@Stateless
public class ModificarAsignaturaEJB implements GestionAsignatura {
  
  private static final Logger LOG = Logger.getLogger(ModificarAsignaturaEJB.class.getCanonicalName());

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

  @Override
  public void addGrupoAsignatura(Asignatura asignatura, Grupo grupo)
      throws GrupoAsignaturaYaRelacionadoException, AsignaturaNoEncontradaException, GrupoNoEncontradoException {
    if(em.find(Asignatura.class, asignatura) == null) throw new AsignaturaNoEncontradaException();
    if(em.find(Grupo.class, grupo) == null) throw new GrupoNoEncontradoException();

    if(asignatura.getGruposPorAsignatura().stream().anyMatch(gpa -> gpa.getGrupo().equals(grupo)))
      throw new GrupoAsignaturaYaRelacionadoException();

    GruposPorAsignatura gpa = new GruposPorAsignatura();
    gpa.setAsignatura(asignatura);
    gpa.setGrupo(grupo);

    em.persist(gpa);
  }
  
  @Override
  public Asignatura findAsignatura(String referencia) throws AsignaturaNoEncontradaException {
    Asignatura a = em.find(Asignatura.class, referencia);
    if(a == null) throw new AsignaturaNoEncontradaException();
    return a;
  }

	@Override
	public List<Asignatura> getAllAsignatura() {
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		 CriteriaQuery<Asignatura> cq = cb.createQuery(Asignatura.class);
		 Root<Asignatura> rootEntry = cq.from(Asignatura.class);
		 CriteriaQuery<Asignatura> all = cq.select(rootEntry);
		 TypedQuery<Asignatura> allQuery = em.createQuery(all);
		 return allQuery.getResultList();
	}
}