package ejb;

import domain.Matricula;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.Alumno;
import exceptions.AlumnoNoEncontradoException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class ModificarAlumnoEJB implements GestionAlumno {
  
  private static final Logger LOG = Logger.getLogger(ModificarAlumnoEJB.class.getCanonicalName());
  
  @PersistenceContext(name="Proyecto-SII")
  private EntityManager em;
  
  @Override
  public void actualizarAlumno(Alumno alumno) throws AlumnoNoEncontradoException {
    Alumno p=em.find(Alumno.class, alumno.getId());
    if(p==null) throw new AlumnoNoEncontradoException();
    em.merge(alumno);
  }

  @Override
  public Alumno findAlumno(int id) throws AlumnoNoEncontradoException {
    Alumno a = em.find(Alumno.class, id);
    if(a == null) throw new AlumnoNoEncontradoException();
    return a;
  }

  @Override
  public List<Alumno> getAllAlumnos(){
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Alumno> cq = cb.createQuery(Alumno.class);
    Root<Alumno> rootEntry = cq.from(Alumno.class);
    CriteriaQuery<Alumno> all = cq.select(rootEntry);
    TypedQuery<Alumno> allQuery = em.createQuery(all);
    return allQuery.getResultList();
  }
}
