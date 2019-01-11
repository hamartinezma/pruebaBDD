/*
Autor: Alexander Martínez
Fecha: 11/01/2019
Ver. 1.0
*/
package cucumberJava; 

import java.util.concurrent.TimeUnit;

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
	   	 System.out.println("Checking inputFieldSearch step by step");
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
   
   
   
   @Given("^Que tengo que buscar un producto desde la barra de busqueda$") 
   public void searchProduct() { 
	   System.out.println("Checking searchProduct step by step");
       //WebElement inputsearchProduct = driver.findElement(by) 
	   driver.navigate().to("https://www.falabella.com/falabella-cl/search/?Ntt=cama");
       //https://www.falabella.com/falabella-cl/search/?Ntt=cama revisar 
       //inputsearchProduct.sendKeys("cama");
       //inputsearchProduct.submit();
       WebElement selectProduct = driver.findElement(ByXPath.xpath("//*[@id=\"all-pods\"]/div[2]/div[2]/a[1]/div/img"));
       selectProduct.click();
       WebDriverWait wait = new WebDriverWait(driver, 5);
       wait.until(ExpectedConditions.presenceOfElementLocated(ByXPath.xpath("//*[@id=\"huinchatrackeo\"]/div/div/a/div[3]")));
   } 
   
   @When("^debo visualizar el detalle del producto seleccionado$")  
   public void viewDetail() {
	   WebElement productDetail = driver.findElement(ByXPath.xpath("//*[@id=\"main\"]/div[4]/div/article/div/header[1]/h2"));
	   productDetail.click();
   }
   
   @When("^lo Podre agregar a la bolsa de products$")  
   public void addProduct() {
	   WebElement buttonAddProduct = driver.findElement(ByXPath.xpath("//*[@id=\"fbra_browseMainProduct\"]/div/div/div[2]/div/div[7]/button"));
	   buttonAddProduct.click();
	   driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	   WebElement buttonViewProductBag = driver.findElement(ByXPath.xpath("//*[@id=\"fb-modal-add\"]/div[3]/div[2]/a"));
	   buttonViewProductBag.click();
   }
   
   
     
   
   @Given("^Que tengo que aumentar en 2 el producto agregado en la bolsa de productos$") 
   public void addTwoUnids() {
	   System.out.println("Checking addTwoUnids step by step");
	   WebElement inputAmount = driver.findElement(ByXPath.xpath("//*[@id=\"fb-basket-products\"]/div[3]/section/section/form/section/div[2]/div/input"));
	   inputAmount.sendKeys("value", "2");
   } 
   
   @When("^necesito agregarle una garantia extendida de 2 anios$") 
   public void addWarranty() {
	   System.out.println("No existe la opción");	   
   }
   
   @When("^Podre darle clic al boton de Ir A compras$")
   public void clickShoppingButton() {
	   WebElement buyButton = driver.findElement(ByXPath.xpath("/html/body/div[1]/main/div/div[2]/div[3]/div/div[1]/form/div[2]/div[2]/div[2]/button"));
	   buyButton.click();
   }
   
   
         
   
   @After
   public void tearDown() throws InterruptedException
   {
	 //driver.close(); /*Bingo llegó :D*/
   }
   
   
   
   
   
}

