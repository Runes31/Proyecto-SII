// Generated by Selenium IDE
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PruebaImportacionIT {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  
  @Before
  public void setUp() {
	System.setProperty("webdriver.chrome.driver","./src/test/resources/drivers/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    //driver.quit();
  }
  @Test
  public void prueba() {
	File titulacion = new File("/Proyecto-SII/DATOS/Titulacion.xlsx");
	File alumnos = new File("alumnos.xlsx");
	File asignaturas = new File("Oferta_asignaturas.xlsx");
	File grupos = new File("grupos.xlsx");
	File encuesta = new File("Encuesta.xlsx");
    driver.get("http://localhost:8080/ProyectoWAR/");
    driver.manage().window().setSize(new Dimension(1936, 1056));
    driver.findElement(By.linkText("Importar")).click();
    driver.findElement(By.id("Importaciones:Alumnos")).sendKeys(alumnos.getAbsolutePath());
    driver.findElement(By.id("Importaciones:Titulaciones")).sendKeys(titulacion.getAbsolutePath());
    driver.findElement(By.id("Importaciones:Asignaturas")).sendKeys(asignaturas.getAbsolutePath());
    driver.findElement(By.id("Importaciones:Grupos")).sendKeys(grupos.getAbsolutePath());
    driver.findElement(By.id("Importaciones:Encuestas")).sendKeys(encuesta.getAbsolutePath());
    driver.findElement(By.id("Importaciones:submit")).click();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.findElement(By.linkText("Home")).click();
  }
}