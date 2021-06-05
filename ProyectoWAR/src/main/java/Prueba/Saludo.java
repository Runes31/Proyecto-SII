package Prueba;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named
public class Saludo implements Serializable {
  private String name;
  private String clase;
  private List<String> cosas = new ArrayList<>(Arrays.asList("A", "B", "C"));
  private String selected;

  public String getSelected() {
    return selected;
  }

  public void setSelected(String selected) {
    this.selected = selected;
  }

  public void seleccionar(String s){
    selected = s;
  }

  public List<String> getCosas() {
    return cosas;
  }

  public void setCosas(List<String> cosas) {
    this.cosas = cosas;
  }

  public String getClase() {
    return clase;
  }

  public void setClase(String clase) {
    this.clase = clase;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String search(){
    return name + " --- " + clase;
  }
}
