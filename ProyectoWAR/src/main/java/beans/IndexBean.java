package beans;

import domain.Alumno;
import domain.AsignaturasMatricula;
import domain.Expediente;
import domain.Matricula;
import ejb.GestionAlumno;
import ejb.GestionExpediente;
import ejb.GestionMatricula;
import exceptions.AlumnoNoEncontradoException;
import exceptions.ExpedienteNoEncontradoException;
import exceptions.MatriculaNoEncontradaException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
@Named
public class IndexBean implements Serializable {
  private static final Logger LOG = Logger.getLogger(IndexBean.class.getCanonicalName());
  private String name;
  private String titulacion;
  private String curso;
  private String grupo;
  private Boolean nuevo = true;
  private Boolean matriculado = true;
  @Inject
  GestionMatricula gm;
  @Inject
  GestionExpediente ge;
  @Inject
  GestionAlumno ga;

  private Expediente selExp = new Expediente();
  private Alumno selAlumno = new Alumno();
  private Matricula selMatricula = new Matricula();

  public String asignar(){
    gm.generarAsignaciones();
    return "";
  }

  public List<Matricula> getMatriculas(){
    if(!matriculado && !nuevo) return new ArrayList<>();

    List<Matricula> list = gm.getAllMatriculas();
    Stream<Matricula> st = list.stream();
    if(name != null && !name.equals("")) st = st.filter(m -> m.getExpediente().getAlumno().getNombre().toLowerCase(
        Locale.ROOT).contains(name.toLowerCase(Locale.ROOT)));
    if(titulacion != null && !titulacion.equals("")) st = st.filter(m -> m.getExpediente().getTitulacion().getNombre().toLowerCase(
        Locale.ROOT).contains(titulacion.toLowerCase(Locale.ROOT)));
    if(curso != null && !curso.equals("")) st = st.filter(m -> m.getCursoAcademico().contains(curso));
    if(grupo != null && !grupo.equals("")) st = st.filter(m -> m.getAsignaturasMatriculas().stream().anyMatch(am -> am.getGrupo() != null && am.getGrupo().getLetra().toLowerCase(
        Locale.ROOT).equalsIgnoreCase(grupo.toLowerCase(Locale.ROOT))));
    if(matriculado && nuevo){
      // Se muestran todos
    } else if(matriculado) st = st.filter(m -> !m.isNuevoIngreso());
    else if(nuevo) st = st.filter(Matricula::isNuevoIngreso);
    return st.collect(Collectors.toList());
  }

  public String getGrupo() {
    return grupo;
  }

  public void setGrupo(String grupo) {
    this.grupo = grupo;
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
      sb.append(am.getAsignatura().getCodigo(), 0, 3);
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
    LOG.log(Level.INFO, "Guardando expediente con número: " + selExp.getNumExpediente());
    ge.actualizarExpediente(selExp);
  }

  public Alumno getSelAlumno() {
    return selAlumno;
  }

  public void setSelAlumno(Alumno selAlumno) {
    this.selAlumno = selAlumno;
  }

  public void selectAlumno(Alumno a){
    setSelAlumno(a);
  }
  public void saveAlu() throws AlumnoNoEncontradoException {
    LOG.log(Level.INFO, "Guardando alumno: " + selAlumno);
    ga.actualizarAlumno(selAlumno);
  }

  public Matricula getSelMatricula() {
    return selMatricula;
  }

  public void setSelMatricula(Matricula selMatricula) {
    this.selMatricula = selMatricula;
  }

  public void selectMatricula(Matricula m){
    setSelMatricula(m);
  }

  public void saveMatricula() throws MatriculaNoEncontradaException {
    LOG.log(Level.INFO, "Actualizando matricula: " + selMatricula);
    gm.actualizarMatricula(selMatricula);
  }

  public Boolean getMatriculado() {
    return matriculado;
  }

  public void setMatriculado(Boolean matriculado) {
    this.matriculado = matriculado;
  }
}
