package pageObjects;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import freemarker.log.Logger;

public class BookShelves extends BasePage {
	

	public BookShelves(WebDriver driver) {
		
		super(driver);	
	}
	
	//ELEMENTS
	@FindBy(xpath="//*[@id='authentication_popup']/div/div/div[2]")
	WebElement popUp;
	
	@FindBy(xpath="//*[@id='authentication_popup']/div/div/div[2]/a[1]")
	WebElement close;
	
	@FindBy(xpath="//*[@id=\"filters-form\"]/div[1]/div/div/ul/li[1]/div[1]") 
	WebElement category;
	
	@FindBy(xpath="//*[@id=\"filters-form\"]/div[1]/div/div/ul/li[1]/div[2]/div/div/div/ul/li/input") 
	List<WebElement> categoryOptions;
	
	@FindBy(xpath="//*[@id=\"filters-form\"]/div[1]/div/div/ul/li[2]/div[1]") 
	WebElement price;
	
	@FindBy(xpath="//*[@id=\"filters-form\"]/div[1]/div/div/ul/li[2]/div[2]/div/div/ul/li[1]/div/div[2]/div[2]/div/div[2]/div")
	WebElement maxPricebtn;
	
	
	@FindBy(xpath="//*[@id=\"search-results\"]/div[2]/div[1]/div/div/div/div/div[2]/div[1]/span") 
	WebElement sortBy;
	
	@FindBy(xpath="//*[@id=\"search-results\"]/div[2]/div[1]/div/div/div/div/div[2]/div[2]/div/div/ul/li") 
	List<WebElement> sortByOptions;
	
	
	@FindBy(xpath="//*[@id=\"search-results\"]/div[3]/ul/li/div/div[5]/a/div[1]/span")
	List<WebElement> productTitle;
	
	@FindBy(xpath="//*[@id=\"search-results\"]/div[3]/ul/li/div/div[5]/a/div[2]/span")
	List<WebElement> productPrice;
	

	@FindBy(xpath="//input[@id='filters_availability_In_Stock_Only']")
	WebElement excludeOutofStock;
	
	//ACTIONS
	public void closePopup() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOfAllElements(popUp));
		close.click();
		
	}
	public void select_category(String c) throws InterruptedException {
		category.click();
		
	
		for(WebElement i : categoryOptions) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
			wait.until(ExpectedConditions.visibilityOfAllElements(i));
			
			if(i.getAttribute("value").equals(c)){
				i.click();
				break;
			}
		}
	}
	
	public void select_price_range() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOfAllElements(price));
		Thread.sleep(3000);
		price.click();
		Actions act = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfAllElements(maxPricebtn));
		act.dragAndDropBy(maxPricebtn, -211, 0).perform();
		
	}
	
	public void select_exclude_outofstock() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", excludeOutofStock);
		
	}
	
	public void select_sortby_as_high_to_low() throws InterruptedException {
			sortBy.click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
			wait.until(ExpectedConditions.visibilityOfAllElements(sortByOptions.get(2)));
			Thread.sleep(3000);
			sortByOptions.get(2).click();	
		}
		
		
	
	public Map<String, Integer> get_first_three_products() throws InterruptedException {
		Map<String,Integer> m = new LinkedHashMap<String,Integer>();
		for(int i=0; i<3 ;i++) {
			try {
			Thread.sleep(3000);
			WebElement curr_ele = productTitle.get(i);	
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
			wait.until(ExpectedConditions.visibilityOfAllElements(curr_ele));
			String pd = productTitle.get(i).getText();
			String pr = productPrice.get(i).getText();
			int finalPrice = Integer.parseInt(pr.replaceAll("[,₹]", ""));
			
			m.put(pd, finalPrice);				
			}catch(org.openqa.selenium.StaleElementReferenceException e) {
				e.getMessage();
			}
			
		}
		return m;
	
	}

}

