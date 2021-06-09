package beans;

import domain.Asignatura;
import ejb.GestionAsignatura;
import exceptions.AsignaturaNoEncontradaException;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
@Named
public class AsignaturasBean implements Serializable {
  @Inject
  GestionAsignatura ga;

  private Asignatura selAsignatura = new Asignatura();

  public List<Asignatura> getAsignaturas(){
    return ga.getAllAsignatura();
  }

  public void save() throws AsignaturaNoEncontradaException {
    ga.actualizarAsignatura(selAsignatura);
  }

  public void borrarAsignatura(Asignatura a) throws AsignaturaNoEncontradaException {
    ga.borrarAsignatura(a);
  }

  public void selectAsignatura(Asignatura a){
    setSelAsignatura(a);
  }

  public Asignatura getSelAsignatura() {
    return selAsignatura;
  }

  public void setSelAsignatura(Asignatura selAsignatura) {
    this.selAsignatura = selAsignatura;
  }
}
