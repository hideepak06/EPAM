package com.test.step.defintions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.selenium.configure.environment.PropertiesHandler;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

/**
 * This class contains methods to allow you input into a field. 
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class InputSteps {
	WebDriver driver;
	
	/******** Log Attribute ********/
    private static Logger log = Logger.getLogger(InputSteps.class);
	
	public InputSteps(){
		 driver= Hooks.driver;
	}
	@Given("^flights with fastest and chepest$")
	public void flights_with_fastest_and_chepest() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String fastflight=FastestFlight();
		List<WebElement> templist = null;
		List<WebElement> tdList = driver.findElements(By.xpath("//div[contains(@id,'fli_list_item')]//div[1]/div[1]/div/div/div/div[2]/div/div/div/label/div[2]/p[1]"));
		//int flighttime=filters[0];
		 String strArray[] = new String[tdList.size()];
			for(int i =0;i<tdList.size();i++)
			{
				if(tdList.get(i).getText()==fastflight)
				{
					templist.add(tdList.get(i));
					log.info("After Sorting the fastest and chepest fligt is"+templist.get(i));
				}
			}
	}
	
    
	/** Enter a text into an input field element. */
	@When("^I enter \"([^\"]*)\" into input field having (.+) \"([^\"]*)\"$")
	public void inputText(String text, String type,String key) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		WebElement input= driver.findElement(element);
		input.click();
		//input.clear();
		log.info("Sending text: "+text+"into element"+element);
		input.sendKeys(text);
		input.sendKeys(Keys.RETURN);
	}
	
	/** Enter a text into an input field element slowly */
	@When("^I slowly enter \"([^\"]*)\" into input field having (.+) \"([^\"]*)\"$")
	public void slowSendKeys(String text, String type,String key) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		WebElement input = driver.findElement(element);
		input.click();
		for (char c : text.toCharArray()) {
			ProgressSteps.wait(5);
			input.sendKeys(""+c);
		}
	}

	/** Clear input field element. */
	@When("^I clear input field having (.+) \"([^\"]*)\"$") 
	public void clearText(String type, String key) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		WebElement input= driver.findElement(element);
		input.click();
		input.clear();
		log.info("Element: "+element + "clear");
	
	}

	/** Select an option by text/value from a drop-down */
	@When("^I select \"(.*?)\" option by (.+) from dropdown having (.+) \"(.*?)\"$")
	public void selectOptionDropdown(String option,String optionBy,String type,String key) throws Exception
	{
		By dropdown = PropertiesHandler.getCompleteElement(type, key);		
		Select opt = new Select(driver.findElement(dropdown));
		
		if(optionBy.equals("text")){
			log.info("Select option: " + option + "by text");		
			opt.selectByVisibleText(option);				
		}
		else if(optionBy.equals("value")){
			log.info("Select option: " + option + "by value");			
			opt.selectByValue(option);				
			
		}					
	}
	
	/** Select an option by index from a drop-down */
	@When("^I select option (\\d+) by index from dropdown having (.+) \"(.*?)\"$")
	public void selectOptionDropdownByIndex(int option, String type, String key) throws Exception
	{
		By dropdown = PropertiesHandler.getCompleteElement(type, key);		
		Select opt = new Select(driver.findElement(dropdown));
		log.info("Select option: " + option + "by text");		
		opt.selectByIndex(option);				
	}
	
	/** Clear all options selected from a drop-down */
	@When("^I clear all options selected from dropdown having (.+) \"(.*?)\"$")
	public void deselectAllOptionDropdown(String type, String key) throws Exception
	{
		By dropdown = PropertiesHandler.getCompleteElement(type, key);		
		Select opt = new Select(driver.findElement(dropdown));
		log.info("Clear all options selected from drop-down");		
		opt.deselectAll();			
	}

	
	/** Unselect an option by text/value from a drop-down */
	@When("^I unselect \"(.*?)\" option by (.+) from dropdown having (.+) \"(.*?)\"$")
	public void unselectOptionMultiDropdown(String option,String optionBy, String type,String key) throws Exception
	{
		By dropdown = PropertiesHandler.getCompleteElement(type, key);		
		Select opt = new Select(driver.findElement(dropdown));
		
		if(optionBy.equals("text")){
			log.info("Unselect option: " + option + "by text");		
			opt.deselectByVisibleText(option);				
		}
		else if(optionBy.equals("value")){
			log.info("Unselect option: " + option + "by value");			
			opt.deselectByValue(option);				
		}		
	}
		
	/** Unselect an option by index from a drop-down */
	@When("^I unselect (\\d+) option by index from dropdown having (.+) \"(.*?)\"$")
	public void unselectOptionMultiDropdownByIndex(int option, String type, String key) throws Exception
	{
		By dropdown = PropertiesHandler.getCompleteElement(type, key);		
		Select opt = new Select(driver.findElement(dropdown));
		log.info("Select option: " + option + "by text");		
		opt.selectByIndex(option);	
	}

	/** Check an option from a checkbox */
	@When("^I check the checkbox having (.+) \"(.*?)\"$") 
	public void checkCheckbox(String type, String key) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);	
		boolean isChecked = driver.findElement(element).isSelected();
		if(!isChecked){
			log.info("Clicking on the checkbox to select:" +element);
			driver.findElement(element).click();
		}
	}

	/** Uncheck an option from a checkbox */
	@When("^I uncheck the checkbox having (.+) \"(.*?)\"$")
	public void uncheckCheckbox(String type,String key) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);	
		boolean isChecked = driver.findElement(element).isSelected();
		if(isChecked){
			log.info("Clicking on the checkbox to uncheck:" +element);
			driver.findElement(element).click();
		}
	}

	/** Select a radio button */
	@When("^I select radio button having (.+) \"(.*?)\"$")
	public void selectRadioButton(String type, String key) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);	
		boolean isSelected = driver.findElement(element).isSelected();
		if(!isSelected){
			log.info("Clicking on the radio button to select:" +element);
			driver.findElement(element).click();
		}

	}
	String temp=null;
	 String FastestFlight()
	{
	 List<WebElement> tdList = driver.findElements(By.xpath("//div[contains(@id,'fli_list_item')]//div[1]/div[1]/div/div/div/div[2]/div/div/div/label/div[2]/p[1]"));
	//int flighttime=filters[0];
	 String strArray[] = new String[tdList.size()];
		for(int i =0;i<tdList.size();i++)
		{
			System.out.println(tdList.get(i).getText());
			strArray[i]=tdList.get(i).getText();
		}
		
		for (int i = 0; i < strArray.length; i++) 
		{
			for (int j = i + 1; j < strArray.length; j++) 
			{
				if (strArray[i].compareTo(strArray[j])>0) 
				{
					temp = strArray[i];
					strArray[i] = strArray[j];
					strArray[j] = temp;
				}
			}
		}
	return strArray[0];
	}

	String cheapestFlight()
	{
		List<WebElement> tdList = driver.findElements(By.xpath("//div[contains(@id,'fli_list_item')]//div[1]/div[1]/div/div/div/div[2]/div/div/div/label/div[2]/p[1]"));

			 String strArray[] = new String[tdList.size()];
		for(int i =0;i<tdList.size();i++)
		{
			System.out.println(tdList.get(i).getText());
			strArray[i]=tdList.get(i).getText();
		}
		
		for (int i = 0; i < strArray.length; i++) 
		{
			for (int j = i + 1; j < strArray.length; j++) 
			{
				if (strArray[i].compareTo(strArray[j])>0) 
				{
					temp = strArray[i];
					strArray[i] = strArray[j];
					strArray[j] = temp;
				}
			}
		}
	return strArray[0];

	//return flighttime;
	}


}
