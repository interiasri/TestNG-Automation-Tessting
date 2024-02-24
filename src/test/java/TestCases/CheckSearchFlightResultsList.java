package TestCases;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AppUtils.SpecialFunctions;



public class CheckSearchFlightResultsList extends SpecialFunctions{
	
	Select from, to;
	
	@Parameters({"From", "To"})
	@Test(priority=1)
	public void checkflightsRoute(String From, String To) {
		from = new Select(driver.findElement(By.xpath("//select[contains(@class,'sf2')]")));
		to = new Select(driver.findElement(By.xpath("//select[contains(@class,'sf3')]")));
		from.selectByVisibleText(From);
		to.selectByVisibleText(To);
	}
	
	@Parameters({"m","y","d", "airlineName"})
	@Test(priority=2)
	public void selectFlight(String m, String y, String d, String airlineName) throws InterruptedException, IOException {
		CheckPickFlightDate.CheckPickDate(m, y, d);
		driver.findElement(By.xpath("//button[contains(@class,'btn-search')]")).click();
		Thread.sleep(2000);
		try {
			List<WebElement> ftrows=driver.findElement(By.className("flights_table")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
			for(int i=1; i<ftrows.size();i++) {
				List<WebElement> ftcols=ftrows.get(i).findElements(By.tagName("td"));
				String aname=ftcols.get(0).getText();
				String select=ftcols.get(8).getText();
				if(aname.equals(airlineName) && select.equalsIgnoreCase("Select")) {
					ftcols.get(8).findElement(By.tagName("button")).click();
					System.out.println(aname);
					break;
				}
			}
			
		} catch (Exception e) {
			String nflight=driver.findElement(By.className("no_flights")).getText();
			System.out.println(nflight);
		}
		
		
		
	}
	
	
	@Parameters({"fclassname"})
	@Test(priority=3)
	public void selectFlightClass(String fclassname) throws InterruptedException, IOException {
		Thread.sleep(2000);
		
		String fclass=driver.findElement(By.xpath("//input[@type='radio' and @value='First Class']")).getAttribute("value");
		String bclass= driver.findElement(By.xpath("//input[@type='radio' and @value='Business']")).getAttribute("value");
		
		if(fclassname.equals(fclass)) {
			driver.findElement(By.xpath("//input[@type='radio' and @value='First Class']")).click();
		}
		else if(fclassname.equals(bclass)) {
			driver.findElement(By.xpath("//input[@type='radio' and @value='Business']")).click();
		}
		else {
			driver.findElement(By.xpath("//input[@type='radio' and @value='Economy']")).click();
		}
		
		
	}
	
	
	
	@Parameters({"MyName"})
	@Test(priority=4)
	public void enterName(String MyName) throws InterruptedException, IOException {
		driver.findElement(By.id("name")).sendKeys(MyName);
		Thread.sleep(2000);
	}
	
	
	
	@Parameters({"Tickets"})
	@Test(priority=5)
	public void numberOfTickets(String Tickets) throws InterruptedException, IOException {
		driver.findElement(By.id("tickets")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("tickets")).sendKeys(Tickets);
		
	}
	
	
	@Test(priority=6)
	public void clickInsertOrder() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//*[@id=\"flight_reservation_form\"]/div/div[4]/div/button")).click();
		Thread.sleep(2000);
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File srcpic=screenshot.getScreenshotAs(OutputType.FILE);
		File trgpic= new File("D:\\QEDGE TECHNOLOGIES\\AUTOMATION TESTING\\FlightBookingApp-TestNG\\test-output\\scpic2.png");
		FileUtils.copyFile(srcpic, trgpic);
		
	}
	
	
}

