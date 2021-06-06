package beans;

import domain.AsignaturasMatricula;
import domain.Expediente;
import domain.Matricula;
import ejb.GestionExpediente;
import ejb.GestionMatricula;
import exceptions.ExpedienteNoEncontradoException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class IndexBean implements Serializable {
  private String name;
  private String titulacion;
  private String curso;
  private String grupo;
  private String selected;
  private Boolean nuevo;
  @Inject
  GestionMatricula gm;
  @Inject
  GestionExpediente ge;

  private Expediente selExp = new Expediente();

  // TODO: Fix el import para que se pueda asignar algun grupo pa poder probarlo lul
  public String asignar(){
    gm.generarAsignaciones();
    return "";
  }

  public List<Matricula> getMatriculas(){
    List<Matricula> list = gm.getAllMatriculas();
    Stream<Matricula> st = list.stream();
    if(name != null && !name.equals("")) st = st.filter(m -> m.getExpediente().getAlumno().getNombre().toLowerCase(
        Locale.ROOT).contains(name.toLowerCase(Locale.ROOT)));
    if(titulacion != null && !titulacion.equals("")) st = st.filter(m -> m.getExpediente().getTitulacion().getNombre().toLowerCase(
        Locale.ROOT).contains(titulacion.toLowerCase(Locale.ROOT)));
    if(curso != null && !curso.equals("")) st = st.filter(m -> m.getCursoAcademico().contains(curso));
    if(grupo != null && !grupo.equals("")) st = st.filter(m -> m.getAsignaturasMatriculas().stream().anyMatch(am -> am.getGrupo() != null && am.getGrupo().getLetra().toLowerCase(
        Locale.ROOT).equalsIgnoreCase(grupo.toLowerCase(Locale.ROOT))));
    if(nuevo != null && nuevo) st = st.filter(Matricula::isNuevoIngreso);
    return st.collect(Collectors.toList());
  }

  public String getGrupo() {
    return grupo;
  }

  public void setGrupo(String grupo) {
    this.grupo = grupo;
  }

  public String getSelected() {
    return selected;
  }

  public void setSelected(String selected) {
    this.selected = selected;
  }

  public void seleccionar(String s){
    selected = s;
  }

  public String getCurso() {
    return curso;
  }

  public void setCurso(String curso) {
    this.curso = curso;
  }

  public String getTitulacion() {
    return titulacion;
  }

  public void setTitulacion(String titulacion) {
    this.titulacion = titulacion;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String search(){
    return name + " --- " + titulacion;
  }

  public String printAsignaturas(List<AsignaturasMatricula> asignaturas){
    if(asignaturas.isEmpty()) return "";
    StringBuilder sb = new StringBuilder();
    for(AsignaturasMatricula am: asignaturas){
      sb.append(", ");
      sb.append(am.getAsignatura().getCodigo().substring(0, 3));
      sb.append("-");
      if(am.getGrupo() != null) sb.append(am.getGrupo().getLetra());
    }
    return sb.substring(2);
  }

  public void setNuevo(Boolean nuevo) {
    this.nuevo = nuevo;
  }

  public Boolean getNuevo() {
    return nuevo;
  }

  public Expediente getSelExp() {
    return selExp;
  }

  public void setSelExp(Expediente selExp) {
    this.selExp = selExp;
  }

  public void selectExp(Expediente e) {
    selExp = e;
  }

  public void saveExp() throws ExpedienteNoEncontradoException {
    // TODO: Fix lanza excepcion de que no lo encuentra, resto ok potencialmente
    ge.actualizarExpediente(selExp);
  }
}
