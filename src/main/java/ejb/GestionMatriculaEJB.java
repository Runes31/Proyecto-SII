package ejb;

import domain.Asignatura;
import domain.Asignatura.AsignaturaId;
import domain.AsignaturasMatricula;
import domain.Encuesta;
import domain.Expediente;
import domain.Grupo;
import domain.GruposPorAsignatura;
import domain.Matricula;
import domain.Matricula.MatriculaId;
import exceptions.AsignaturaNoEncontradaException;
import exceptions.GrupoNoEncontradoException;
import exceptions.MatriculaNoEncontradaException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class GestionMatriculaEJB implements GestionMatricula {
  
  private static final Logger LOG = Logger.getLogger(GestionMatriculaEJB.class.getCanonicalName());
  
  @PersistenceContext(name="Proyecto-SII")
  private EntityManager em;
  
  @Override
  public void actualizarMatricula(Matricula matricula) throws MatriculaNoEncontradaException {
    Matricula p=em.find(Matricula.class,
        new MatriculaId(matricula.getCursoAcademico(), matricula.getExpediente().getNumExpediente()));
    if(p==null) throw new MatriculaNoEncontradaException();
    em.merge(matricula);
  }

  @Override
  public void generarAsignaciones() {
    // Consideramos las matriculas activas solo
    List<Matricula> allMatriculas = getAllMatriculas().stream().filter(m -> m.getEstado().equalsIgnoreCase("activa")).collect(
        Collectors.toList());

    // Prioridad fecha de matriculacion para alumnos de nuevo ingreso
    List<Matricula> primerIngreso = allMatriculas.stream().filter(Matricula::isNuevoIngreso)
        .sorted(Comparator.comparing(Matricula::getFechaMatricula))
        .collect(Collectors.toList());
    generarAsignaciones(primerIngreso);

    // Prioridad nota media provisional para el resto
    List<Matricula> otros = allMatriculas.stream().filter(m -> !m.isNuevoIngreso())
        .sorted((m1, m2) -> Double.compare(m2.getExpediente().getNotaMediaProvisional(), m1.getExpediente().getNotaMediaProvisional()))
        .collect(Collectors.toList());
    generarAsignaciones(otros);

  }

  private void generarAsignaciones(List<Matricula> matriculas) {
    for(Matricula matricula: matriculas){
      Expediente exp = matricula.getExpediente();
      // Consideramos la última encuesta que rellenó solo
      Encuesta prefs = exp.getEncuestas().stream().max(
          Comparator.comparing(Encuesta::getFechaEnvio)).orElse(null);

      for(AsignaturasMatricula am: matricula.getAsignaturasMatriculas()){
        // Si ya tiene un grupo asignado para esa asignatura respetamos la asignacion
        if(am.getGrupo() != null) continue;

        Grupo grupo = null;
        // Tenemos en cuenta la preferencia
        if (prefs != null) {
          grupo = prefs.getGruposPorAsignaturas().stream().filter(ga -> ga.getAsignatura().equals(am.getAsignatura()))
              .map(GruposPorAsignatura::getGrupo).findFirst().orElse(null);
        }
        // Si no tiene preferencias le asignamos un grupo random
        if(grupo == null){
          List<GruposPorAsignatura> gpa = am.getAsignatura().getGruposPorAsignatura();
          if(gpa.isEmpty()) grupo = null;
          else grupo = gpa.get((int) (Math.random()*gpa.size())).getGrupo();
        }
        am.setGrupo(grupo);
        em.merge(am);
      }
    }
  }

  @Override
  public List<Matricula> getAllMatriculas(){
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Matricula> cq = cb.createQuery(Matricula.class);
    Root<Matricula> rootEntry = cq.from(Matricula.class);
    CriteriaQuery<Matricula> all = cq.select(rootEntry);
    TypedQuery<Matricula> allQuery = em.createQuery(all);
    return allQuery.getResultList();
  }

  @Override
  public List<AsignaturasMatricula> listarAsignaciones() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<AsignaturasMatricula> cq = cb.createQuery(AsignaturasMatricula.class);
    Root<AsignaturasMatricula> rootEntry = cq.from(AsignaturasMatricula.class);
    CriteriaQuery<AsignaturasMatricula> all = cq.select(rootEntry);
    TypedQuery<AsignaturasMatricula> allQuery = em.createQuery(all);
    return allQuery.getResultList().stream().filter(am -> am.getGrupo() != null).collect(Collectors.toList());
  }

  @Override
  public List<AsignaturasMatricula> listarAsignaciones(boolean nuevoIngreso){
    List<AsignaturasMatricula> asignaciones = listarAsignaciones();
    return asignaciones.stream().filter(a -> a.getGrupo() != null && a.getMatricula().isNuevoIngreso() == nuevoIngreso).collect(
        Collectors.toList());
  }

  @Override
  public void asignarGrupo(Matricula m, Asignatura a, Grupo g)
      throws MatriculaNoEncontradaException, AsignaturaNoEncontradaException, GrupoNoEncontradoException {
    findMatricula(m.getCursoAcademico(), m.getExpediente().getNumExpediente());
    if(em.find(Asignatura.class, new AsignaturaId(a.getReferencia(), a.getTitulacion().getCodigo())) == null) throw new AsignaturaNoEncontradaException();
    if(em.find(Grupo.class, g.getId()) == null) throw new GrupoNoEncontradoException();

    AsignaturasMatricula am = new AsignaturasMatricula();
    am.setMatricula(m);
    am.setAsignatura(a);
    am.setGrupo(g);
    em.merge(am);
  }

  @Override
  public Encuesta obtenerPreferencias(Matricula matricula) throws MatriculaNoEncontradaException {
    Matricula m = em.find(Matricula.class,
        new MatriculaId(matricula.getCursoAcademico(), matricula.getExpediente().getNumExpediente()));
    if(m == null)
      throw new MatriculaNoEncontradaException();
    return m.getExpediente().getEncuestas().stream()
        .max(Comparator.comparing(Encuesta::getFechaEnvio)).orElse(null);
  }

  @Override
  public void guardarPreferencias(Encuesta encuesta) {
    /*
     Independientemente de si existe o no la guardamos/actualizamos
     Puede quitar preferencias por lo que nos da igual la diferencia que haya entre lo que tenemos
     en la base de datos y lo nuevo que nos envia
     */
    em.merge(encuesta);
  }
  
  @Override
  public Matricula findMatricula(String cursoAcademico, int expediente) throws MatriculaNoEncontradaException {
    MatriculaId pk = new MatriculaId(cursoAcademico, expediente);
    Matricula m = em.find(Matricula.class, pk);
    if(m == null) throw new MatriculaNoEncontradaException();
    return m;
  }

	

}
