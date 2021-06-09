import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import es.uma.informatica.sii.anotaciones.Requisitos;

public class Pruebas {
  private WebDriver driver;
  JavascriptExecutor js;
  
  @Before
  public void setUp() {
	System.setProperty("webdriver.chrome.driver","./src/test/resources/drivers/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
  }
  @After
  public void tearDown() {
    //driver.quit();
  }
 @Test
 @Requisitos({"009"})
 // 009 ya que estamos comprobando que podemos filtrar las matriculas que visualizamos por su titulación
 public void buscarTitulacion() {
	 driver.get("http://localhost:8080/ProyectoWAR/index.xhtml");
	 driver.manage().window().maximize();
	 driver.findElement(By.id("matriculas:0:_pref")).click();
	 assertThat(driver.findElement(By.cssSelector("#pref-form h2")).getText(), is("Editar preferencias"));
	 driver.findElement(By.id("pref-form:sendPref")).click();
 }
}