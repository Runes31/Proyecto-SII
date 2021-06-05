package beans;

import ejb.ImportarDatos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.apache.commons.io.FileUtils;

@RequestScoped
@Named
public class ImportarBean implements Serializable{
  private Part titulaciones;
  private Part alumnos;
  private Part asignaturas;
  private Part grupos;
  private Part encuestas;
  @Inject
  ImportarDatos ejb;

  public Part getTitulaciones() {
    return titulaciones;
  }

  public void setTitulaciones(Part titulaciones) {
    this.titulaciones = titulaciones;
  }

  public void process() throws IOException {
    if(titulaciones != null) ejb.importarTitulacionExcel(getFile(titulaciones));
    if(asignaturas != null) ejb.importarAsignaturasExcel(getFile(asignaturas));
    if(alumnos != null) ejb.importarAlumnosExcel(getFile(alumnos));
    if(grupos != null) ejb.importarGruposExcel(getFile(grupos));
    if(encuestas != null) ejb.importarEncuestaExcel(getFile(encuestas));
  }

  private File getFile(Part part) throws IOException {
    File f = File.createTempFile("titulaciones", "xlsx");
    InputStream input = null;
    OutputStream output = null;
    try {
      input = part.getInputStream();
      output = new FileOutputStream(f);

      // commons-io
      FileUtils.copyInputStreamToFile(input, f);
    } finally {
      if (output != null)
        try {output.close();} catch (IOException logOrIgnore) {}
      if (input != null)
        try {input.close();} catch (IOException logOrIgnore) {}
    }
    return f;
  }

  public Part getAlumnos() {
    return alumnos;
  }

  public void setAlumnos(Part alumnos) {
    this.alumnos = alumnos;
  }

  public Part getAsignaturas() {
    return asignaturas;
  }

  public void setAsignaturas(Part asignaturas) {
    this.asignaturas = asignaturas;
  }

  public Part getGrupos() {
    return grupos;
  }

  public void setGrupos(Part grupos) {
    this.grupos = grupos;
  }

  public Part getEncuestas() {
    return encuestas;
  }

  public void setEncuestas(Part encuestas) {
    this.encuestas = encuestas;
  }
}