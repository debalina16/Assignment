package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CarouselHandling_AssignmentNo2 {
	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		driver = new ChromeDriver();
		String baseUrl = "https://www.noon.com/uae-en/";
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<String> labelList =getCarouselLabels();
		for(String label:labelList) {
			scrollCarouselAndSortData(label); 
		  }
	   System.out.println("\t Testcase Successfully");
       driver.quit();
	}
	
	public static List<String> getCarouselLabels() throws InterruptedException {
	  List<String> labelList = new ArrayList<>();
	  ((JavascriptExecutor) driver).
	        executeScript("window.scrollBy(0,2000)");
	  Thread.sleep(5000);
	  //get label names
	  List<WebElement> labels=driver.findElements(By.xpath("//*[@class='sc-gsTCUz bhdLno']/h3"));
	  System.out.println("Labels count is "+labels.size());
	  for(WebElement label:labels) {
		  labelList.add(label.getText());
		  System.out.println("Carousel Label is "+label.getText());	  
	  }
	  return labelList;  
	}
	
	public static List<String> scrollCarouselAndSortData(String label) {
		List<String> productList = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String carouselLeftBtn_xpath = "//*[text()='" + label + "']/../.."
				+ "/following-sibling::div//*[contains(@class,'swiper-button-next')]";

		String carouselLeftDisabledBtn_xpath = "//*[text()='" + label + "']/../.."
				+ "/following-sibling::div//*[contains(@class,'swiper-button-next') "
				+ "and not(contains(@class,'swiper-button-disabled'))]";

		String products_xpath = "//*[text()='" + label + "']/../.."
				+ "/following-sibling::div//*[@data-qa='product-name']";

		System.out.println("Current label is "+label);
		WebElement Element = driver.findElement(By.xpath("//*[text()='" + label + "']"));
		js.executeScript("arguments[0].scrollIntoView();", Element);

		while (driver.findElements(By.xpath(carouselLeftDisabledBtn_xpath)).size() > 0) {
		//	driver.findElement(By.xpath(carouselLeftBtn_xpath)).click();
			WebElement element = driver.findElement(By.xpath(carouselLeftBtn_xpath));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		}
		List<WebElement> products = driver.findElements(By.xpath(products_xpath));
		for (WebElement product : products) {
			productList.add(product.getAttribute("innerText"));
		}
		System.out.println("Total Product count is "+productList.size());
		//sort all product
		Collections.sort(productList);
		//print all sorted product 
		System.out.println("Print List after sorting of " + label + " label is ");
		for (String product : productList) {
			System.out.println(product);
		}
		System.out.println("=====================================================");
		return productList;
	}
	

}
