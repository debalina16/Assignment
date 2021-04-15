package Assignment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AssignmentNo1_15042021 {
	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		int count=1;
		driver = new ChromeDriver();
		String baseUrl = "https://www.worldometers.info/world-population/";
		String currPopulation_xpath="//*[@class='maincounter-number']/span[@class='rts-counter']";
		String todayAndThisYear_xpath="//*[text()='Today' or text()='This year']"
				                   + "/..//*[@class='sec-counter']";
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		while (count<=20) {
			if(count==21)break;
			System.out.println("Count: "+count+" sec");
			System.out.println("===============Current World Population================");
			printPopulation(currPopulation_xpath);
			System.out.println("===============Today and This Year Population================");
			printPopulation(todayAndThisYear_xpath);
			Thread.sleep(1000);
			count++;
			System.out.println("=============================================");
		}
		driver.close();
	}

	public static void printPopulation(String locator) {
		List<WebElement>popCnt = driver.findElements(By.xpath(locator));
		for(WebElement e:popCnt) {
			System.out.println(e.getText());
		}
		
	}
}
