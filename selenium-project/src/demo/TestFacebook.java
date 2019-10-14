package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestFacebook {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\dhruv\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.facebook.com");
		driver.manage().window().maximize();
		driver.findElement(By.id("u_0_m")).sendKeys("Abhinav") ;
		driver.findElement(By.id("u_0_o")).sendKeys("Jha") ;
		driver.findElement(By.id("u_0_r")).sendKeys("9766318407") ;
		driver.findElement(By.id("u_0_7")).click();
		Select sel1 = new Select(driver.findElement(By.id("day")));
		sel1.selectByIndex(13);
		Select sel2 = new Select(driver.findElement(By.id("month")));
		sel2.selectByVisibleText("Oct");
		Select sel3 = new Select(driver.findElement(By.id("year")));
		sel3.selectByValue("2019");
		
		driver.findElement(By.id("u_0_15")).click();
		driver.findElement(By.className("_8esh")).click();
		driver.navigate().back();
		String expectedTitle = "Facebook – log in or sign up";
        String actualTitle = "";
        actualTitle = driver.getTitle();
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
            System.out.println(actualTitle);
        }
		
	}
}
