package automationreport;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
	static WebDriver driver;
	
	public static void main(String[] args)  throws Exception{
		
	    String dt_path ="/Users/peter/Downloads/TestSuit.xlsx";
	    String[][] recData= 	ReusableMethods.readXLSheet(dt_path, "Sheet1");
   	 	System.setProperty("webdriver.gecko.driver", "/Users/peter/Downloads/Seleniumtestcases/geckodriver");
		driver = new FirefoxDriver();
		
	/*	System.setProperty("webdriver.chrome.driver", "/Users/peter/Downloads/Seleniumtestcases/chromedriver");
		driver = new FirefoxDriver();*/
				
	    for(int i=1; i<recData.length; i++){
	    	String execute =  recData[i][1];
	    	
	    	if(execute.equalsIgnoreCase("Y")){
	    		try{
	    			
		    		String testcase = recData[i][2];
		    		String start = recData[i][3];
		    		String end = recData[i][4];
		    		int startRow = Integer.parseInt(start);
		    		int endRow = Integer.parseInt(end);
	    		Object resulthtmlpath = ReusableMethods.startReport(testcase, "/Users/peter/ReportLogs");	
	    		
	    		/* Java Reflection */		
	    		Method tc = AutomationScripts.class.getMethod(testcase,int.class,int.class);
 	    	    
	    		
	    	  Object  result = tc.invoke(tc,startRow,endRow );
	    		
	    	  // if result true 
	    	  // update the xl sheet box recData[i][1] color to green and add hyper link pointin to resulthtmlpath
	    		} catch(Exception e){
	    			System.out.println(e);
	    		}
	    		
	    	}
	    	
	    }
	    
	    

	//	Method tc = AutomationScripts.class.getMethod("xeroLogin", Integer.class ,Integer.class);
	

		
		//AutomationScripts.xeroLogin();
		//AutomationScripts.xeroLogin(30,32);
		//AutomationScripts.xeroLogin(35,37);
		//AutomationScripts.xeroLogin(40,42);
		//AutomationScripts.xeroLogin(45,47);
		//AutomationScripts.xeroLogin(50,58);
		//AutomationScripts.xeroLogin(61,64);
		/*FreeTrail-Terms,Privacy Policy link_X*/
		//AutomationScripts.xeroLogin(67,69);
		//AutomationScripts.xeroLogin(72,73);
		/* Accountant/bookkeeper_X*/
		//AutomationScripts.xeroLogin(76,77);
		//AutomationScripts.xeroLogin(80,92);
		/*Logout Button_X*/
		//AutomationScripts.xeroLogin(95,99);
		
		  
		
		/*  Login To SalesForce -2  */
	//  AutomationScripts.SFDCLogin(2,4);
		/* Empty Password Field*/
	 // AutomationScripts.SFDCLogin(7,10);
		/* SF_Check RemeberMe - 3 */
	 //AutomationScripts.SFDCLogin(13,19);
		/*SF_Test forgot password -4A  */
	  // AutomationScripts.SFDCLogin(22,24);
		/*  SF_ValidateLoginErrorMessage-4B  */
	 // AutomationScripts.SFDCLogin(27,30); 
		
		/*SF_Username dropdowndisplay -5*/
	// AutomationScripts.SFDCLogin(105,109);
		/*SF_MyProfile from Username  */
	//AutomationScripts.SFDCLogin(112,132);
	/*  SF_MySettings from Username  -7 */
		//AutomationScripts.SFDCLogin(135,156);
		
	   
	}

}
