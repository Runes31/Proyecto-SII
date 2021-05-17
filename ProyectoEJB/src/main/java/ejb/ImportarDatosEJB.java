package ejb;

import domain.Alumno;
import domain.Asignatura;
import domain.Asignatura.AsignaturaId;
import domain.AsignaturasMatricula;
import domain.Encuesta;
import domain.Expediente;
import domain.Grupo;
import domain.GruposPorAsignatura;
import domain.GruposPorAsignatura.GruposPorAsignaturaId;
import domain.Matricula;
import domain.Optativa;
import domain.Titulacion;
import exceptions.AsignaturaNoEncontradaException;
import exceptions.ExpedienteNoEncontradoException;
import exceptions.TitulacionNoEncontradaException;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Stateless
public class ImportarDatosEJB implements ImportarDatos{
  
  private static final Logger LOG = Logger.getLogger(ImportarDatosEJB.class.getCanonicalName());
  
  @PersistenceContext(name="Proyecto-SII")
  private EntityManager em;
  
  @Override
  public void importarAlumnosExcel(File fichero) {
    try {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Asignatura> cq = cb.createQuery(Asignatura.class);
      Root<Asignatura> rootEntry = cq.from(Asignatura.class);
      CriteriaQuery<Asignatura> all = cq.select(rootEntry);
      TypedQuery<Asignatura> allQuery = em.createQuery(all);
      List<Asignatura> asignaturas = allQuery.getResultList();

      FileInputStream file = new FileInputStream(fichero);
      XSSFWorkbook workbook = new XSSFWorkbook(file);
      XSSFSheet sheet = workbook.getSheetAt(0);
      
      Iterator<Row> rowIterator = sheet.iterator();
      Row row = rowIterator.next();
      Iterator<Cell> cellIterator = row.cellIterator();
      cellIterator.next();
      String curso = cellIterator.next().toString();
      row = rowIterator.next();
      row = rowIterator.next();
      cellIterator = row.cellIterator();
      cellIterator.next();
      String estado = cellIterator.next().toString();
      row = rowIterator.next();
      while (rowIterator.hasNext()) {
        Alumno al = new Alumno();
        Expediente exp = new Expediente();
        Matricula m = new Matricula();
        row = rowIterator.next();
        cellIterator = row.cellIterator();
        al.setDni(cellIterator.next().toString());
        String nombre = cellIterator.next().toString();
        String apellido1 = cellIterator.next().toString();
        String apellido2 = cellIterator.next().toString();
        String nombreCompleto = nombre + " " + apellido1 + " " + apellido2;
        al.setNombre(nombreCompleto);
        exp.setNumExpediente((int) Double.parseDouble(cellIterator.next().toString()));
        m.setNumArchivo((int) Double.parseDouble(cellIterator.next().toString()));
        al.setEmailInstitucional(cellIterator.next().toString());
        al.setEmailPersonal(cellIterator.next().toString());
        al.setTelefono(cellIterator.next().toString());
        al.setMovil(cellIterator.next().toString());
        al.setDireccion(cellIterator.next().toString());
        al.setLocalidad(cellIterator.next().toString());
        al.setProvincia(cellIterator.next().toString());
        al.setCodigoPostal(cellIterator.next().toString());
        String fM = cellIterator.next().toString();
        Date fechaMatricula = new SimpleDateFormat("dd-MMMM-yyyy").parse(fM);
        m.setFechaMatricula(fechaMatricula);
        m.setTurnoPreferente(cellIterator.next().toString());
        m.setEstado(estado);
        m.setCursoAcademico(curso);
        m.setListadoAsignaturas(cellIterator.next().toString());
        exp.setCreditosSuperados((int) Double.parseDouble(cellIterator.next().toString()));
        exp.setCreditosFB((int) Double.parseDouble(cellIterator.next().toString()));
        exp.setCreditosOB((int) Double.parseDouble(cellIterator.next().toString()));
        exp.setCreditosOP((int) Double.parseDouble(cellIterator.next().toString()));
        exp.setCreditosCF((int) Double.parseDouble(cellIterator.next().toString()));
        exp.setCreditosPE((int) Double.parseDouble(cellIterator.next().toString()));
        exp.setCreditosTF((int) Double.parseDouble(cellIterator.next().toString()));

        em.persist(al);
        exp.setAlumno(al);
        // Hardcodeamos la gente a informatica porque en el excel no viene la titulacion y es necesario...
        exp.setTitulacion(em.find(Titulacion.class, "1041.0"));
        em.persist(exp);
        m.setExpediente(exp);
        em.persist(m);


        for(String asignatura: m.getListadoAsignaturas().split(",")){
          int dash = asignatura.lastIndexOf("-");
          asignatura = asignatura.substring(0, (dash > 0) ? dash : asignatura.length());
          int point = asignatura.lastIndexOf(".");
          String c = asignatura.substring(0, (point > 0) ? point : asignatura.length())+".0";
          Asignatura a = asignaturas.stream().filter(as -> as.getCodigo()
              .equals(c)) .findFirst().orElse(null);
          if(a == null) throw new AsignaturaNoEncontradaException("PK " + c + " y alumno " + al.getDni());
          AsignaturasMatricula am = new AsignaturasMatricula();
          am.setAsignatura(a);
          am.setMatricula(m);
          em.merge(am);
        }
      }
      file.close();
    } catch (Exception e) { e.printStackTrace(); }    
  }

  public void importarTitulacionExcel(File fichero) {
    try {
      FileInputStream file = new FileInputStream(fichero);
      XSSFWorkbook workbook = new XSSFWorkbook(file);
      XSSFSheet sheet = workbook.getSheetAt(0);
      
      Iterator<Row> rowIterator = sheet.iterator();
      Row row = rowIterator.next();
      while (rowIterator.hasNext()) {
        Titulacion t = new Titulacion();
        row = rowIterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();
        t.setCodigo(cellIterator.next().toString());
        t.setNombre(cellIterator.next().toString());
        t.setCreditos((int) Double.parseDouble(cellIterator.next().toString()));

        em.merge(t);
      }
      file.close();
    } catch (Exception e) { e.printStackTrace(); }    
  }
  
  
  public void importarAsignaturasExcel(File fichero) {
    try {
      FileInputStream file = new FileInputStream(fichero);
      try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
        for(int i = 2; i < workbook.getNumberOfSheets(); i++) {
          XSSFSheet sheet = workbook.getSheetAt(i);
          Iterator<Row> rowIterator = sheet.iterator();
          Row row = rowIterator.next();
          while (rowIterator.hasNext()) {
            Asignatura asig = new Asignatura();
            row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Titulacion titulacion = new Titulacion();
            titulacion.setCodigo(cellIterator.next().toString());
            String codigo = titulacion.getCodigo();
            if(codigo.equals("")) break;
            titulacion = em.find(Titulacion.class, titulacion.getCodigo());
            if(titulacion == null) throw new TitulacionNoEncontradaException("No se ha encontrado la titulaciÃ³n con cÃ³digo " + codigo);
            asig.setTitulacion(titulacion);
            asig.setOfertada(!cellIterator.next().toString().equalsIgnoreCase("no"));
            asig.setCodigo(cellIterator.next().toString());
            String referencia = cellIterator.next().toString();
            asig.setReferencia(referencia);
            asig.setNombre(cellIterator.next().toString());
            asig.setCurso((int) Double.parseDouble(cellIterator.next().toString()));
            asig.setCreditos((int) Double.parseDouble(cellIterator.next().toString()));
            cellIterator.next();
            cellIterator.next();
            asig.setCuatrimestre(cellIterator.next().toString()); 
            cellIterator.next();
            // Esta columna puede no estar definida
            if(cellIterator.hasNext())
              asig.setIdiomas(cellIterator.next().toString());
            
            XSSFSheet sheet0 = workbook.getSheetAt(0);
            Iterator<Row> rowIterator0 = sheet0.iterator();
            Row row0 = rowIterator0.next();
            int j = 0;
            Optativa optativa = new Optativa();
            optativa.setReferencia(referencia);
            optativa.setCodigo(asig.getCodigo());
            optativa.setCreditos(asig.getCreditos());
            optativa.setOfertada(asig.isOfertada());
            optativa.setTitulacion(asig.getTitulacion());
            optativa.setNombre(asig.getNombre());
            optativa.setCurso(asig.getCurso());
            optativa.setCuatrimestre(asig.getCuatrimestre());
            optativa.setIdiomas(asig.getIdiomas());
            while(rowIterator0.hasNext() && j == 0) {
              row0 = rowIterator0.next();
              Iterator<Cell> cellIterator0 = row0.cellIterator();
              if(referencia.equals(cellIterator0.next().toString())) {
                optativa.setPlazas((int) Double.parseDouble(cellIterator0.next().toString()));
                j++;
                em.merge(optativa);
              }
            }
              
            XSSFSheet sheet1 = workbook.getSheetAt(1);
            Iterator<Row> rowIterator1 = sheet1.iterator();
            Row row1 = rowIterator1.next();
            while(rowIterator1.hasNext() && j == 0) {
              row1 = rowIterator1.next();
              Iterator<Cell> cellIterator1 = row1.cellIterator();
              if(referencia.equals(cellIterator1.next().toString())) {
                optativa.setPlazas((int) Double.parseDouble(cellIterator1.next().toString()));
                optativa.setMencion(cellIterator1.next().toString());
                j++;
                em.merge(optativa);
              }
            }
            if(j == 0) { em.merge(asig); }
          }
        }
      file.close();
      }
    } catch (Exception e) { e.printStackTrace(); }    
  }
  
  @Override
  public void importarEncuestaExcel(File fichero) {
	  try {
	      FileInputStream file = new FileInputStream(fichero);
	      XSSFWorkbook workbook = new XSSFWorkbook(file);
	      XSSFSheet sheet = workbook.getSheetAt(0);
	      
	      Iterator<Row> rowIterator = sheet.iterator();
	      Row row = rowIterator.next();
	      while (rowIterator.hasNext()) {
          Encuesta e = new Encuesta();
	        row = rowIterator.next();
	        Iterator<Cell> cellIterator = row.cellIterator();
          Date fechaMatricula = new SimpleDateFormat("dd-MM-yyyy").parse(cellIterator.next().toString());
          e.setFechaEnvio(fechaMatricula);

	        int numExpediente = (int) Double.parseDouble(cellIterator.next().toString());
	        Expediente exp = em.find(Expediente.class, numExpediente);
	        if(exp == null) throw new ExpedienteNoEncontradoException();
	        e.setExpediente(exp);
	        String curso = cellIterator.next().toString();
	        String asignaturas = cellIterator.next().toString();
	        int grupo = (int) Double.parseDouble(cellIterator.next().toString());
	        List<GruposPorAsignatura> gpa = new ArrayList<>();
	        for(String asignatura: asignaturas.split(",")) {
            GruposPorAsignaturaId pk = new GruposPorAsignaturaId(curso, new AsignaturaId(asignatura, exp.getTitulacion().getCodigo()), grupo);
            gpa.add(em.find(GruposPorAsignatura.class, pk));
          }

	        e.setGruposPorAsignaturas(gpa);
	        em.merge(e);
	      }
	      file.close();
	    } catch (Exception e) { e.printStackTrace(); }    
	  
  }

  @Override
  public void importarGruposExcel(File fichero) {
    try {
      FileInputStream file = new FileInputStream(fichero);
      XSSFWorkbook workbook = new XSSFWorkbook(file);
      XSSFSheet sheet = workbook.getSheetAt(0);

      Iterator<Row> rowIterator = sheet.iterator();
      Row row = rowIterator.next();
      List<GruposPorAsignatura> gpaList = new ArrayList<>();
      while (rowIterator.hasNext()) {
        Grupo g = new Grupo();
        row = rowIterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();
        g.setCurso(cellIterator.next().toString());
        g.setLetra(cellIterator.next().toString());
        g.setTurno(cellIterator.next().toString());
        Titulacion t = em.find(Titulacion.class, cellIterator.next().toString());
        if(t == null) throw new TitulacionNoEncontradaException();
        g.setTitulacion(t);
        String cursoAcademico = cellIterator.next().toString();
        for(String asignatura: cellIterator.next().toString().split(",")) {
          int point = asignatura.lastIndexOf(".");
          asignatura = asignatura.substring(0, (point > 0) ? point : asignatura.length());
          Asignatura a = em.find(Asignatura.class, new AsignaturaId(asignatura+ ".0", g.getTitulacion().getCodigo()));
          if(a == null) throw new AsignaturaNoEncontradaException("Para la asignatura " + asignatura);
          GruposPorAsignatura gpa = new GruposPorAsignatura();
          gpa.setGrupo(g);
          gpa.setAsignatura(a);
          gpa.setCursoAcademico(cursoAcademico);
          gpa.setOferta(true);
          gpaList.add(gpa);
        }
        g.setIngles(cellIterator.next().toString().equals("Si"));
        g.setVisible(cellIterator.next().toString().equals("Si"));
        g.setAsignar(cellIterator.next().toString().equals("Si"));
        g.setPlazas((int) Double.parseDouble(cellIterator.next().toString()));

        em.persist(g);
      }
      gpaList.forEach(em::merge);
      file.close();
    } catch (Exception e) { e.printStackTrace(); }

  }
}