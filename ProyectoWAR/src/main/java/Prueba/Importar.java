package Prueba;

import ejb.ImportarDatos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

@ViewScoped
@Named
public class Importar implements Serializable{
  private Part titulaciones;
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

    File t = new File("../DATOS/Titulacion.xlsx");
    ejb.importarTitulacionExcel(t);
    File file = new File("../DATOS/Oferta_asignaturas.xlsx");
    ejb.importarAsignaturasExcel(file);
    ejb.importarAlumnosExcel(new File("../DATOS/alumnos.xlsx"));
    ejb.importarGruposExcel(new File("../DATOS/grupos.xlsx"));
    ejb.importarEncuestaExcel(new File("../DATOS/Encuesta.xlsx"));

    //if(alumnos != null) ejb.importarAlumnosExcel(getFile(alumnos));
  }

  private File getFile(Part part) throws IOException {
    
    File f = File.createTempFile("titulaciones", "xlsx");
    InputStream input = null;
    OutputStream output = null;
    /*try {
      input = part.getInputStream();
      output = new FileOutputStream(f);
      byte[] buffer = new byte[8192];
      int length;
      while((length = input.read(buffer)) != -1){
        output.write(buffer, 0, length);
      }
    } finally {
      if (output != null)
        try {output.close();} catch (IOException logOrIgnore) {}
      if (input != null)
        try {input.close();} catch (IOException logOrIgnore) {}
    }   
    return f;*/
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
}