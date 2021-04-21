package ejb;

import domain.Alumno;
import domain.Asignatura;
import domain.AsignaturasMatricula;
import domain.Encuesta;
import domain.Expediente;
import domain.Grupo;
import domain.GruposPorAsignatura;
import exceptions.GrupoNoEncontradoException;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import domain.Matricula;
import exceptions.MatriculaNoEncontradaException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class GestionMatriculaEJB implements GestionMatricula {
  
  @PersistenceContext(name="Proyecto-SII")
  private EntityManager em;
  @Override
  public void actualizarMatricula(Matricula matricula) throws MatriculaNoEncontradaException {
  //busca el matricula si no esta lanaza una exception y si esta se modifica con el .merge
    Matricula p=em.find(Matricula.class, matricula);
    if(p==null) throw new MatriculaNoEncontradaException();
    em.merge(matricula);
  }

  @Override
  public void generarAsignaciones() throws GrupoNoEncontradoException {
    // Consideramos las matriculas activas solo
    List<Matricula> allMatriculas = getAllMatriculas().stream().filter(m -> m.getEstado().equalsIgnoreCase("activo")).collect(
        Collectors.toList());

    // Prioridad fecha de matriculacion para alumnos de nuevo ingreso
    List<Matricula> primerIngreso = allMatriculas.stream().filter(Matricula::isNuevoIngreso)
        .sorted((m1, m2) -> m1.getFechaMatricula().compareTo(m2.getFechaMatricula()))
        .collect(Collectors.toList());
    generarAsignaciones(primerIngreso);

    // Prioridad nota media provisional para el resto
    List<Matricula> otros = allMatriculas.stream().filter(m -> !m.isNuevoIngreso())
        .sorted((m1, m2) -> Double.compare(m2.getExpediente().getNotaMediaProvisional(), m1.getExpediente().getNotaMediaProvisional()))
        .collect(Collectors.toList());
    generarAsignaciones(otros);

  }

  private void generarAsignaciones(List<Matricula> matriculas) throws GrupoNoEncontradoException {
    for(Matricula matricula: matriculas){
      Expediente exp = matricula.getExpediente();
      // Consideramos la última encuesta que rellenó solo
      Encuesta prefs = exp.getEncuestas().stream().max((e1, e2) -> e1.getFechaEnvio().compareTo(e2.getFechaEnvio())).orElse(null);

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
          if(gpa.isEmpty()) throw new GrupoNoEncontradoException("No hay grupo para la asignatura " + am);
          grupo = gpa.get((int) (Math.random()*gpa.size())).getGrupo();
        }
        am.setGrupo(grupo);
        em.merge(am);
      };
    }
  }

  public List<Matricula> getAllMatriculas(){
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Matricula> cq = cb.createQuery(Matricula.class);
    Root<Matricula> rootEntry = cq.from(Matricula.class);
    CriteriaQuery<Matricula> all = cq.select(rootEntry);
    TypedQuery<Matricula> allQuery = em.createQuery(all);
    return allQuery.getResultList();
  }

}
