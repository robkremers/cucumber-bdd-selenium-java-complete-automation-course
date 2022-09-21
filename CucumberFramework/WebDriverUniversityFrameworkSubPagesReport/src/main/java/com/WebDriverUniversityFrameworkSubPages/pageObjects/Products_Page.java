package com.WebDriverUniversityFrameworkSubPages.pageObjects;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products_Page extends BasePage {
	/**
	 * '//' means: look anywhere in the DOM.
	 * './/*[@id='myModal']' means: look anywhere below the current position.
	 * './/*[@id='myModal']//b[contains(text(),'NEWCUSTOMER')]' means: 
	 *                          - look anywhere below the current position
	 *                          - find an element with id = "myModal"
	 *                          - look anywhere below for a bold element
	 *                          - look for an element with text 'NEWCUSTOMER'
	 */
	public @FindBy(css = "#container-special-offers") WebElement button_SpecialOffers;
	public @FindBy(xpath = ".//*[@id='container-product2']") WebElement button_NewLaptops;
	public @FindBy(xpath = ".//*[@id='myModal']//b[contains(text(),'NEWCUSTOMER')]") WebElement voucherCode;
	public @FindBy(xpath = "//button[text()='Proceed']") WebElement button_Proceed_Popup;

	public Products_Page() throws IOException {
		super();
	}
	
	public Products_Page clickOnProceedButton_Popup() throws IOException, InterruptedException {
		waitAndClickElement(button_Proceed_Popup);
		return new Products_Page();
	}

	public String printSpecialOffersVoucherCode() throws InterruptedException {
		WaitUntilWebElementIsVisible(voucherCode);
		String voucherCodeValue = voucherCode.getText();
		System.out.println("Voucher Code: " + voucherCode.getText());
		return voucherCodeValue;
	}
}
