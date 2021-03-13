package com.bae.sharesave.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Sql(scripts = { "classpath:share-schema.sql",
		"classpath:share-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ShareSaveAutomationTest {

	private RemoteWebDriver driver;

	@BeforeEach
	void setup() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}

	@Test
	void testShareSaveRead() throws InterruptedException {
		this.driver.get("http://127.0.0.1:5500/ShareSave/index.html");

		Thread.sleep(2000);

		WebElement cardTitle = this.driver.findElement(By.xpath("//*[@id=\"output\"]/section/section/section[1]/h5"));

		Assertions.assertTrue(cardTitle.getText().contains("Barclays"));
	}

	@Test
	void testShareSaveCreate() throws InterruptedException {
		this.driver.get("http://127.0.0.1:5500/ShareSave/index.html");

		WebElement shareName = this.driver.findElement(By.xpath("//*[@id=\"shareName\"]"));

		shareName.sendKeys("Vodafone");

		WebElement shareAmount = this.driver.findElement(By.xpath("//*[@id=\"shareAmount\"]"));

		shareAmount.sendKeys("2000");

		WebElement sharePrice = this.driver.findElement(By.xpath("//*[@id=\"sharePrice\"]"));

		sharePrice.sendKeys("2");

		WebElement submitBtn = this.driver.findElement(By.xpath("//*[@id=\"shareForm\"]/button[2]"));

		submitBtn.click();

		WebDriverWait wait = new WebDriverWait(driver, 30);

		WebElement newShareCard = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"output\"]/section[2]/section/section[1]/h5")));

		Assertions.assertTrue(newShareCard.getText().contains("Vodafone"));

	}
//
//	@AfterEach
//	void tearDown() {
//		this.driver.close();
//	}
}
