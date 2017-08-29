package automationreport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.WebElementHandler;
import org.openqa.selenium.support.ui.Select;

public class AutomationScripts extends ReusableMethods {
	private static WebDriver driver;
	
	

	public static void SFDCLogin() throws Exception {
		
          /* Launch a Browser*/
		 System.setProperty("webdriver.gecko.driver", "/Users/peter/Downloads/Seleniumtestcases/geckodriver");
		 driver = new FirefoxDriver();
		    
		  /*  Launch URL*/
		 driver.get("https://login.salesforce.com");
			startReport("Decending","/Users/Peter/TestReport/");

		 /* WebElement userName1 = driver.findElement(By.xpath(".//*[@id='username']"));
		    enterText(userName1, "merlintech10@gmail.com ","UsernameTextbox");
		    
		    WebElement password = driver.findElement(By.xpath(".//*[@id='password']"));
		    enterText(password, "jakejoel18015 ","PasswordTextbox");
		    
		    
		    WebElement loginButton = driver.findElement(By.xpath(".//*[@id='Login']"));
		    clickButton(driver,loginButton, "Log In ");*/
			
	 		String dt_path = "/Users/peter/Downloads/logino3.xlsx";

			 String[][]  recData = readXLSheet(dt_path, "Sheet1");
	 
			   By  locator = null;
			    WebElement wl = null;

	        for(int i=2;i<=4;i++){  
	  
	            
	            if (recData[i][1].toString().equals("xpath") ){
	    		  //  locator = By.xpath(recData[i][2].toString());
	    		      wl =  driver.findElement(By.xpath(recData[i][2].toString()));


	            }else  if (recData[i][1].equals("name")){
	    		    wl = driver.findElement(By.name(recData[i][2].toString()));
	            }
	            
	            if (recData[i][3].toString().equals("sendKeys")){
 	            	//driver.findElement(locator).sendKeys(recData[i][4].toString());
	    		    enterText(wl,  recData[i][3].toString(), recData[i][4].toString());

	            }else  if (recData[i][3].toString().equals("click")){
	            //	driver.findElement(locator).click();
	            	 clickButton(driver,wl, "Log In ");

	            }
	            
	        }
		    
		    bw.close();

	}

	
	
	public  static void validateErrorMessage(){
		
	}
	
}

