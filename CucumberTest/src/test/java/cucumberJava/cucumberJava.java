/*
Autor: Alexander Martínez
Fecha: 11/01/2019
Ver. 1.0
*/
package cucumberJava; 

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When; 

public class cucumberJava { 
	
   WebDriver driver = null; 
   
   @Given("^Que tengo que invocar el navegador$") /*Dado que tengo que abrir el navegador*/
   public void openBrowser() { 
	   System.setProperty("webdriver.chrome.driver", Thread.currentThread().getContextClassLoader().getResource("drivers/chromedriver.exe").getFile());	   
	  //chromeOptions.setBinary("D:\\Program Files\\Google\\GoogleChromePortableBeta\\GoogleChromePortable.exe"); /*Ubica mi portable*/
	  //driver = new FirefoxDriver(); 
	   
	   ChromeOptions options = new ChromeOptions();
	   options.addArguments("--no-sandbox"); /*Soluciono error buscando binario*/
       options.addArguments("--disable-dev-shm-usage"); /*Soluciono error buscando binario*/
       driver = new ChromeDriver();
   } 
 
   @When("^Necesite abrir el portal de busquedas de Google$")  /*Cuando yo necesite abrir Google*/
   public void goToGoogle() { 
	   driver.get("https://google.com");
	   driver.manage().window().maximize();
	   driver.getTitle();
   } 
	
   @Then("^Podre buscar e ingresar al sitio web de Falabella$")  /*Para entonces ubicar el input de consulta e ingresar mi valor...*/
   public void inputFieldSearch() { 
	   	 System.out.println("Checking step by step");
         WebElement inputFieldSearch = driver.findElement(ByXPath.xpath("//*[@id=\"tsf\"]/div[2]/div/div[1]/div/div[1]/input"));
         if(inputFieldSearch.isDisplayed()) { 
            System.out.println("Check input fiel Google: OK"); 
            inputFieldSearch.sendKeys("falabella.com");
            inputFieldSearch.submit();
            //driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS); /**Puede ser*/
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
            WebElement search = driver.findElement(By.partialLinkText("Falabella.com - Mejor Compra Online"));
            search.click();
            WebElement logoPageFalabella = driver.findElement(ByXPath.xpath("//*[@id=\"header\"]/nav/div[1]/div/div[1]/a[1]/i"));
            if(logoPageFalabella.isDisplayed()) {
            	System.out.println("Check open Falabella website: OK");
            }
            else {
            	System.out.println("Check open Falabella website: FAIL");
            }
      } else { 
    	 System.out.println("Check input fiel Google: FAIL"); 
      } 
   } 
   
   @After
   public void tearDown() throws InterruptedException
   {
	 //driver.close(); /*Bingo llegó :D*/
   }
   
   
   
   
   
}

