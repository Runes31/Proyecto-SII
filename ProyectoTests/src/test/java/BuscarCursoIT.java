// Generated by Selenium IDE
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class BuscarCursoIT {
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
    driver.quit();
  }
  @Test
  public void prueba() {
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
}
