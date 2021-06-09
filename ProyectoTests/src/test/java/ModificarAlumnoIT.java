// Generated by Selenium IDE
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ModificarAlumnoIT {
  private WebDriver driver;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	//System.setProperty("webdriver.chrome.driver","./src/test/resources/drivers/chromedriver.exe");
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
    driver.findElement(By.id("matriculas:0:_alu")).click();
    try {
        Thread.sleep(7000);
    } catch (Exception e) {
        System.out.println(e);
    }
    driver.findElement(By.id("alu-form:dni")).clear();
    driver.findElement(By.id("alu-form:dni")).sendKeys("78945612A");
    driver.findElement(By.id("alu-form:nombre")).clear();
    driver.findElement(By.id("alu-form:nombre")).sendKeys("Gregoria G�mez Pabon");
    driver.findElement(By.id("alu-form:sendAlu")).click();
    try {
        Thread.sleep(7000);
    } catch (Exception e) {
        System.out.println(e);
    }
    driver.findElement(By.cssSelector("tbody > tr:nth-child(1)")).click();
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")).getText(), is("78945612A"));
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(1)")).click();
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(1)")).getText(), is("Gregoria G�mez Pabon"));
    driver.findElement(By.id("matriculas:0:_alu")).click();
    try {
        Thread.sleep(7000);
    } catch (Exception e) {
        System.out.println(e);
    }
    driver.findElement(By.id("alu-form:nombre")).clear();
    driver.findElement(By.id("alu-form:nombre")).sendKeys("Gregorina G�mez Pabon");
    driver.findElement(By.id("alu-form:dni")).clear();
    driver.findElement(By.id("alu-form:dni")).sendKeys("83582041G");
    driver.findElement(By.id("alu-form:sendAlu")).click();
  }
}