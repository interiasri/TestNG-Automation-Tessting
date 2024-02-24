package TestCases;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AppUtils.SpecialFunctions;

public class CheckPreviousBookingOrders extends SpecialFunctions{
	
	@Test(priority=1)
	public void isOrdersModuleMessageDisplayed() {
		driver.findElement(By.linkText("Flight Bookings")).click();
		boolean porders=driver.findElement(By.xpath("//*[@id=\"contact-info\"]/div/div[1]/h2")).isDisplayed();
		Assert.assertTrue(porders);
	}
	
	
	@Test(priority=2)
	public void getOrderList() {
		List<WebElement> rows=driver.findElement(By.className("flights_table")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(int i=1; i<rows.size();i++) {
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			System.out.println(cols.get(0).getText());
		}
	}
	
	@Parameters({"OrderID"})
	@Test(priority=3)
	public void deleteUnwantedOrder(String OrderID) throws InterruptedException, IOException {
		List<WebElement> trows=driver.findElement(By.className("flights_table")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(int j=1; j<trows.size();j++) {
			List<WebElement> tcols=trows.get(j).findElements(By.tagName("td"));
			String orderId=tcols.get(0).getText();
			List<WebElement> links=tcols.get(9).findElements(By.tagName("a"));
			String del=links.get(2).getText();
			if(orderId.equals(OrderID) && del.equalsIgnoreCase("Delete")) {
				links.get(2).click();
				driver.switchTo().alert().accept();
				break;
			}
		}
		
		driver.navigate().refresh();
		Thread.sleep(2000);
		TakesScreenshot sc = (TakesScreenshot)driver;
		File src=sc.getScreenshotAs(OutputType.FILE);
		File trg = new File("D:\\QEDGE TECHNOLOGIES\\AUTOMATION TESTING\\FlightBookingApp-TestNG\\test-output\\refresh.png");
		FileUtils.copyFile(src, trg);
		Thread.sleep(2000);
	}
	
	
	@Parameters({"vieworderID"})
	@Test(priority=4)
	public void viewOrderIdDetails(String vieworderID) throws InterruptedException, IOException {
		driver.findElement(By.linkText("Flight Bookings")).click();
		List<WebElement> trows=driver.findElement(By.className("flights_table")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(int j=1; j<trows.size();j++) {
			List<WebElement> tcols=trows.get(j).findElements(By.tagName("td"));
			String orderId=tcols.get(0).getText();
			List<WebElement> links=tcols.get(9).findElements(By.tagName("a"));
			String view=links.get(0).getText();
			if(orderId.equals(vieworderID) && view.equalsIgnoreCase("View")) {
				links.get(0).click();
				break;
			}
		}
		
		driver.navigate().refresh();
		Thread.sleep(2000);
		TakesScreenshot sc = (TakesScreenshot)driver;
		File src=sc.getScreenshotAs(OutputType.FILE);
		File trg = new File("D:\\QEDGE TECHNOLOGIES\\AUTOMATION TESTING\\FlightBookingApp-TestNG\\test-output\\refresh1.png");
		FileUtils.copyFile(src, trg);
		Thread.sleep(2000);
	}
	
	
	
}
