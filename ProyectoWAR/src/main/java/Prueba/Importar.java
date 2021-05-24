package Prueba;

import ejb.ImportarDatos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

  public void process() throws IOException{
    if(alumnos != null) ejb.importarTitulacionExcel(getFile(alumnos));
  }

  private File getFile(Part part) throws IOException {
    
    /**String filename = part.getSubmittedFileName();
    String prefix = filename;
    String suffix = "";
    if (filename.contains("."))
    {
        prefix = filename.substring(0, filename.lastIndexOf('.'));
        suffix = filename.substring(filename.lastIndexOf('.'));
    }
    File file = File.createTempFile("alumnos", "xlsx");
    
    InputStream input = null;
    OutputStream output = null;
    try {
      input = new BufferedInputStream(part.getInputStream(), 2048);
      output = new BufferedOutputStream(new FileOutputStream(file), 2048);
      byte[] buffer = new byte[2048];
      for (int length = 0; ((length = input.read(buffer)) > 0);){
        output.write(buffer, 0, length);
      }
    } finally {
      if (output != null)
        try {output.close();} catch (IOException logOrIgnore) {}
      if (input != null)
        try {input.close();} catch (IOException logOrIgnore) {}
    }
    part.delete();
    return file;
    }*/
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
