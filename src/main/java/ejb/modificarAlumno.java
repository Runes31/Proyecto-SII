package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.Alumno;
import domain.Expediente;
import exceptions.AlumnoNoEncontradoException;
import exceptions.ExpedienteNoEcontradoException;

@Stateless
public class modificarAlumno implements GestionAlumno {
  
  @PersistenceContext(name="Proyecto-SII")
  private EntityManager em;
  
  @Override
  public void actualizarAlumno(Alumno alumno) throws AlumnoNoEncontradoException {
  //busca el alumno si no esta lanaza una exception y si esta se modifica con el .merge
    Alumno p=em.find(Alumno.class, alumno);
    if(p==null) throw new AlumnoNoEncontradoException();
    em.merge(alumno);
  }

}