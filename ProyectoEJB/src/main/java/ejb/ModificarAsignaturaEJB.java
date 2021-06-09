package ejb;

import domain.Asignatura;
import domain.Asignatura.AsignaturaId;
import domain.Grupo;
import domain.GruposPorAsignatura;
import exceptions.AsignaturaNoEncontradaException;
import exceptions.GrupoAsignaturaYaRelacionadoException;
import exceptions.GrupoNoEncontradoException;
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
public class ModificarAsignaturaEJB implements GestionAsignatura {
  
  private static final Logger LOG = Logger.getLogger(ModificarAsignaturaEJB.class.getCanonicalName());

  @PersistenceContext(name="Proyecto-SII")
  private EntityManager em;
  
  @Override
  public void actualizarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException {
    Asignatura p = em.find(Asignatura.class, new AsignaturaId(asignatura.getReferencia(), asignatura.getTitulacion().getCodigo()));
    if (p == null) throw new AsignaturaNoEncontradaException();
    em.merge(asignatura);
  }

  @Override
  public void borrarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException {
	  Asignatura asig = em.find(Asignatura.class, new AsignaturaId(asignatura.getReferencia(), asignatura.getTitulacion().getCodigo()));
	  if (asig == null) throw new AsignaturaNoEncontradaException();
	  asignatura.setOfertada(false);
	  em.merge(asignatura);
  }

  @Override
  public void addGrupoAsignatura(Asignatura asignatura, Grupo grupo)
      throws GrupoAsignaturaYaRelacionadoException, AsignaturaNoEncontradaException, GrupoNoEncontradoException {
    Asignatura a = em.find(Asignatura.class, new AsignaturaId(asignatura.getReferencia(), asignatura.getTitulacion().getCodigo()));
    if(a == null)
      throw new AsignaturaNoEncontradaException();
    Grupo g = em.find(Grupo.class, grupo.getId());
    if(g == null) throw new GrupoNoEncontradoException();

    if(a.getGruposPorAsignatura().stream().anyMatch(gpa -> gpa.getGrupo().equals(g)))
      throw new GrupoAsignaturaYaRelacionadoException();

    GruposPorAsignatura gpa = new GruposPorAsignatura();
    gpa.setCursoAcademico("2020/2021");
    gpa.setAsignatura(a);
    gpa.setGrupo(g);

    em.persist(gpa);
  }
  
  @Override
  public Asignatura findAsignatura(String referencia, String codigo) throws AsignaturaNoEncontradaException {
    Asignatura a = em.find(Asignatura.class, new AsignaturaId(referencia, codigo));
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