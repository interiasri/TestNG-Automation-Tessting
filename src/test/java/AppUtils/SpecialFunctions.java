package AppUtils;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class SpecialFunctions extends AppFun {
	
	@BeforeTest
	public void login() {
		driver.findElement(By.name("email")).sendKeys("sridhar_interia@outlook.com");
		driver.findElement(By.name("password")).sendKeys("Sridhar@1234567");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	
//	Login Information for flights.qedgetech.com("sridhar_interia@outlook.com", "Sridhar@1234567");
	
	
	@AfterTest
	public void logout() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/header/nav/div/div[3]/ul/li[2]/div/a/i")).click();
		driver.findElement(By.linkText("Logout")).click();
	}

}
