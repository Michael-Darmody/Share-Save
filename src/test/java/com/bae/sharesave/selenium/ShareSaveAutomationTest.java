package com.bae.sharesave.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShareSaveAutomationTest {

	private RemoteWebDriver driver;

	@BeforeEach
	void setup() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}

	@Test
	void testShareSaveRead() {
		this.driver.get("http://127.0.0.1:5500/ShareSave/index.html");

		WebElement cardTitle = this.driver
				.findElement(By.xpath("//*[@id=\"output\"]/section[1]/section/section[1]/h5"));

		Assertions.assertTrue(cardTitle.getText().contains("Barclays"));
	}

	@AfterEach
	void tearDown() {
		this.driver.close();
	}
}
