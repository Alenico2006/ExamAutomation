package com.quality.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class EbayShoes {
	
	private WebDriver driver;
	By searchArticle = By.id("gh-ac");
	By searchButton = By.id("gh-btn");
	By searchBrand = By.id("w4-w12-0[0]");
	By searchresults = By.xpath("//*[@id='mainContent']/div[1]/div/div[2]/div/div[1]/h1/span[1]") ;
	By searchproduct = By.xpath("//*[@id='srp-river-results-listing1']/div/div[2]/a/h3");
	
	
			
			
	@Before
	public void setUp() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String Url = "https://www.ebay.com/";
		driver.get(Url);
	}

	@After
	public void tearDown() throws Exception {
		
		driver.close();
	}

	@Test
	public void test() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.findElement(searchArticle).sendKeys("shoes");
		driver.findElement(searchButton).click();
		
		Thread.sleep(2000);
		
		driver.findElement(searchBrand).sendKeys("PUMA");
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("(//input[@type='checkbox'])[16]")).click();
		
		Thread.sleep(2000);
				
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("svg-icon-chevron-down")));
        
        driver.findElement(By.xpath("(//input[@type='checkbox'])[6]")).click();
        
        Thread.sleep(2000);
     
		String results = driver.findElement(searchresults).getText();
		System.out.print("Number of results is  ---> " + results + '\n');
		
		WebElement sortprice =driver.findElement(By.xpath("//*[@id=\"w9\"]/button/div/div"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(sortprice).build().perform();
		
		WebElement minorprice =driver.findElement(By.xpath("//*[@id=\"w9\"]/div/div/ul/li[4]/a/span"));
		
		Actions actions1 = new Actions(driver);
		actions1.moveToElement(minorprice).click().build().perform();
		
		for (int i=1 ; i < 6 ; i++)
		{
						
			By searchproduct = By.xpath("//li[@id=\"srp-river-results-listing"+i+"\"]/div/div[2]/a/h3");
			
			By priceproduct = By.xpath("//li[@id=\"srp-river-results-listing"+i+"\"]/div/div[2]/div[3]/div[1]/span");
			
			String producto = driver.findElement(searchproduct).getText();
			
			String price = driver.findElement(priceproduct).getText();
			
			System.out.print(producto + "-->" + price + '\n');
			
		}
	
	
	}
}
