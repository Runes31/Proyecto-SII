package ejb;

import domain.Asignatura;
import domain.AsignaturasMatricula;
import domain.Encuesta;
import domain.Expediente;
import domain.Grupo;
import domain.GruposPorAsignatura;
import exceptions.ExpedienteNoEcontradoException;
import exceptions.GrupoNoEncontradoException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import domain.Matricula;
import domain.Titulacion;
import exceptions.MatriculaNoEncontradaException;
import exceptions.ProyectoException;
import exceptions.TitulacionNoEncontradaException;

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

  @Override
  public List<AsignaturasMatricula> listarAsignaciones() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<AsignaturasMatricula> cq = cb.createQuery(AsignaturasMatricula.class);
    Root<AsignaturasMatricula> rootEntry = cq.from(AsignaturasMatricula.class);
    CriteriaQuery<AsignaturasMatricula> all = cq.select(rootEntry);
    TypedQuery<AsignaturasMatricula> allQuery = em.createQuery(all);
    return allQuery.getResultList();
  }

  @Override
  public List<AsignaturasMatricula> listarAsignaciones(boolean nuevoIngreso){
    List<AsignaturasMatricula> asignaciones = listarAsignaciones();
    return asignaciones.stream().filter(a -> a.getMatricula().isNuevoIngreso() == nuevoIngreso).collect(
        Collectors.toList());
  }

  @Override
  public Map<Asignatura, List<GruposPorAsignatura>> generarEncuesta(Matricula matricula) throws MatriculaNoEncontradaException {
    if(em.find(Matricula.class, matricula) == null) throw new MatriculaNoEncontradaException();
    Map<Asignatura, List<GruposPorAsignatura>> res = new HashMap<>();

    matricula.getAsignaturasMatriculas().forEach(am -> {
      res.put(am.getAsignatura(), am.getAsignatura().getGruposPorAsignatura());
    });

    return res;
  }

  @Override
  public Encuesta obtenerPreferencias(Matricula matricula) throws MatriculaNoEncontradaException {
    if(em.find(Matricula.class, matricula) == null) throw new MatriculaNoEncontradaException();
    return matricula.getExpediente().getEncuestas().stream()
        .max((e1, e2) -> e1.getFechaEnvio().compareTo(e2.getFechaEnvio())).orElse(null);
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
	public List<Matricula> visualizarMatricula(Titulacion titulacion,Grupo grupo, Expediente expediente) throws ProyectoException {
		List<Matricula> matriculas;
		Expediente t  =em.find(Expediente.class, expediente);
	    if(t==null) throw new ExpedienteNoEcontradoException();
		matriculas = getAllMatriculas().stream().filter(a -> a.getExpediente().equals(expediente)).collect(Collectors.toList());
		
		return matriculas;
		
		 
		
	}
	
	public List<Matricula> MatriculasExpediente(Expediente expediente){
		Expediente t  =em.find(Expediente.class, expediente);
	    if(t==null) throw new ExpedienteNoEcontradoException();
	    visualizarMatricula();
	}
	/*
	 * List<Matricula> matriculas = getAllMatriculas().stream().collect(Collectors.toList());
	 * return matriculaas;
	 */

}
