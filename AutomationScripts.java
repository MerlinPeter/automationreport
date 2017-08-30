package automationreport;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AutomationScripts extends ReusableMethods {
	private static WebDriver driver;
	
	public static void SFDCLogin(int row,int end) throws Exception {
		
          /* Launch a Browser*/
		 System.setProperty("webdriver.gecko.driver", "/Users/peter/Downloads/Seleniumtestcases/geckodriver");
		 driver = new FirefoxDriver();
		 /*  Launch URL*/
		    driver.get("https://login.salesforce.com");
		    //driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS); 
		 
			startReport("Decending","/Users/Peter/TestReport/");

		 /* WebElement userName1 = driver.findElement(By.xpath(".//*[@id='username']"));
		    enterText(userName1, "merlintech10@gmail.com ","UsernameTextbox");
		    
		    WebElement password = driver.findElement(By.xpath(".//*[@id='password']"));
		    enterText(password, "jakejoel18015 ","PasswordTextbox");
		    
		    
		    WebElement loginButton = driver.findElement(By.xpath(".//*[@id='Login']"));
		    clickButton(driver,loginButton, "Log In ");*/
			
	 		String dt_path = "/Users/peter/Downloads/logino3.xlsx";

			 String[][]  recData = readXLSheet(dt_path, "Sheet1");
			
				 
		
			//   By  locator = null;
			    WebElement wl = null;

	        for(int i=row;i<=end;i++){  
	  
	            
	            if (recData[i][1].toString().equals("xpath") ){
 	    		      wl =  driver.findElement(By.xpath(recData[i][2].toString()));


	            }else  if (recData[i][1].equals("name")){
	    		    wl = driver.findElement(By.name(recData[i][2].toString()));
	            }
	            if (recData[i][3].toString().equals("sendKeys")){
 	    		    enterText(wl,  recData[i][4].toString(), recData[i][4].toString());

	            }else  if (recData[i][3].toString().equals("click")){
 	            	 clickButton(driver,wl, "Log In ");

	            }else  if (recData[i][3].toString().equals("clickAndWait")){
	            	 clickWaitButton(driver,wl, "Logout ");
	            	 
	            }
	        }
			  
		    bw.close();
    driver.quit();
	}
//Assert.assertEquals(actual_msg, expect);
	
	public static void xeroLogin(int row,int end) throws Exception {
		
        /* Launch a Browser*/
		 System.setProperty("webdriver.gecko.driver", "/Users/peter/Downloads/Seleniumtestcases/geckodriver");
		 driver = new FirefoxDriver();
		 /*  Launch URL*/
		 //   driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS); 
		 
			startReport("Decending","/Users/Peter/TestReport/");
			
	 		String dt_path = "/Users/peter/Downloads/logino3.xlsx";

			 String[][]  recData = readXLSheet(dt_path, "Sheet1");

				 
		
			//   By  locator = null;
			    WebElement wl = null;
			    try{
			    driver.get(recData[row - 1][4].toString());
				Update_Report("Pass "  +"  ",recData[row - 1][4].toString()," able to open website");

			    }catch (Exception e){
					Update_Report("Fail "  +"  ",recData[row - 1][4].toString()," not able to open website");

			    }

	        for(int i=row;i<=end;i++){  

	            if (recData[i][1].toString().equals("xpath") ){
	            	
	                try{
	                	 wl =  driver.findElement(By.xpath(recData[i][2].toString()));
	                	 
	                	 Update_Report("Pass "  +"  ",recData[i][2].toString()," able to find  element");

	    			    }catch (Exception e){
		                	 Update_Report("Fail "  +"  ",recData[i][2].toString()," not able to find  element, terminating the test");
                            e.printStackTrace();
                            break;
	    			    }  


	            }else  if (recData[i][1].equals("name")){
	    		    wl = driver.findElement(By.name(recData[i][2].toString()));
	            }else  if (recData[i][1].equals("linkText")){
	    		    wl = driver.findElement(By.xpath(recData[i][2].toString()));
	            }
	            if (recData[i][3].toString().equals("sendKeys")){
	    		    enterText(wl,  recData[i][4].toString(), recData[i][4].toString());

	            }else  if (recData[i][3].toString().equals("click")){
	            	try{
	            	 clickButton(driver,wl, "Click ");
	            	 }catch (Exception e){
	                	 Update_Report("Fail "  +"  ",recData[i][2].toString()," not able to find  element, terminating the test");
                        e.printStackTrace();
                        break;
    			    }  

	            }else  if (recData[i][3].toString().equals("clickAndWait")){
	            	 clickWaitButton(driver,wl, "Logout ");
	            	 
	            }
	        }
			 
			 
		    bw.close();
        driver.quit();
	}
	
	public  static void validateErrorMessage(){
		
	}
	
}

