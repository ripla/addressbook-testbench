package com.vaadin.tutorial.addressbook;

import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchTestCase;

public class EnterSvenCoded extends TestBenchTestCase {

	@Before
	public void setUp() {
		driver = TestBench.createDriver(new ChromeDriver());
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void enterSven() throws InterruptedException {
		driver.get("http://localhost:8080/?restartApplication");
		
		// Click the "new" button
		driver.findElement(By.id("new-contact")).click();
		
		// Enter the name
		WebElement firstName = driver.findElement(By.id("First-Name"));
		WebElement lastName = driver.findElement(By.id("Last-Name"));
		WebElement company = driver.findElement(By.id("Company"));
		// Clear first as the fields contain text
		firstName.clear();
		firstName.sendKeys("Sven");
		lastName.clear();
		lastName.sendKeys("Svensson");
		company.sendKeys("Vaadin Ltd");
		
		// Search for sven
		driver.findElement(By.id("search-field")).sendKeys("sven");
		
		// Wait for the text change event
		Thread.sleep(500);
		
		// Assert Sven
		WebElement firstNameCell = driver.findElement(By.xpath("id('contact-list')//td[@class='v-table-cell-content'][1]"));
		WebElement lastNameCell = driver.findElement(By.xpath("id('contact-list')//td[@class='v-table-cell-content'][2]"));
		WebElement companyCell = driver.findElement(By.xpath("id('contact-list')//td[@class='v-table-cell-content'][3]"));
		Assert.assertEquals("Sven", firstNameCell.getText());
		Assert.assertEquals("Svensson", lastNameCell.getText());
		Assert.assertEquals("Vaadin Ltd", companyCell.getText());
	}
	
}
