package Prueba;

import ejb.ImportarDatos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

@ViewScoped
@Named
public class Importar {
  private Part alumnos;
  @Inject
  ImportarDatos ejb;

  public Part getAlumnos() {
    return alumnos;
  }

  public void setAlumnos(Part alumnos) {
    this.alumnos = alumnos;
  }

  public void process(){
    File f = new File("../DATOS/");

    File t = new File("../DATOS/Titulacion.xlsx");
    ejb.importarTitulacionExcel(t);
    File file = new File("../DATOS/Oferta_asignaturas.xlsx");
    ejb.importarAsignaturasExcel(file);
    ejb.importarAlumnosExcel(new File("../DATOS/alumnos.xlsx"));
    ejb.importarGruposExcel(new File("../DATOS/grupos.xlsx"));
    ejb.importarEncuestaExcel(new File("../DATOS/Encuesta.xlsx"));

    //if(alumnos != null) ejb.importarAlumnosExcel(getFile(alumnos));
  }

  private File getFile(Part part) {
    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
    File f = null;
    try(InputStream inputStream = part.getInputStream()) {
      f = File.createTempFile("alumnos", "xlsx");
      OutputStream outputStream = new FileOutputStream(f);
      byte[] buffer = new byte[1024];
      int length;
      while ((length = inputStream.read(buffer)) > 0){
        outputStream.write(buffer, 0, length);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return f;
  }
}
