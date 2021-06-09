package beans;

import domain.Asignatura;
import domain.Grupo;
import ejb.GestionAsignatura;
import exceptions.AsignaturaNoEncontradaException;
import exceptions.GrupoAsignaturaYaRelacionadoException;
import exceptions.GrupoNoEncontradoException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
@Named
public class AsignaturasBean implements Serializable {
  private static final Logger LOG = Logger.getLogger(IndexBean.class.getCanonicalName());

  @Inject
  GestionAsignatura ga;

  private Asignatura selAsignatura = new Asignatura();

  public void addGrupo(Grupo g)
      throws AsignaturaNoEncontradaException, GrupoAsignaturaYaRelacionadoException, GrupoNoEncontradoException {
    LOG.log(Level.INFO, "Añadiendo el grupo " + g + " a la asignatura " + selAsignatura + " de la titulación " + selAsignatura.getTitulacion());
    ga.addGrupoAsignatura(selAsignatura, g);
  }

  public Boolean renderButton(Grupo g){
    return selAsignatura.getGruposPorAsignatura().stream().noneMatch(gpa -> gpa.getGrupo().equals(g));
  }

  public List<Asignatura> getAsignaturas(){
    return ga.getAllAsignatura();
  }

  public List<Grupo> getGrupos(){
    if(selAsignatura.getTitulacion() == null) return new ArrayList<>();
    return selAsignatura.getTitulacion().getGrupos();
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
