package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Testhacker {
	public static void main(String[] args) {
	System.setProperty("webdriver.chrome.driver","C:\\Users\\dhruv\\Downloads\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	
	driver.get("http://localhost/hackathon/");
	driver.manage().window().maximize();
	
	driver.findElement(By.className("menu-has-children")).click();
	driver.findElement(By.id("email")).sendKeys("abc@gmail.com") ;
	
	driver.findElement(By.id("type")).sendKeys("Student") ;
	
	driver.findElement(By.id("login-submit")).click();
	driver.navigate().back();
	
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	jse.executeScript("scroll(0,3000)");
	
	
	driver.findElement(By.id("name")).sendKeys("Hello testing") ;
	driver.findElement(By.id("email")).sendKeys("abc@gmail.com") ;
	driver.findElement(By.id("subject")).sendKeys("Welcome to selenium") ;
	
	
	}
}
