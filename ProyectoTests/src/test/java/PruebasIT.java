// Generated by Selenium IDE
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import es.uma.informatica.sii.anotaciones.Requisitos;
import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PruebasIT {
  private WebDriver driver;
  JavascriptExecutor js;
  
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
  }
  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  @Requisitos({"006", "009", "012", "014", "015"})
  /*
  006, 012, 014 y 015 ya que estamos importando los datos
  009 porque estamos comprobando que los datos están y se han importado bien mirando en la vista que
  los datos se muestran
   */
  public void aaPruebaImportar() {
    File titulacion = new File("Titulacion.xlsx");
    File alumnos = new File("alumnos.xlsx");
    File asignaturas = new File("Oferta_asignaturas.xlsx");
    File grupos = new File("grupos.xlsx");
    File encuesta = new File("Encuesta.xlsx");
    driver.get("http://localhost:8080/ProyectoWAR/");
    driver.manage().window().maximize();
    driver.findElement(By.linkText("Importar")).click();
    driver.findElement(By.id("Importaciones:Alumnos")).sendKeys(alumnos.getAbsolutePath());
    driver.findElement(By.id("Importaciones:Titulaciones")).sendKeys(titulacion.getAbsolutePath());
    driver.findElement(By.id("Importaciones:Asignaturas")).sendKeys(asignaturas.getAbsolutePath());
    driver.findElement(By.id("Importaciones:Grupos")).sendKeys(grupos.getAbsolutePath());
    driver.findElement(By.id("Importaciones:Encuestas")).sendKeys(encuesta.getAbsolutePath());
    driver.findElement(By.id("Importaciones:submit")).click();
    try {
        Thread.sleep(3500);
    } catch (Exception e) {
        System.out.println(e);
    }

    driver.findElement(By.linkText("Home")).click();
    {
       List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(1) > td:nth-child(1)"));
       assert(elements.size() > 0);
    }
  }

  @Test
  @Requisitos({"009", "008"})
  /*
  008 ya que estamos modificando una matricula
  009 ya que estamos comprobando que esa matricula se ha cambiado en la vista y estamos usando los
  filtros de la vista además
   */
  public void modificarMatricula() {
    driver.get("http://127.0.0.1:8080/ProyectoWAR/");
    driver.manage().window().maximize();
    driver.findElement(By.id("matriculas:0:_mat")).click();
    try {
      Thread.sleep(7000);
    } catch (Exception e) {
      System.out.println(e);
    }
    driver.findElement(By.id("mat-form:nuevoIng")).click();
    try {
      Thread.sleep(3000);
    } catch (Exception e) {
      System.out.println(e);
    }
    driver.findElement(By.id("mat-form:sendMatricula")).click();
    driver.findElement(By.id("form:matriculado")).click();
    driver.findElement(By.id("form:search")).click();
    try {
      Thread.sleep(7000);
    } catch (Exception e) {
      System.out.println(e);
    }
    driver.findElement(By.id("matriculas:0:_mat")).click();
    try {
      Thread.sleep(7000);
    } catch (Exception e) {
      System.out.println(e);
    }
    {
      String value = driver.findElement(By.id("mat-form:nuevoIng")).getAttribute("value");
      assertThat(value, is("on"));
    }
    driver.findElement(By.id("mat-form:nuevoIng")).click();
    driver.findElement(By.id("mat-form:sendMatricula")).click();
  }

  @Test
  @Requisitos({"007"})
  /*
  007 ya que estamos modificando un expediente y comprobamos que los datos se vuelven a cargar en el modal
   */
  public void modificarExpediente() {
    driver.get("http://127.0.0.1:8080/ProyectoWAR/#");
    driver.manage().window().maximize();
    driver.findElement(By.id("matriculas:0:_exp")).click();
    try {
      Thread.sleep(7000);
    } catch (Exception e) {
      System.out.println(e);
    }
    driver.findElement(By.id("exp-form:media")).clear();
    driver.findElement(By.id("exp-form:media")).sendKeys("8.5");
    driver.findElement(By.id("exp-form:sendExp")).click();
    driver.findElement(By.id("matriculas:0:_exp")).click();
    try {
      Thread.sleep(7000);
    } catch (Exception e) {
      System.out.println(e);
    }
    {
      String value = driver.findElement(By.id("exp-form:media")).getAttribute("value");
      assertThat(value, is("8.5"));
    }
    driver.findElement(By.id("exp-form:media")).clear();
    driver.findElement(By.id("exp-form:media")).sendKeys("3");
    driver.findElement(By.cssSelector("#id01 .w3-button")).click();
    driver.findElement(By.id("matriculas:0:_exp")).click();
    try {
      Thread.sleep(7000);
    } catch (Exception e) {
      System.out.println(e);
    }
    {
      String value = driver.findElement(By.id("exp-form:media")).getAttribute("value");
      assertThat(value, is("8.5"));
    }
    driver.findElement(By.id("exp-form:media")).clear();
    driver.findElement(By.id("exp-form:media")).sendKeys("0.0");
    driver.findElement(By.id("exp-form:sendExp")).click();
  }

  @Test
  @Requisitos({"003"})
  /*
  003 ya que estamos modificando la asignaturas y comprobando en la tabla que se han cambiado
   */
  public void modificarAsignatura() {
    driver.get("http://127.0.0.1:8080/ProyectoWAR/");
    driver.manage().window().maximize();
    driver.findElement(By.linkText("Asignaturas")).click();
    driver.findElement(By.id("frm:asignaturas:1:__edit")).click();
    try {
      Thread.sleep(7000);
    } catch (Exception e) {
      System.out.println(e);
    }
    driver.findElement(By.id("asig-form:idiomas")).clear();
    driver.findElement(By.id("asig-form:idiomas")).sendKeys("Inglés");
    driver.findElement(By.id("asig-form:sendAsig")).click();
    try {
      Thread.sleep(3000);
    } catch (Exception e) {
      System.out.println(e);
    }
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(9)")).getText(), is("Inglés"));
    driver.findElement(By.id("frm:asignaturas:1:__edit")).click();
    try {
      Thread.sleep(5000);
    } catch (Exception e) {
      System.out.println(e);
    }
    driver.findElement(By.id("asig-form:idiomas")).clear();
    driver.findElement(By.id("asig-form:sendAsig")).click();
  }

  @Test
  @Requisitos({"005"})
  // 005 Ya que modificamos un alumno y comprobamos que sus datos han cambiado
  public void modificarAlumno() {
    driver.get("http://127.0.0.1:8080/ProyectoWAR/");
    driver.manage().window().maximize();
    driver.findElement(By.id("matriculas:0:_alu")).click();
    try {
      Thread.sleep(7000);
    } catch (Exception e) {
      System.out.println(e);
    }
    driver.findElement(By.id("alu-form:dni")).clear();
    driver.findElement(By.id("alu-form:dni")).sendKeys("78945612A");
    driver.findElement(By.id("alu-form:nombre")).clear();
    driver.findElement(By.id("alu-form:nombre")).sendKeys("Gregoria Gómez Pabon");
    driver.findElement(By.id("alu-form:sendAlu")).click();
    try {
      Thread.sleep(7000);
    } catch (Exception e) {
      System.out.println(e);
    }
    driver.findElement(By.cssSelector("tbody > tr:nth-child(1)")).click();
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")).getText(), is("78945612A"));
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(1)")).click();
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(1)")).getText(), is("Gregoria Gómez Pabon"));
    driver.findElement(By.id("matriculas:0:_alu")).click();
    try {
      Thread.sleep(7000);
    } catch (Exception e) {
      System.out.println(e);
    }
    driver.findElement(By.id("alu-form:nombre")).clear();
    driver.findElement(By.id("alu-form:nombre")).sendKeys("Gregorina Gómez Pabon");
    driver.findElement(By.id("alu-form:dni")).clear();
    driver.findElement(By.id("alu-form:dni")).sendKeys("83582041G");
    driver.findElement(By.id("alu-form:sendAlu")).click();
  }

  @Test
  @Requisitos({"009"})
  // 009 ya que estamos comprobando que podemos filtrar las matriculas que visualizamos por su titulación
  public void buscarTitulacion() {
    driver.get("http://127.0.0.1:8080/ProyectoWAR/");
    driver.manage().window().maximize();
    driver.findElement(By.id("form:titulacion")).click();
    driver.findElement(By.id("form:titulacion")).sendKeys("Software");
    driver.findElement(By.id("form:search")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(4)")).click();
    //Dormimos para que de tiempo a recargar la p�gina ya que con
    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); no funciona
    try {
      Thread.sleep(7000);
    } catch (Exception e) {
      System.out.println(e);
    }
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(1) > td:nth-child(4)"));
      assert(elements.size() > 0);
    }
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(4)")).getText(), is("Grado en Ingeniería del Software"));
  }

  @Test
  @Requisitos({"009"})
  // 009 ya que estamos comprobando que podemos filtrar las matriculas que visualizamos por su curso
  public void buscarCurso() {
    driver.get("http://127.0.0.1:8080/ProyectoWAR/");
    driver.manage().window().setSize(new Dimension(1936, 1056));
    driver.findElement(By.id("form:curso")).click();
    driver.findElement(By.id("form:curso")).sendKeys("2020/2021");
    driver.findElement(By.id("form:search")).click();
    try {
      Thread.sleep(5000);
    } catch (Exception e) {
      System.out.println(e);
    }
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(1) > td:nth-child(5)"));
      assert(elements.size() > 0);
    }
  }

  @Test
  @Requisitos({"009"})
  // 009 ya que estamos comprobando que podemos filtrar las matriculas que visualizamos por su nombre de alumno
  public void buscarAlumno() {
    driver.get("http://127.0.0.1:8080/ProyectoWAR/");
    driver.manage().window().maximize();
    driver.findElement(By.id("form:name")).click();
    driver.findElement(By.id("form:name")).sendKeys("Rocco");
    driver.findElement(By.id("form:search")).click();
    driver.findElement(By.cssSelector("td:nth-child(1)")).click();
    //Dormimos para que de tiempo a recargar la página ya que con
    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); no funciona
    try {
      Thread.sleep(5000);
    } catch (Exception e) {
      System.out.println(e);
    }
    assertThat(driver.findElement(By.cssSelector("td:nth-child(1)")).getText(), is("Rocco Altamirano Henriquez"));
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("td:nth-child(1)"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector("td:nth-child(2)")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("td:nth-child(2)"));
      assert(elements.size() > 0);
    }
  }
  
  @Test
  @Requisitos({"001","011"})
  //001 ya que estamos asignamos grupos a cada uno de los alumnos
  //011 en el momento en el que se asignan se filtran en la tabla y podemos comprobar como hay alumnos con grupos asignados
  public void asignarGrupos() {
    driver.get("http://localhost:8080/ProyectoWAR/index.xhtml");
    driver.manage().window().maximize();
    driver.findElement(By.id("form:asignar")).click();
    try {
       Thread.sleep(5000);
    } catch (Exception e) {
       System.out.println(e);
    }
    driver.findElement(By.id("form:grupo")).sendKeys("a");
    driver.findElement(By.id("form:search")).click();
    try {
       Thread.sleep(5000);
    } catch (Exception e) {
       System.out.println(e);
    }
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(1) > td:nth-child(1)"));
      assert(elements.size() > 0);
    }
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(6)")).getText(), is(not("101-, 105-, 103-, 109-, 110-, 202-, 204-, 205-, 206-, 208-, 209-")));
  }
  
  @Test
  @Requisitos({"005"})
  // 005 ya que estamos borrando una asignatura, lo cual hace que se quede deshabilitida
  public void borrarAsignatura() {
    driver.get("http://localhost:8080/ProyectoWAR/index.xhtml");
    driver.manage().window().maximize();
    driver.findElement(By.linkText("Asignaturas")).click();
    driver.findElement(By.id("frm:asignaturas:0:__borrar")).click();
    driver.findElement(By.id("frm:asignaturas:0:__borrar")).isEnabled();
  }
  
  @Test
  @Ignore
  @Requisitos({"010"})
  // 010 ya que estamos asign�ndole manualmente a una asignatura su grupo
  public void asignarAsignaturaGrupo() {
    driver.get("http://127.0.0.1:8080/ProyectoWAR/");
    driver.manage().window().maximize();
    
  }
  
  @Test
  @Requisitos({"013"})
  // 013 ya que estamos viendo como existe un lugar donde se podr�n editar las preferencias
  public void editarPreferencais() {
 	 driver.get("http://localhost:8080/ProyectoWAR/index.xhtml");
 	 driver.manage().window().maximize();
 	 driver.findElement(By.id("matriculas:0:_pref")).click();
 	 assertThat(driver.findElement(By.cssSelector("#pref-form h2")).getText(), is("Editar preferencias"));
 	 driver.findElement(By.id("pref-form:sendPref")).click();
  }
  
  @Test
  @Requisitos({"010"})
  // 010 ya que a�adimos una asignatura a un grupo
  public void untitled() {
    driver.get("http://127.0.0.1:8080/ProyectoWAR/");
    driver.manage().window().maximize();
    driver.findElement(By.linkText("Asignaturas")).click();
    driver.findElement(By.id("frm:asignaturas:1:__edit")).click();
    try {
       Thread.sleep(5000);
    } catch (Exception e) {
       System.out.println(e);
    }
    driver.findElement(By.id("asig-form:j_idt65:0:j_idt68")).click();
    driver.findElement(By.id("asig-form:sendAsig")).click();
    driver.findElement(By.id("frm:asignaturas:1:__edit")).click();
    try {
        Thread.sleep(5000);
    } catch (Exception e) {
        System.out.println(e);
    }
    assertThat(driver.findElement(By.cssSelector(".w3-row-padding:nth-child(1)")).getText(), is(not("1.0-a")));
    driver.findElement(By.id("asig-form:sendAsig")).click();
  }
  
}