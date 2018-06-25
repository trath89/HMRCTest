package com.hmrc.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UtilityClass {

	public static void clickEvent(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}


	public static void addText(WebDriver driver, By by, String text ) {
		wait(driver, by);
		WebElement element = driver.findElement(by);
		element.sendKeys(text);

	}


	public static void wait(WebDriver driver,By by)
	{
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		System.out.println("Wait Method: waited for element to be present" + by.toString());
	}

}
