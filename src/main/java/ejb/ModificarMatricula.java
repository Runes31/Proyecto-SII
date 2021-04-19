package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import domain.Matricula;
import exceptions.MatriculaNoEncontradaException;

@Stateless
public class ModificarMatricula implements GestionMatricula {
  
  @PersistenceContext(name="Proyecto-SII")
  private EntityManager em;
  @Override
  public void actualizarMatricula(Matricula matricula) throws MatriculaNoEncontradaException {
  //busca el matricula si no esta lanaza una exception y si esta se modifica con el .merge
    Matricula p=em.find(Matricula.class, matricula);
    if(p==null) throw new MatriculaNoEncontradaException();
    em.merge(matricula);
  }

}
