import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class BuscarAlumnoIT {
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
  public void prueba() {
    driver.get("http://127.0.0.1:8080/ProyectoWAR/");
    driver.manage().window().maximize();
    driver.findElement(By.id("form:name")).click();
    driver.findElement(By.id("form:name")).sendKeys("Rocco");
    driver.findElement(By.id("form:search")).click();
    driver.findElement(By.cssSelector("td:nth-child(1)")).click();
    //Dormimos para que de tiempo a recargar la p�gina ya que con
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
}

