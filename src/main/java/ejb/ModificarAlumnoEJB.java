package ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.Alumno;
import exceptions.AlumnoNoEncontradoException;

@Stateless
public class ModificarAlumnoEJB implements GestionAlumno {
  
  private static final Logger LOG = Logger.getLogger(ModificarAlumnoEJB.class.getCanonicalName());
  
  @PersistenceContext(name="Proyecto-SII")
  private EntityManager em;
  
  @Override
  public void actualizarAlumno(Alumno alumno) throws AlumnoNoEncontradoException {
    Alumno p=em.find(Alumno.class, alumno);
    if(p==null) throw new AlumnoNoEncontradoException();
    em.merge(alumno);
  }

}
