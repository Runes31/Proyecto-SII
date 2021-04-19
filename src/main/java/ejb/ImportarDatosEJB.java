package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ImportarDatosEJB implements ImportarDatos{

  @PersistenceContext(name="Proyecto-SII")
  private EntityManager em;
  
  @Override
  public void importarAlumnos() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void importarPreferencias() {
    // TODO Auto-generated method stub
    
  }

}
