package automationreport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AutomationScripts extends ReusableMethods {
 	
	public static boolean SFDCLogin(int row,int end) throws Exception {
		
          /* Launch a Browser*/
	 	 
		 /*  Launch URL*/
		    driver.get("https://login.salesforce.com");
		    //driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS); 
		 
 
	 		String dt_path = "/Users/peter/Downloads/logino3.xlsx";

			 String[][]  recData = readXLSheet(dt_path, "Sheet1");
		
			    WebElement wl = null;

	         for(int i=row;i<=end;i++){  
	
	          if (recData[i][1].equals("xpath") ){
 	    		      wl =  driver.findElement(By.xpath(recData[i][2]));
  	    		     int timeCount = 1;

  	            }else  if (recData[i][1].equals("name")){
	    		    wl = driver.findElement(By.name(recData[i][2]));
	            }else  if (recData[i][1].equals("linkText")){
	    
	    		    wl = driver.findElement(By.linkText(recData[i][2]));
	            }else  if (recData[i][1].equals("id")){
	            	try{
	    		    wl = driver.findElement(By.id(recData[i][2]));
	            	}
	            	catch(Exception e){
	            		e.printStackTrace();
	     				Update_Report("Fail"+"  ", recData[i][2],"testcase failed because of successful login.", driver );
                          break;
	            	}
	            }
	            
	            
		          /* deals with first   3rd  and 4th col to execute  wl */  
	            if (recData[i][3].equals("sendKeys")){
 	    		    enterText(wl,  recData[i][4], recData[i][4]);

	            } if (recData[i][3].equals("text")){
	            	 checkError(wl,  recData[i][4]);
	            	 
	            }else  if (recData[i][3].equals("click")){
	            	
  	            	try{
 		            	 clickButton(wl, "Click ",recData[i][4]);
 		     			Update_Report("Pass"+"  ", recData[i][2],"   click success", driver );

 		            	 }catch (Exception e){
 		                	 Update_Report("Fail "  +"  ",recData[i][2]," not able to find  element, terminating the test", driver);
 	                        e.printStackTrace();
 	                        break;
 	    			    }  

	            }

	        }
			  
		    bw.close();
		    Thread.sleep(5000);
		   // driver.close();
   // driver.quit();
			return true;
	}
//Assert.assertEquals(actual_msg, expect);
	
	public static void xeroLogin() throws Exception {
		int row =33;int end=35;
        /* Launch a Browser*/
		 System.setProperty("webdriver.gecko.driver", "/Users/peter/Downloads/Seleniumtestcases/geckodriver");
		 driver = new FirefoxDriver();
		 /*  Launch URL*/
		    driver.get("https://login.xero.com/");
		 //   driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS); 
		 
			startReport("Merlin","/Users/Peter/ReportLogs/");
			
	 		String dt_path = "/Users/peter/Downloads/logino3.xlsx";

			 String[][]  recData = readXLSheet(dt_path, "Sheet1");

				 
		
			//   By  locator = null;
			    WebElement wl = null;
			    try{
			    driver.get(recData[2 - 1][4].toString());
			    System.out.print("===================");
			    System.out.print(recData[2 - 1][4].toString());
				Update_Report("Pass "  +"  ",recData[2 - 1][4].toString()," able to open website", driver);

			    }catch (Exception e){
           e.printStackTrace();
           Update_Report("Fail "  +"  ",recData[2 - 1][4].toString()," not able to open website", driver);

			    }

	        for(int i=row;i<=end;i++){  

	            if (recData[i][1].toString().equals("xpath") ){
	            	
	                try{
	                	 wl =  driver.findElement(By.xpath(recData[i][2].toString()));
 	                	 Update_Report("Pass "  +"  ",recData[i][2].toString()," able to find  element", driver);

	    			    }catch (Exception e){
		                	 Update_Report("Fail "  +"  ",recData[i][2].toString()," not able to find  element, terminating the test", driver);
                            e.printStackTrace();
                            break;
	    			    }  


	            }else  if (recData[i][1].equals("name")){
	    		    wl = driver.findElement(By.name(recData[i][2].toString()));
	            }else  if (recData[i][1].equals("linkText")){
	    		    wl = driver.findElement(By.xpath(recData[i][2].toString()));
	            }
	            if (recData[i][3].toString().equals("sendKeys")){
	    		    enterText(wl,  recData[i][4], recData[i][4].toString());

	            }else  if (recData[i][3].toString().equals("click")){
	            	try{
	            	 clickButton(wl, "Click ",recData[i][4]);
	     			Update_Report("Pass"+"  ", recData[i][2],"   click success", driver );

	            	 }catch (Exception e){
	                	 Update_Report("Fail "  +"  ",recData[i][2]," not able to find  element, terminating the test", driver);
                        e.printStackTrace();
                        break;
    			    }  

	            } 
	        }
			 
			 
		    bw.close();
       // driver.quit();
		  // driver.close();
		    
	}
	
	public  static void validateErrorMessage(){
		
	}
	
}

