package stepDefn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AmazonDefn {
	public static WebDriver driver;

	@Given("^we are working on Amazon$")
	public void Amazon_Home() throws IOException {
		driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		driver.get("http://www.amazon.com");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath(get_value("login_click"))).click();
	}

	@When("^we input username as \"(.*)\" password as \"(.*)\" an click on login botton$")
	public void login_to_Amazon(String user, String pass) throws IOException {
//		File file=new File("C:\\Users\\Freeware Sys\\eclipse-workspace\\cucumber_gorup\\src\\test\\java\\util\\pasword.xlsx");
//		FileInputStream fis=new FileInputStream(file);
//		XSSFWorkbook wb=new XSSFWorkbook(fis);
//		Sheet st=wb.getSheetAt(0);
//		Row rw=st.getRow(2);
//		Cell user1=rw.getCell(0);
//		Cell pass1=rw.getCell(1);
//		String user2=user1.getStringCellValue();
//		String pass2=pass1.getStringCellValue();
		driver.findElement(By.xpath(get_value("username"))).sendKeys(user);
		driver.findElement(By.xpath(get_value("password"))).sendKeys(pass);
		driver.findElement(By.xpath(get_value("login_amazon"))).click();
	}

	@Then("^we search for \"(.*)\"$")
	public void search_item(String item) {
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(item); 
		 driver.findElement(By.xpath("//input[@value='Go']")).click();
	}

	@Then("^we click on selected laptop as \"([^\"]*)\"$")
	public void select_item(String selected) {
		driver.findElement(By.partialLinkText("ASUS Chromebook C202SA-YS02 11.6")).click();
	}

	@And("^ckeck out our item$")
	public void check_out() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//button[@id='siNoCoverage-announce']")).click();
	}

	@And("^Logout from Amazom$")
	public void logout_Amazon() throws InterruptedException {
		 Actions ac=new Actions(driver);
		 ac.moveToElement(driver.findElement(By.xpath("//span[contains(.,'Hello, selenium')]"))).build().perform();
		 System.out.println(driver.findElements(By.tagName("iframe")).size());
		 Thread.sleep(5000);
		 driver.findElement(By.linkText("Not selenium? Sign Out")).click();
		Thread.sleep(5000);
		 driver.close();
	}
	public static String get_value(String arg) throws IOException {
		String value="";
		File file=new File("C:\\Users\\Freeware Sys\\eclipse-workspace\\cucumber_gorup\\src\\test\\java\\property\\Amazon.properties");
		FileInputStream fis1=new FileInputStream(file);
		Properties pro=new Properties();
		pro.load(fis1);
		value=pro.getProperty(arg);
		return value;
	}
}
