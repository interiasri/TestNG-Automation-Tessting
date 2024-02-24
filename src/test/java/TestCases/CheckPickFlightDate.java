package TestCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AppUtils.SpecialFunctions;

public class CheckPickFlightDate extends SpecialFunctions{
	
	
	
	@Parameters({"m","y","d"})
	@Test
	public static void CheckPickDate(String m, String y, String d) throws IOException {
		driver.findElement(By.id("search-date")).click();
		String calmonth=driver.findElement(By.className("ui-datepicker-month")).getText();
		String calyear=driver.findElement(By.className("ui-datepicker-year")).getText();
		while(!calyear.equals(y)) {
			driver.findElement(By.linkText("Next")).click();
			calyear=driver.findElement(By.className("ui-datepicker-year")).getText();	
		}
		
		System.out.println(calyear);
		
		while(!calmonth.equals(m)) {
			driver.findElement(By.linkText("Next")).click();
			calmonth=driver.findElement(By.className("ui-datepicker-month")).getText();	
		}
		System.out.println(calmonth);
		
		List<WebElement> trows=driver.findElement(By.className("ui-datepicker-calendar")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(int i=0;i<trows.size(); i++) {
			List<WebElement> tcols=trows.get(i).findElements(By.tagName("td"));
			for(int j=0; j<tcols.size();j++) {
				if(tcols.get(j).getText().equals(d)) {
					tcols.get(j).click();
					break;
				}
				
			}
			
		}
		
	}

}
